package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.layer.LayerStatusMapper;
import ru.hhschool.segment.mapper.merge.MergeResponseMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.layer.LayerDtoForList;
import ru.hhschool.segment.model.dto.merge.MergeResponseDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.SegmentStateLink;
import ru.hhschool.segment.model.enums.LayerStateType;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LayerService {
  private final LayerDao layerDao;
  private final PlatformDao platformDao;
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final SegmentService segmentService;

  @Inject
  public LayerService(LayerDao layerDao, PlatformDao platformDao, SegmentStateLinkDao segmentStateLinkDao, SegmentService segmentService) {
    this.layerDao = layerDao;
    this.platformDao = platformDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.segmentService = segmentService;
  }

  public List<LayerDto> getLayerGroupList() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }

  @Transactional
  public Optional<LayerBasicInfoDto> getLayerDtoForBasicInfoPage(Long id) {
    Optional<Layer> layer = layerDao.findById(id);
    if (layer.isEmpty()) {
      return Optional.empty();
    }
    LayerBasicInfoDto layerBasicInfoDto = LayerBasicInfoMapper.toDtoForBasicInfoPage(
        layer.get(),
        layerDao.getAllParents(id),
        PlatformMapper.toDtoList(platformDao.findAll(layer.get().getPlatforms()))
    );
    return Optional.of(layerBasicInfoDto);
  }

  @Transactional
  public MergeResponseDto mergeLayerWithParent(Long layerId) {
    Layer mergingLayer = layerDao.findById(layerId)
        .orElseThrow(() -> new HttpNotFoundException("Такого слоя не существует"));
    Layer lastStableLayer = layerDao.findLastStableLayer();
    if (mergingLayer.getState() == LayerStateType.STABLE) {
      throw new HttpBadRequestException("Слой с Id " + mergingLayer.getId() + " уже стабильный");
    }
    if (mergingLayer.getState() == LayerStateType.ARCHIVE) {
      throw new HttpBadRequestException("Слой с Id " + mergingLayer.getId() + "архивный");
    }
    if (Objects.equals(mergingLayer.getParent().getId(), lastStableLayer.getId()) && mergingLayer.getState() != LayerStateType.CONFLICT) {
      mergingLayer.setState(LayerStateType.STABLE);
      layerDao.update(mergingLayer);
      return MergeResponseMapper.toDtoResponse(mergingLayer);
    }
    mergingLayer.setParent(lastStableLayer);
    changeSSL(mergingLayer);
    layerDao.update(mergingLayer);
    List<SegmentLayerViewDto> segmentLayerViewDtoList = segmentService
        .getSegmentViewDtoListForSegmentsInLayerPage(mergingLayer.getId(), "").get()
        .getSegments();
    List<SegmentSelectedDto> selectedDtoList = new ArrayList<>();
    segmentLayerViewDtoList.forEach(segmentLayerViewDto -> {
      SegmentSelectedDto selectedDto = segmentService.getSegmentSelectedDto(mergingLayer.getId(), segmentLayerViewDto.getId()).get();
      selectedDtoList.add(selectedDto);
    });
    if (!checkStateSegment(selectedDtoList) ||
        !checkQuestionVisibilityAndPosition(selectedDtoList) ||
        !checkScreenPositionAndState(selectedDtoList) ||
        !checkRequiredQuestion(selectedDtoList)) {
      mergingLayer.setState(LayerStateType.CONFLICT);
      layerDao.update(mergingLayer);
      return MergeResponseMapper.toDtoResponse(mergingLayer);
    }
    mergingLayer.setState(LayerStateType.STABLE);
    layerDao.update(mergingLayer);
    return MergeResponseMapper.toDtoResponse(mergingLayer);
  }

  @Transactional
  public void changeSSL(Layer mergingLayer) {
    List<Layer> parentsOfMergingLayer = layerDao.getAllParents(mergingLayer.getId());
    Collections.reverse(parentsOfMergingLayer);
    List<SegmentLayerViewDto> segmentDtoList = segmentService
        .getSegmentViewDtoListForSegmentsInLayerPage(mergingLayer.getId(), "")
        .get()
        .getSegments();
    segmentDtoList.forEach(segmentSelectedDto -> {
      Map<Long, SegmentStateLink> segmentStateLinkMap = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(parentsOfMergingLayer, ""));
      Map<Long, SegmentStateLink> segmentStateLinkMapMerging = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(List.of(mergingLayer), ""));
      segmentStateLinkMapMerging.forEach((id, segmentStateLink) -> {
        if (segmentStateLink.getOldSegmentStateLink().getId()) {
          segmentStateLink.setOldSegmentStateLink(segmentStateLinkMap.get(segmentStateLink.getSegment().getId()));
          segmentStateLinkDao.update(segmentStateLink);
        }
      });
    });
  }

  public boolean checkStateSegment(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      if (segmentSelectedDto.getOldActiveState() != null && segmentSelectedDto.getActiveState() != segmentSelectedDto.getOldActiveState()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkQuestionVisibilityAndPosition(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewEntryPointDto segmentViewEntryPointDto : segmentSelectedDto.getEntryPoints()) {
        for (SegmentViewScreenDto segmentViewScreenDto : segmentViewEntryPointDto.getScreens()) {
          for (SegmentViewQuestionDto segmentViewQuestionDto : segmentViewScreenDto.getFields()) {
            if (segmentViewQuestionDto.getOldPosition() != null || segmentViewQuestionDto.getOldVisibility() != null) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  public boolean checkScreenPositionAndState(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewEntryPointDto segmentViewEntryPointDto : segmentSelectedDto.getEntryPoints()) {
        for (SegmentViewScreenDto segmentViewScreenDto : segmentViewEntryPointDto.getScreens()) {
          if (segmentViewScreenDto.getOldPosition() != null) {
            return false;
          }
          if (segmentViewScreenDto.getOldState() != null) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean checkRequiredQuestion(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewRequirementDto segmentViewRequirementDto : segmentSelectedDto.getFields()) {
        if (segmentViewRequirementDto.getIsChanged()) {
          return false;
        }
      }
    }
    return true;
  }


  public List<LayerDtoForList> getAll(List<String> layerStringStateTypes) {
    List<LayerStateType> layerStateTypes = LayerStatusMapper.toStatusList(layerStringStateTypes);
    List<Layer> layerList = layerDao.findAll(layerStateTypes);


    return LayerMapper.toLayerForListDto(layerList);
  }

  @Transactional
  public void setLayerStateToArchive(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      throw new HttpNotFoundException("Слой не найден.");
    }
    if (layer.get().getState() == LayerStateType.STABLE) {
      throw new HttpBadRequestException("Не возможно STABLE слой сделать архивным.");
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
