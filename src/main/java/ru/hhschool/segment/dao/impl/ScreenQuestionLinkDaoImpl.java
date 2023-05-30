package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;

public class ScreenQuestionLinkDaoImpl extends ReadWriteDaoImpl<ScreenQuestionLink, Long> implements ScreenQuestionLinkDao {
  @Override
  public Long countById(Long layerId, Long segmentId) {
    return (Long) em.createQuery("SELECT COUNT(e) FROM ScreenQuestionLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getSingleResult();
  }
  @Override
  public List<ScreenQuestionLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM ScreenQuestionLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
  @Override
  public List<ScreenQuestionLink> findAll(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM ScreenQuestionLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
  @Override
  public List<ScreenQuestionLink> findAll(Long layerId, Long segmentId, Long entrypointId, Long screenId) {
    return em.createQuery("SELECT e FROM ScreenQuestionLink e WHERE " +
            "e.layer.id = :layerId AND " +
            "e.segment.id = :segmentId AND " +
            "e.entrypoint.id = :entrypointId AND " +
            "e.screen.id = : screenId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .setParameter("entrypointId", entrypointId)
        .setParameter("screenId", screenId)
        .getResultList();
  }

}
