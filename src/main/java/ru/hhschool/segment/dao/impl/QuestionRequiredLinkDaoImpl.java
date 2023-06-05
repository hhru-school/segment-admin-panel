package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;

public class QuestionRequiredLinkDaoImpl extends ReadWriteDaoImpl<QuestionRequiredLink, Long> implements QuestionRequiredLinkDao {
  @Override
  public Long countById(Long layerId, Long segmentId) {
    return (Long) em.createQuery("SELECT COUNT(e) FROM QuestionRequiredLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getSingleResult();
  }
}
