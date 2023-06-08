package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.layer.LayerStatusMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.layer.LayerForListDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStateType;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LayerService {
  private final LayerDao layerDao;
  private final PlatformDao platformDao;

  private final SegmentService segmentService;

  @Inject
  public LayerService(LayerDao layerDao, PlatformDao platformDao, SegmentService segmentService) {
    this.layerDao = layerDao;
    this.platformDao = platformDao;
    this.segmentService = segmentService;
  }

  public List<LayerDto> getLayerDtoListForMainPage() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }

  @Transactional
  public Optional<LayerBasicInfoDto> getLayerDtoForBasicInfoPage(Long id) {
    Optional<Layer> layer = layerDao.findById(id);
    if (layer.isEmpty()) {
      return Optional.empty();
    }
    LayerBasicInfoDto layerBasicInfoDto = LayerBasicInfoMapper.toDtoForBasicInfoPage(layer.get(),
        layerDao.getAllParents(id),
        PlatformMapper.toDtoList(platformDao.findAll(layer.get().getPlatforms())));
    return Optional.of(layerBasicInfoDto);
  }

  @Transactional
  public List<List<String>> mergeLayerWithParent(Long layerId) {
    List<SegmentSelectedDto> selectedDtoList = new ArrayList<>();
    List<List<String>> conflictMessages = new ArrayList<>();
    Optional<Layer> optionalMergingLayer = layerDao.findById(layerId);
    Layer lastStableLayer = layerDao.findLastStableLayer();
    if (optionalMergingLayer.isEmpty()) {
      return List.of(List.of("Такого слоя не существует.  Последний стабильный слой имеет id" + lastStableLayer.getId()));
    }
    Layer mergingLayer = optionalMergingLayer.get();

    if (Objects.equals(mergingLayer.getParent().getId(), lastStableLayer.getId())) {
      mergingLayer.setState(LayerStateType.STABLE);
      layerDao.update(mergingLayer);
      return List.of(List.of("Тестовый слой унаследован от последнего стабильного слоя. [Сделал тестовый слой с Id" + mergingLayer.getId() + " стабильным]"));
    }
    mergingLayer.setParent(lastStableLayer);
    layerDao.update(mergingLayer);
    List<SegmentLayerViewDto> segmentLayerViewDtoList = segmentService
        .getSegmentViewDtoListForSegmentsInLayerPage(mergingLayer.getId(), "").get()
        .getSegments();
    segmentLayerViewDtoList.forEach(segmentLayerViewDto -> {
      SegmentSelectedDto selectedDto = segmentService.getSegmentSelectedDto(mergingLayer.getId(), segmentLayerViewDto.getId()).get();
      selectedDtoList.add(selectedDto);
    });
    conflictMessages.add(List.of("Мерджим  Слой с Id" + mergingLayer.getId() + "с последним стаблильным Id " + lastStableLayer.getId()));
    conflictMessages.add(checkStateSegment(selectedDtoList));
    conflictMessages.add(checkScreenPostion(selectedDtoList));
    conflictMessages.add(checkQuestionVisibilityAndPosition(selectedDtoList));
    if (conflictMessages.get(0) == null && conflictMessages.get(1) == null && conflictMessages.get(2) == null) {
      mergingLayer.setState(LayerStateType.STABLE);
      layerDao.update(mergingLayer);
      List.of(List.of("Конфликтов нет, сделал Слой с Id " + mergingLayer.getId() + "стабльиным"));
    }
    return conflictMessages;
  }

  public List<String> checkStateSegment(List<SegmentSelectedDto> selectedDtoList) {
    List<String> conflictMessages = new ArrayList<>();
    selectedDtoList.forEach(segmentSelectedDto -> {
      if (segmentSelectedDto.getOldActiveState() != null) {
        conflictMessages.add(
            "Есть конфликт состояния сегмента. Id сегмента " + segmentSelectedDto.getSegmentId() +
                " Старый статус " + segmentSelectedDto.getOldActiveState() +
                " Новый статус " + segmentSelectedDto.getActiveState());
      }
    });
    return conflictMessages;
  }

  public List<String> checkQuestionVisibilityAndPosition(List<SegmentSelectedDto> selectedDtoList) {
    List<String> conflictMessages = new ArrayList<>();
    selectedDtoList.forEach(segmentSelectedDto -> {
      segmentSelectedDto.getEntryPoints().forEach(segmentViewEntryPointDto -> {
        segmentViewEntryPointDto.getScreens().forEach(segmentViewScreenDto -> {
          segmentViewScreenDto.getFields().forEach(segmentViewQuestionDto -> {
            if (segmentViewQuestionDto.getOldPosition() != null) {
              conflictMessages.add(
                  "Есть конфликт позиции поля. Id сегмента " + segmentSelectedDto.getSegmentId() +
                      " Точка доступа " + segmentViewEntryPointDto.getTitle() +
                      " Название экрана " + segmentViewScreenDto.getTitle() +
                      " Старая позиция " + segmentViewQuestionDto.getOldPosition());
            }
            if (segmentViewQuestionDto.getOldVisibility() != null) {
              conflictMessages.add(
                  "Есть конфликт видимости поля. Id сегмента " + segmentSelectedDto.getSegmentId() +
                      " Точка доступа " + segmentViewEntryPointDto.getTitle() +
                      " Название экрана " + segmentViewScreenDto.getTitle() +
                      " Старая видимость " + segmentViewQuestionDto.getOldVisibility());
            }
          });
        });
      });
    });

    return conflictMessages;
  }

  public List<String> checkScreenPostion(List<SegmentSelectedDto> selectedDtoList) {
    List<String> conflictMessages = new ArrayList<>();
    selectedDtoList.forEach(segmentSelectedDto -> {
      segmentSelectedDto.getEntryPoints().forEach(segmentViewEntryPointDto -> {
        segmentViewEntryPointDto.getScreens().forEach(segmentViewScreenDto -> {
          if (segmentViewScreenDto.getOldPosition() != null) {
            conflictMessages.add(
                "Есть конфликт позиции экрана. Id сегмента " + segmentSelectedDto.getSegmentId() +
                    " Точка доступа " + segmentViewEntryPointDto.getTitle() +
                    " Название экрана " + segmentViewScreenDto.getTitle() +
                    " Старая позиция " + segmentViewScreenDto.getOldPosition());
          }
        });
      });
    });

    return conflictMessages;
  }

  public List<LayerForListDto> getAll(List<String> layerStringStatus) {
    List<LayerStateType> layerStatusList = LayerStatusMapper.toStatusList(layerStringStatus);
    List<Layer> layerList = layerDao.findAll(layerStatusList);

    return LayerMapper.toLayerForListDto(layerList);
  }

  @Transactional
  public void setLayerStateToArchive(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      throw new HttpNotFoundException("Слой не найден.");
    }
    layer.get().setState(LayerStateType.ARCHIVE);
    try {
      layerDao.update(layer.get());
    } catch (Exception err) {
      String lastMessage = err.getMessage();
      Throwable cause = err.getCause();
      while (cause != null) {
        lastMessage = cause.getMessage();
        cause = cause.getCause();
      }
      throw new HttpBadRequestException(lastMessage);
    }
  }
}
