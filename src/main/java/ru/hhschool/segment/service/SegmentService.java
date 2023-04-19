package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Segment;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

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
    Layer layer = layerDao.findById(layerId);
    List<Layer> layersInSpace = layerDao.getAllParents(layerId);
    layersInSpace.add(layer);
    List<Segment> segmentList = segmentDao.findSegmentsInSpace(layersInSpace);
    return SegmentViewMapper.toDtoListForAllSegmentsPage(segmentList);
  }
}
