package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentApplicationScreenLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.SegmentStateLink;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.entity.SegmentApplicationScreenLink;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.HashMap;
import java.util.Collections;

public class SegmentService {
  private final LayerDao layerDao;
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final ScreenQuestionLinkDao screenQuestionLinkDao;
  private final SegmentApplicationScreenLinkDao segmentApplicationScreenLinkDao;
  private final QuestionRequiredLinkDao questionRequiredLinkDao;
  private final RoleDao roleDao;

  @Inject
  public SegmentService(LayerDao layerDao,
                        SegmentStateLinkDao segmentStateLinkDao,
                        ScreenQuestionLinkDao screenQuestionLinkDao,
                        SegmentApplicationScreenLinkDao segmentApplicationScreenLinkDao,
                        QuestionRequiredLinkDao questionRequiredLinkDao,
                        RoleDao roleDao) {
    this.layerDao = layerDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.screenQuestionLinkDao = screenQuestionLinkDao;
    this.segmentApplicationScreenLinkDao = segmentApplicationScreenLinkDao;
    this.questionRequiredLinkDao = questionRequiredLinkDao;
    this.roleDao = roleDao;
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
  public List<SegmentViewDto> getSegmentViewDtoListForSegmentsInLayerPage(Long layerId) {
    List<Layer> space = getLayersInSpace(layerId);
    Map<Long, SegmentStateLink> stateLinkMap = getLatestSSLInSpace(getSSLInSpace(space));
    List<SegmentViewDto> segmentViewDtos = new ArrayList<>();
    for (Long key : stateLinkMap.keySet()) {
      SegmentStateLink link = stateLinkMap.get(key);
      Segment segment = link.getSegment();
      List<Role> roles = getRoles(segment);
      SegmentViewChangeState changeState = getChangeSegmentState(layerId, segment.getId());
      segmentViewDtos.add(SegmentViewMapper.toDtoForSegmentsInLayerPage(segment, roles, changeState, link.getState()));
    }
    segmentViewDtos.sort(Comparator.comparing(SegmentViewDto::getTitle));
    return segmentViewDtos;
  }
  private List<Role> getRoles(Segment segment) {
    return segment.getRoleList().stream()
        .map(id -> roleDao.findById(id))
        .filter(role -> role.isPresent())
        .map(role -> role.get())
        .toList();
  }
  private SegmentViewChangeState getChangeSegmentState(Long layerId, Long segmentId) {
    Optional<SegmentStateLink> segmentStateLink = segmentStateLinkDao.findById(layerId, segmentId);
    if (segmentStateLink.isPresent()) {
      if (segmentStateLink.get().getOldSegmentStateLink() == null){
        return SegmentViewChangeState.NEW;
      } else if (segmentStateLink.get().getOldSegmentStateLink() != null) {
        return SegmentViewChangeState.CHANGED;
      }
    }
    List<ScreenQuestionLink> screenQuestionLinks = screenQuestionLinkDao.findAllByLayerIdSegmentId(layerId, segmentId);
    List<SegmentApplicationScreenLink> segmentApplicationScreenLinks = segmentApplicationScreenLinkDao.findAllByLayerIdSegmentId(layerId, segmentId);
    List<QuestionRequiredLink> questionRequiredLinks = questionRequiredLinkDao.findAllByLayerIdSegmentId(layerId, segmentId);
    if (screenQuestionLinks.isEmpty() && segmentApplicationScreenLinks.isEmpty() && questionRequiredLinks.isEmpty()) {
      return SegmentViewChangeState.NOT_CHANGED;
    }
    return SegmentViewChangeState.CHANGED;
  }
  private List<SegmentStateLink> getSSLInSpace(List<Layer> layerSpace) {
    List<SegmentStateLink> questionActivatorLinkList = new ArrayList<>();
    for(Layer layer : layerSpace){
      questionActivatorLinkList.addAll(segmentStateLinkDao.findAllByLayerId(layer.getId()));
    }
    return questionActivatorLinkList;
  }
  private Map<Long, SegmentStateLink> getLatestSSLInSpace(List<SegmentStateLink> links) {
    Map<Long, SegmentStateLink> segmentStateLinkMap = new HashMap<>();
    for (SegmentStateLink link : links) {
      Long key = link.getSegment().getId();
      if (segmentStateLinkMap.get(key) != null){
        segmentStateLinkMap.replace(key, link);
      }
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
}
