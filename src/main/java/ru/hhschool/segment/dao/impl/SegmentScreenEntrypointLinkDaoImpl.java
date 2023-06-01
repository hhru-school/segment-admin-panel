package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public class SegmentScreenEntrypointLinkDaoImpl extends ReadWriteDaoImpl<SegmentScreenEntrypointLink, Long> implements SegmentScreenEntrypointLinkDao {
  @Override
  public Long countById(Long layerId, Long segmentId) {
    return (Long) em.createQuery("SELECT COUNT(e) FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getSingleResult();
  }
}
