package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentDaoImpl extends ReadWriteDaoImpl<Segment, Long> implements SegmentDao {
  @Override
  public List<Segment> findAll(String searchQuery) {
    List<Segment> segmentList;
    if (searchQuery.isBlank()) {
      segmentList = findAll();
    } else {
      segmentList = em.createQuery("SELECT s FROM Segment s WHERE LOWER(s.title) LIKE LOWER(:searchQuery)")
          .setParameter("searchQuery", "%" + searchQuery + "%")
          .getResultList();
    }
    return segmentList;
  }
  @Override
  public List<Segment> findAll(Long layerId) {
    return em.createQuery("SELECT DISTINCT e FROM Segment e WHERE e.layerId = :layerId")
        .setParameter("layerId", layerId)
        .getResultList();
  }
}
