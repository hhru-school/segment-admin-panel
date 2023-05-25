package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentApplicationScreenLinkDao;
import ru.hhschool.segment.model.entity.SegmentApplicationScreenLink;

import java.util.List;

public class SegmentApplicationScreenLinkDaoImpl extends ReadWriteDaoImpl<SegmentApplicationScreenLink, Long> implements SegmentApplicationScreenLinkDao {
  @Override
  public List<SegmentApplicationScreenLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM SegmentApplicationScreenLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
}
