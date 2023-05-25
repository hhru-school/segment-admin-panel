package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;

import java.util.List;

public class QuestionRequiredLinkDaoImpl extends ReadWriteDaoImpl<QuestionRequiredLink, Long> implements QuestionRequiredLinkDao {
  @Override
  public List<QuestionRequiredLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId) {
    return em.createQuery("SELECT e FROM QuestionRequiredLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getResultList();
  }
}
