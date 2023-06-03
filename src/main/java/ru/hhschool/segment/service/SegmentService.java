package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.*;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.mapper.RoleMapper;
import ru.hhschool.segment.mapper.SegmentMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentLayerViewMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.LayerSegmentsMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentSelectedMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentViewRequirementMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentViewEntryPointMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentViewScreenMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentViewPlatformMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentViewQuestionMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.dto.viewsegments.layerview.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;
import ru.hhschool.segment.model.entity.SegmentStateLink;
import ru.hhschool.segment.model.entity.Role;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Collections;

public class SegmentService {
  private final LayerDao layerDao;
  private final SegmentDao segmentDao;
  private final EntrypointDao entrypointDao;
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final ScreenQuestionLinkDao screenQuestionLinkDao;
  private final SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao;
  private final QuestionRequiredLinkDao questionRequiredLinkDao;
  private final RoleDao roleDao;
  private final PlatformDao platformDao;

  @Inject
  public SegmentService(LayerDao layerDao,
                        SegmentDao segmentDao,
                        EntrypointDao entrypointDao,
                        SegmentStateLinkDao segmentStateLinkDao,
                        ScreenQuestionLinkDao screenQuestionLinkDao,
                        SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao,
                        QuestionRequiredLinkDao questionRequiredLinkDao,
                        RoleDao roleDao, PlatformDao platformDao) {
    this.layerDao = layerDao;
    this.segmentDao = segmentDao;
    this.entrypointDao = entrypointDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.screenQuestionLinkDao = screenQuestionLinkDao;
    this.segmentScreenEntrypointLinkDao = segmentScreenEntrypointLinkDao;
    this.questionRequiredLinkDao = questionRequiredLinkDao;
    this.roleDao = roleDao;
    this.platformDao = platformDao;
  }

  @Transactional
  public List<SegmentDto> getAll(String searchQuery) {
    List<Segment> segmentList = segmentDao.findAll(searchQuery.trim());

    List<SegmentDto> segmentDtoList = new ArrayList<>();
    for (Segment segment : segmentList) {
      List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.getRoleList()));
      segmentDtoList.add(SegmentMapper.segmentToDto(segment, roleList));
    }

    return segmentDtoList;
  }

  @Transactional
  public Optional<SegmentDto> add(SegmentCreateDto segmentCreateDto) {
    if (segmentCreateDto.getTitle() == null || segmentCreateDto.getTitle().isBlank()) {
      throw new HttpBadRequestException("Название(Title) неверно указанное значение или пустой.");
    }
    if (segmentCreateDto.getRolesId() == null || segmentCreateDto.getRolesId().isEmpty()) {
      throw new HttpBadRequestException("Не заданы значения массива Roles");
    }
    Optional<Segment> parentSegment = Optional.empty();
    if (segmentCreateDto.getParentSegmentId() != null) {
      parentSegment = segmentDao.findById(segmentCreateDto.getParentSegmentId());
      if (parentSegment.isEmpty()) {
        throw new HttpBadRequestException("Указанный ParentId не существует.");
      }
    }

    Segment segment = SegmentMapper.dtoToSegment(segmentCreateDto, parentSegment);
    try {
      segmentDao.persist(segment);
    } catch (Exception err) {
      String lastMessage = err.getMessage();
      Throwable cause = err.getCause();
      while (cause != null) {
        lastMessage = cause.getMessage();
        cause = cause.getCause();
      }
      throw new HttpBadRequestException(lastMessage);
    }

    List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.getRoleList()));

    return Optional.of(SegmentMapper.segmentToDto(segment, roleList));
  }

  @Transactional
  public Optional<SegmentDto> getById(Long segmentId) {
    Optional<Segment> segment = segmentDao.findById(segmentId);
    if (segment.isEmpty()) {
      return Optional.empty();
    }
    List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.get().getRoleList()));

    return Optional.of(SegmentMapper.segmentToDto(segment.get(), roleList));
  }

  @Transactional
  public Optional<LayerSegmentsDto> getSegmentViewDtoListForSegmentsInLayerPage(Long layerId, String searchQuery) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()){
      return Optional.empty();
    }
    List<Layer> space = getLayersInSpace(layerId);
    Map<Long, SegmentStateLink> stateLinkMap = getLatestSSLInSpace(getSSLInSpace(space, searchQuery));
    List<SegmentLayerViewDto> segmentLayerViewDtos = new ArrayList<>();
    for (Long key : stateLinkMap.keySet()) {
      SegmentStateLink link = stateLinkMap.get(key);
      Segment segment = link.getSegment();
      List<Role> roles = roleDao.findAll(segment.getRoleList());
      SegmentViewChangeState changeState = getChangeSegmentState(layerId, segment.getId());
      segmentLayerViewDtos.add(SegmentLayerViewMapper.toDtoForSegmentsInLayerPage(segment, roles, changeState, link.getState()));
    }
    segmentLayerViewDtos.sort(Comparator.comparing(SegmentLayerViewDto::getTitle));
    LayerSegmentsDto layerSegmentsDto = LayerSegmentsMapper.toDtoForSegmentsInLayerPage(layer.get(), segmentLayerViewDtos);
    return Optional.of(layerSegmentsDto);
  }

  private SegmentViewChangeState getChangeSegmentState(Long layerId, Long segmentId) {
    Optional<SegmentStateLink> segmentStateLink = segmentStateLinkDao.findById(layerId, segmentId);
    if (segmentStateLink.isPresent()) {
      if (segmentStateLink.get().getOldSegmentStateLink() == null){
        return SegmentViewChangeState.NEW;
      } else {
        return SegmentViewChangeState.CHANGED;
      }
    }
    Long screenQuestionLinksCount = screenQuestionLinkDao.countById(layerId, segmentId);
    Long segmentScreenEntrypointLinksCount = segmentScreenEntrypointLinkDao.countById(layerId, segmentId);
    Long questionRequiredLinksCount = questionRequiredLinkDao.countById(layerId, segmentId);
    if (screenQuestionLinksCount == 0 && segmentScreenEntrypointLinksCount == 0 && questionRequiredLinksCount == 0) {
      return SegmentViewChangeState.NOT_CHANGED;
    }
    return SegmentViewChangeState.CHANGED;
  }

  private List<SegmentStateLink> getSSLInSpace(List<Layer> layerSpace, String searchQuery) {
    List<SegmentStateLink> questionActivatorLinkList = new ArrayList<>();
    for(Layer layer : layerSpace){
      questionActivatorLinkList.addAll(segmentStateLinkDao.findAll(layer.getId(), searchQuery));
    }
    return questionActivatorLinkList;
  }

  private Map<Long, SegmentStateLink> getLatestSSLInSpace(List<SegmentStateLink> links) {
    Map<Long, SegmentStateLink> segmentStateLinkMap = new HashMap<>();
    for (SegmentStateLink link : links) {
      Long key = link.getSegment().getId();
      segmentStateLinkMap.put(key, link);
    }
    return segmentStateLinkMap;
  }

  private List<Layer> getLayersInSpace(Long layerId){
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()) {
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      Collections.reverse(layersInSpace);
      layersInSpace.add(layer.get());
      return layersInSpace;
    }
    return Collections.EMPTY_LIST;
  }

  @Transactional
  public Optional<SegmentSelectedDto> getSegmentSelectedDto(Long layerId, Long segmentId) {
    Optional<SegmentStateLink> segmentStateLink = segmentStateLinkDao.findById(layerId, segmentId);
    if (segmentStateLink.isPresent()){
      Segment segment = segmentStateLink.get().getSegment();
      Optional<Layer> layer = layerDao.findById(layerId);
      List<Role> roles = roleDao.findAll(segment.getRoleList());
      List<Layer> space = getLayersInSpace(layerId);
      List<QuestionRequiredLink> questionRequiredLinks = getLatestQRLInSpace(getQRLInSpace(space, segmentId));
      List<SegmentViewRequirementDto> segmentViewRequirementDtoList = getSegmentViewRequirementDtos(questionRequiredLinks, layerId);
      List<SegmentViewEntryPointDto> segmentViewEntryPointDtoList = getSegmentViewEntryPointDtos(layerId, segmentId);
      return Optional.of(SegmentSelectedMapper.toDtoForSelectedSegmentViewPage(layer.get(), segment, segmentStateLink.get(), roles, segmentViewRequirementDtoList, segmentViewEntryPointDtoList));
    }
    return Optional.empty();
  }
  private List<QuestionRequiredLink> getQRLInSpace(List<Layer> space, Long segmentId) {
    List<QuestionRequiredLink> questionRequiredLinks = new ArrayList<>();
    for (Layer layer : space) {
      questionRequiredLinks.addAll(questionRequiredLinkDao.findAll(layer.getId(), segmentId));
    }
    return questionRequiredLinks;
  }
  private List<QuestionRequiredLink> getLatestQRLInSpace(List<QuestionRequiredLink> links){
    Map<String, QuestionRequiredLink> questionRequiredLinkMap = new HashMap<>();
    for (QuestionRequiredLink link : links) {
      String key = link.getSegment().getTitle() + link.getQuestion().getTitle();
      if (questionRequiredLinkMap.get(key) != null){
        questionRequiredLinkMap.replace(key, link);
      } else {
        questionRequiredLinkMap.put(key, link);
      }
    }
    return questionRequiredLinkMap.values().stream().toList();
  }
  private List<SegmentViewRequirementDto> getSegmentViewRequirementDtos(List<QuestionRequiredLink> links, Long layerId) {
    return links.stream()
        .map(link -> SegmentViewRequirementMapper.toDtoForSelectedSegmentViewPage(link,
            link.getLayer().getId().equals(layerId) && link.getOldQuestionRequiredLink() != null && link.isQuestionRequired() != link.getOldQuestionRequiredLink().isQuestionRequired(),
            link.getLayer().getId().equals(layerId) && link.getOldQuestionRequiredLink() == null))
        .sorted(Comparator.comparing(SegmentViewRequirementDto::getTitle))
        .toList();
  }
  private List<SegmentViewEntryPointDto> getSegmentViewEntryPointDtos(Long layerId, Long segmentId){
    List<Entrypoint> entrypoints = entrypointDao.findAll();
    return entrypoints.stream()
        .map(entrypoint -> SegmentViewEntryPointMapper.toDtoForSelectedSegmentViewPage(entrypoint, getSegmentViewScreenDtos(layerId, segmentId, entrypoint.getId())))
        .toList();
  }
  private List<SegmentViewScreenDto> getSegmentViewScreenDtos(Long layerId, Long segmentId, Long entrypointId){
    List<Layer> space = getLayersInSpace(layerId);
    List<SegmentScreenEntrypointLink> segmentScreenEntrypointLinks = getLatestSSELInSpace(getSSELInSpace(space, segmentId, entrypointId));
    return segmentScreenEntrypointLinks.stream()
        .sorted(Comparator.comparing(SegmentScreenEntrypointLink::getScreenPosition))
        .map(link -> SegmentViewScreenMapper.toDtoForSelectedSegmentViewPage(link,
            layerId,
            link.getLayer().getId().equals(layerId) && link.getOldSegmentScreenEntrypointLink() == null,
            SegmentViewPlatformMapper.toDtoForSelectedSegmentViewPage(platformDao.findAll(link.getScreen().getPlatforms())),
            getSegmentViewQuestionDtos(layerId, link)))
        .toList();
  }
  private List<SegmentScreenEntrypointLink> getSSELInSpace(List<Layer> space, Long segmentId, Long entrypointId) {
    List<SegmentScreenEntrypointLink> segmentScreenEntrypointLinks = new ArrayList<>();
    for (Layer layer : space) {
      segmentScreenEntrypointLinks.addAll(segmentScreenEntrypointLinkDao.findAll(layer.getId(), segmentId, entrypointId));
    }
    return segmentScreenEntrypointLinks;
  }
  private List<SegmentScreenEntrypointLink> getLatestSSELInSpace(List<SegmentScreenEntrypointLink> links){
    Map<String, SegmentScreenEntrypointLink> segmentScreenEntrypointLinkMap = new HashMap<>();
    for (SegmentScreenEntrypointLink link : links) {
      String key = link.getSegment().getTitle() + link.getEntrypoint().getTitle() + link.getScreen().getTitle();
      if (segmentScreenEntrypointLinkMap.get(key) != null){
        segmentScreenEntrypointLinkMap.replace(key, link);
      } else {
        segmentScreenEntrypointLinkMap.put(key, link);
      }
    }
    return segmentScreenEntrypointLinkMap.values().stream().toList();
  }

  private List<SegmentViewQuestionDto> getSegmentViewQuestionDtos(Long layerId, SegmentScreenEntrypointLink link){
    List<Layer> space = getLayersInSpace(layerId);
    List<ScreenQuestionLink> screenQuestionLinks = getLatestSQLInSpace(getSQLInSpace(space, link));
    return screenQuestionLinks.stream()
        .sorted(Comparator.comparing(ScreenQuestionLink::getQuestionPosition))
        .map(questionLink -> SegmentViewQuestionMapper.toDtoForSelectedSegmentViewPage(questionLink.getQuestion(),
            layerId,
            questionLink.getLayer().getId().equals(layerId) && questionLink.getOldScreenQuestionLink() == null,
            questionLink))
        .toList();
  }
  private List<ScreenQuestionLink> getSQLInSpace(List<Layer> space, SegmentScreenEntrypointLink link) {
    List<ScreenQuestionLink> screenQuestionLinks = new ArrayList<>();
    for (Layer layer : space) {
      screenQuestionLinks.addAll(screenQuestionLinkDao.findAll(layer.getId(), link.getSegment().getId(), link.getEntrypoint().getId(), link.getScreen().getId()));
    }
    return screenQuestionLinks;
  }
  private List<ScreenQuestionLink> getLatestSQLInSpace(List<ScreenQuestionLink> links){
    Map<String, ScreenQuestionLink> questionRequiredLinkMap = new HashMap<>();
    for (ScreenQuestionLink link : links) {
      String key = link.getSegment().getTitle() + link.getEntrypoint().getTitle() + link.getScreen().getTitle() + link.getQuestion().getTitle();
      if (questionRequiredLinkMap.get(key) != null){
        questionRequiredLinkMap.replace(key, link);
      } else {
        questionRequiredLinkMap.put(key, link);
      }
    }
    return questionRequiredLinkMap.values().stream().toList();
  }
}
