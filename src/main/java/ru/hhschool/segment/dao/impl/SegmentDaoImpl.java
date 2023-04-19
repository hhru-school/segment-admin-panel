package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Segment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SegmentDaoImpl extends ReadWriteDaoImpl<Segment, Long> implements SegmentDao {
  @Override
  public List<Segment> findSegmentsInSpace(Collection<Layer> space) {
    List<Segment> segmentList = new ArrayList<>();
    for (Layer layer : space) {
      segmentList.addAll(
          em.createQuery("SELECT DISTINCT e FROM Segment e WHERE e.layerId = :layerId")
              .setParameter("layerId", layer.getId())
              .getResultList());
    }
    return segmentList;
  }
}
