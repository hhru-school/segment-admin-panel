package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;

import java.util.List;

public class ScreenQuestionLinkDaoImpl extends ReadWriteDaoImpl<ScreenQuestionLink, Long> implements ScreenQuestionLinkDao {
  @Override
  public List<ScreenQuestionLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM ScreenQuestionLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
}
