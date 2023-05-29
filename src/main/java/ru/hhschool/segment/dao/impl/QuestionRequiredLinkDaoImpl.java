package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;

import java.util.List;

public class QuestionRequiredLinkDaoImpl extends ReadWriteDaoImpl<QuestionRequiredLink, Long> implements QuestionRequiredLinkDao {
  @Override
  public Long countById(Long layerId, Long segmentId) {
    return (Long) em.createQuery("SELECT COUNT(e) FROM QuestionRequiredLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId")
        .setParameter("layerId", layerId)
        .setParameter("segmentId", segmentId)
        .getSingleResult();
  }
  @Override
  public List<QuestionRequiredLink> findAllByLayerIdQuestionId(Long layerId, Long questionId) {
    return em.createQuery("SELECT e FROM QuestionRequiredLink e WHERE e.layer.id = :layerId AND e.question.id = :questionId")
        .setParameter("layerId", layerId)
        .setParameter("questionId", questionId)
        .getResultList();
  }
  @Override
  public List<QuestionRequiredLink> findAllByQuestionId(Long questionId) {
    return em.createQuery("SELECT e FROM QuestionRequiredLink e WHERE e.question.id = :questionId")
        .setParameter("questionId", questionId)
        .getResultList();
  }
}
