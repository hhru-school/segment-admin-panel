package ru.hhschool.segment.service;

import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.mapper.RoleMapper;
import ru.hhschool.segment.mapper.SegmentMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.LayerSegmentsMapper;
import ru.hhschool.segment.mapper.viewsegments.layerview.SegmentLayerViewMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.dto.viewsegments.layerview.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.Layer;
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
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final ScreenQuestionLinkDao screenQuestionLinkDao;
  private final SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao;
  private final QuestionRequiredLinkDao questionRequiredLinkDao;
  private final RoleDao roleDao;

  @Inject
  public SegmentService(LayerDao layerDao,
                        SegmentDao segmentDao, SegmentStateLinkDao segmentStateLinkDao,
                        ScreenQuestionLinkDao screenQuestionLinkDao,
                        SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao,
                        QuestionRequiredLinkDao questionRequiredLinkDao,
                        RoleDao roleDao) {
    this.layerDao = layerDao;
    this.segmentDao = segmentDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.screenQuestionLinkDao = screenQuestionLinkDao;
    this.segmentScreenEntrypointLinkDao = segmentScreenEntrypointLinkDao;
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
    if (screenQuestionLinksCount.equals(0) && segmentScreenEntrypointLinksCount.equals(0) && questionRequiredLinksCount.equals(0)) {
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
      if (segmentStateLinkMap.get(key) != null){
        segmentStateLinkMap.replace(key, link);
      } else {
        segmentStateLinkMap.put(key, link);
      }
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
