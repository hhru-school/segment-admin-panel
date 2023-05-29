package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public class SegmentScreenEntrypointLinkDaoImpl extends ReadWriteDaoImpl<SegmentScreenEntrypointLink, Long> implements SegmentScreenEntrypointLinkDao {
  @Override
  public List<SegmentScreenEntrypointLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }

  @Override
  public List<SegmentScreenEntrypointLink> findAllBySegmentIdEntrypointId(Long segmentId, Long entrypointId) {
    return em.createQuery("SELECT e FROM SegmentScreenEntrypointLink e WHERE e.segment.id = :layerId AND e.entrypoint.id = :segmentId")
        .setParameter("segmentId", segmentId)
        .setParameter("entrypointId", entrypointId)
        .getResultList();
  }

  @Override
  public List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId, Long entrypointId) {
    return em.createQuery("SELECT e FROM SegmentScreenEntrypointLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId AND e.entrypoint.id = :entrypointId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .setParameter("entrypointId", entrypointId)
        .getResultList();
  }
}
