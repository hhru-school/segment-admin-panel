package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

public class SegmentScreenEntrypointLinkDaoImpl extends ReadWriteDaoImpl<SegmentScreenEntrypointLink, Long> implements SegmentScreenEntrypointLinkDao {
  @Override
  public Long countById(Long layerId, Long segmentId) {
    return (Long) em.createQuery("SELECT COUNT(e) FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getSingleResult();
  }

  @Override
  public List<SegmentScreenEntrypointLink> findAll(Long layerId) {
    return em.createQuery("SELECT e FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId ")
        .setParameter("layerId", layerId)
        .getResultList();
  }

  @Override
  public List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId, Long entrypointId) {
    return em.createQuery(
            "SELECT e FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId AND e.entrypoint.id = " +
                ":entrypointId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .setParameter("entrypointId", entrypointId)
        .getResultList();
  }

  @Override
  public List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
}
