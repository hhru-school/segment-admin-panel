package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.model.entity.Segment;

import java.util.List;

public class SegmentDaoImpl extends ReadWriteDaoImpl<Segment, Long> implements SegmentDao {
  @Override
  public List<Segment> findAll(Long layerId) {
    return em.createQuery("SELECT DISTINCT e FROM Segment e WHERE e.layerId = :layerId")
        .setParameter("layerId", layerId)
        .getResultList();
  }
}
