package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionDaoImpl extends ReadWriteDaoImpl<Question, Long> implements QuestionDao {

  @Override
  public List<Question> findAllQuestionByLayerId(Long layerId) {
    return em.createQuery(
            "SELECT e FROM Question e WHERE e.layerId = :layerId", Question.class)
        .setParameter("layerId", layerId)
        .getResultList();
  }
}
