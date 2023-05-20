package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.mapper.RoleMapper;
import ru.hhschool.segment.mapper.SegmentMapper;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.SegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentService {
  private final SegmentDao segmentDao;
  private final LayerDao layerDao;
  private final RoleDao roleDao;

  @Inject
  public SegmentService(SegmentDao segmentDao, LayerDao layerDao, RoleDao roleDao) {
    this.segmentDao = segmentDao;
    this.layerDao = layerDao;
    this.roleDao = roleDao;
  }

  @Transactional
  public List<SegmentViewDto> getSegmentViewDtoListForAllSegmentsPage(Long layerId) {
    return SegmentViewMapper.toDtoListForAllSegmentsPage(getSegmentsForSpace(layerId));
  }

  public List<Segment> getSegmentsForSpace(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()) {
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      layersInSpace.add(layer.get());
      return findSegmentsInSpace(layersInSpace);
    }
    return Collections.EMPTY_LIST;
  }

  private List<Segment> findSegmentsInSpace(Collection<Layer> space) {
    List<Segment> segmentList = new ArrayList<>();
    for (Layer layer : space) {
      // в сегментах теперь нет поля layerId
//      segmentList.addAll(segmentDao.findAll(layer.getId()));
    }
    return segmentList;
  }

  @Transactional
  public Optional<SegmentSelectedDto> getSegmentSelectedDto(Long layerId, Long segmentId) {
    return Optional.empty();
  }

  private List<Layer> getLayersInSpace(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()) {
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      Collections.reverse(layersInSpace);
      layersInSpace.add(layer.get());
      return layersInSpace;
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * Получить список всех Сегментов, с условием фильтра searchQuery.
   */
  @Transactional
  public List<SegmentDto> getAll(String searchQuery) {
    List<Segment> segmentList = segmentDao.findAll(searchQuery);

    List<SegmentDto> segmentDtoList = new ArrayList<>();
    for (Segment segment : segmentList) {
      List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.getRoleList()));
      segmentDtoList.add(SegmentMapper.segmentToDto(segment, roleList));
    }

    return segmentDtoList;
  }
}
