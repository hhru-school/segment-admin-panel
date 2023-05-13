package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.model.dto.viewsegments.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

public class SegmentService {
  private final SegmentDao segmentDao;
  private final LayerDao layerDao;

  @Inject
  public SegmentService(SegmentDao segmentDao, LayerDao layerDao) {
    this.segmentDao = segmentDao;
    this.layerDao = layerDao;
  }

  @Transactional
  public List<SegmentViewDto> getSegmentViewDtoListForAllSegmentsPage(Long layerId) {
    return SegmentViewMapper.toDtoListForAllSegmentsPage(getSegmentsForSpace(layerId));
  }

  public List<Segment> getSegmentsForSpace(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()){
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      layersInSpace.add(layer.get());
      return findSegmentsInSpace(layersInSpace);
    }
    return Collections.EMPTY_LIST;
  }

  private List<Segment> findSegmentsInSpace(Collection<Layer> space) {
    List<Segment> segmentList = new ArrayList<>();
    for (Layer layer : space) {
      segmentList.addAll(segmentDao.findAll(layer.getId()));
    }
    return segmentList;
  }

  @Transactional
  public Optional<SegmentSelectedDto> getSegmentSelectedDto(Long layerId, Long segmentId) {
    return Optional.empty();
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
