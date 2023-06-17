package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.layer.LayerStatusMapper;
import ru.hhschool.segment.mapper.merge.MergeResponseMapper;
import ru.hhschool.segment.mapper.validate.SegmentSelectedToSegmentValidateInfoMapper;
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
import ru.hhschool.segment.model.entity.QuestionRequiredLink;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;
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
import java.util.function.Function;
import java.util.stream.Collectors;

public class LayerService {
  private final LayerDao layerDao;
  private final PlatformDao platformDao;
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final SegmentService segmentService;
  private final QuestionRequiredLinkDao questionRequiredLinkDao;
  private final ScreenQuestionLinkDao screenQuestionLinkDao;
  private final SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao;

  @Inject
  public LayerService(LayerDao layerDao, PlatformDao platformDao, SegmentStateLinkDao segmentStateLinkDao, SegmentService segmentService, QuestionRequiredLinkDao questionRequiredLinkDao, ScreenQuestionLinkDao screenQuestionLinkDao, SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao) {
    this.layerDao = layerDao;
    this.platformDao = platformDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.segmentService = segmentService;
    this.questionRequiredLinkDao = questionRequiredLinkDao;
    this.screenQuestionLinkDao = screenQuestionLinkDao;
    this.segmentScreenEntrypointLinkDao = segmentScreenEntrypointLinkDao;
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
    layerDao.update(mergingLayer);

    List<Layer> parentsOfMergingLayer = layerDao.getAllParents(mergingLayer.getId());
    Collections.reverse(parentsOfMergingLayer);

    changeOldSegmentStateLinks(mergingLayer, parentsOfMergingLayer);
    changeOldQuestionRequiredLinks(mergingLayer, parentsOfMergingLayer);
    changeOldScreenQuestionLinks(mergingLayer, parentsOfMergingLayer);
    changeOldScreenEntrypointLinks(mergingLayer, parentsOfMergingLayer);

    List<SegmentLayerViewDto> segmentLayerViewDtoList = segmentService
        .getSegmentViewDtoListForSegmentsInLayerPage(mergingLayer.getId(), "").get()
        .getSegments();

    List<SegmentSelectedDto> selectedDtoList = new ArrayList<>();
    segmentLayerViewDtoList.forEach(segmentLayerViewDto -> {
      SegmentSelectedDto selectedDto = segmentService.getSegmentSelectedDto(mergingLayer.getId(), segmentLayerViewDto.getId()).get();
      selectedDtoList.add(selectedDto);
    });

    selectedDtoList.forEach(segmentSelectedDto -> {
      segmentService.validateSegment(SegmentSelectedToSegmentValidateInfoMapper.toDto(segmentSelectedDto)).forEach(validateResultDto -> {
        if (validateResultDto.getResult() != null) {
          throw new HttpBadRequestException("Ошибка валидации сегмента. Мердж невозможен");
        }
      });
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
  public void changeOldSegmentStateLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    Map<Long, SegmentStateLink> parentsSegmentStateLinkMap = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(parentsOfMergingLayer, ""));
    Map<Long, SegmentStateLink> margingSegmentStateLinkMap = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(List.of(mergingLayer), ""));
    margingSegmentStateLinkMap.forEach((id, segmentStateLink) -> {
      if (segmentStateLink.getOldSegmentStateLink().getId() != parentsSegmentStateLinkMap.get(id).getId()) {
        segmentStateLink.setOldSegmentStateLink(parentsSegmentStateLinkMap.get(id));
        segmentStateLinkDao.update(segmentStateLink);
      }
    });
  }

  @Transactional
  public void changeOldQuestionRequiredLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<QuestionRequiredLink> parentsQuestionRequiredLinks = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsQuestionRequiredLinks.addAll(questionRequiredLinkDao.findAll(layer.getId()));
    }
    List<QuestionRequiredLink> latestParentsQuestionRequiredLinks = segmentService.getLatestQRLInSpace(parentsQuestionRequiredLinks);
    List<QuestionRequiredLink> mergingQuestionRequiredLinks = questionRequiredLinkDao.findAll(mergingLayer.getId());
    Map<Long, QuestionRequiredLink> parentsQuestionRequiredLinksMap = latestParentsQuestionRequiredLinks
        .stream()
        .collect(Collectors.toMap(questionRequiredLink -> questionRequiredLink.getQuestion().getId(),
            Function.identity(), (link1, link2) -> link1));
    Map<Long, QuestionRequiredLink> mergingQuestionRequiredLinksMap = mergingQuestionRequiredLinks
        .stream()
        .collect(Collectors.toMap(questionRequiredLink -> questionRequiredLink.getQuestion().getId(),
            Function.identity(), (link1, link2) -> link1));
    mergingQuestionRequiredLinksMap.forEach((id, questionRequiredLink) -> {
      if (parentsQuestionRequiredLinksMap.get(id) != null && !Objects.equals(questionRequiredLink.getOldQuestionRequiredLink().getId(), parentsQuestionRequiredLinksMap.get(id).getId())) {
        questionRequiredLink.setOldQuestionRequiredLink(parentsQuestionRequiredLinksMap.get(id));
        questionRequiredLinkDao.update(questionRequiredLink);
      }
    });
  }

  @Transactional
  public void changeOldScreenQuestionLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<ScreenQuestionLink> parentsScreenQuestionLink = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsScreenQuestionLink.addAll(screenQuestionLinkDao.findAll(layer.getId()));
    }
    List<ScreenQuestionLink> latestParentsScreenQuestionLinks = segmentService.getLatestSQLInSpace(parentsScreenQuestionLink);
    List<ScreenQuestionLink> mergingScreenQuestionLinks = screenQuestionLinkDao.findAll(mergingLayer.getId());
    Map<String, ScreenQuestionLink> parentsScreenQuestionLinkMap = latestParentsScreenQuestionLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s,%s", link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle(),
                link.getQuestion().getTitle()),
            Function.identity(), (link1, link2) -> link1));
    Map<String, ScreenQuestionLink> mergingScreenQuestionLinkMap = mergingScreenQuestionLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s,%s", link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle(),
                link.getQuestion().getTitle()),
            Function.identity(), (link1, link2) -> link1));
    mergingScreenQuestionLinkMap.forEach((id, screenQuestionLink) -> {
      if (parentsScreenQuestionLinkMap.get(id) != null && !Objects.equals(screenQuestionLink.getOldScreenQuestionLink().getId(), parentsScreenQuestionLinkMap.get(id).getId())) {
        screenQuestionLink.setOldScreenQuestionLink(parentsScreenQuestionLinkMap.get(id));
        screenQuestionLinkDao.update(screenQuestionLink);
      }
    });
  }

  @Transactional
  public void changeOldScreenEntrypointLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<SegmentScreenEntrypointLink> parentsSegmentScreenEntrypointLink = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsSegmentScreenEntrypointLink.addAll(segmentScreenEntrypointLinkDao.findAll(layer.getId()));
    }
    List<SegmentScreenEntrypointLink> latestParentsSegmentScreenEntrypointLinks = segmentService.getLatestSSELInSpace(parentsSegmentScreenEntrypointLink);
    List<SegmentScreenEntrypointLink> mergingSegmentScreenEntrypointLinks = segmentScreenEntrypointLinkDao.findAll(mergingLayer.getId());
    Map<String, SegmentScreenEntrypointLink> parentsScreenQuestionLinkMap = latestParentsSegmentScreenEntrypointLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s",
                link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle()),
            Function.identity(), (link1, link2) -> link1));
    Map<String, SegmentScreenEntrypointLink> mergingScreenQuestionLinkMap = mergingSegmentScreenEntrypointLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s",
                link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle()),
            Function.identity(), (link1, link2) -> link1));
    mergingScreenQuestionLinkMap.forEach((id, segmentScreenEntrypointLink) -> {
      if (parentsScreenQuestionLinkMap.get(id) != null && !Objects.equals(segmentScreenEntrypointLink.getOldSegmentScreenEntrypointLink().getId(), parentsScreenQuestionLinkMap.get(id).getId())) {
        segmentScreenEntrypointLink.setOldSegmentScreenEntrypointLink(parentsScreenQuestionLinkMap.get(id));
        segmentScreenEntrypointLinkDao.update(segmentScreenEntrypointLink);
      }
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

  @Transactional
  public MergeResponseDto forceMergeLayer(Long layerId) {
    Layer mergingLayer = layerDao.findById(layerId)
        .orElseThrow(() -> new HttpNotFoundException("Такого слоя не существует"));
    if (mergingLayer.getState() != LayerStateType.CONFLICT) {
      throw new HttpNotFoundException("Force merge невозможен");
    }
    mergingLayer.setState(LayerStateType.STABLE);
    layerDao.update(mergingLayer);
    return MergeResponseMapper.toDtoResponse(mergingLayer);
  }
}
