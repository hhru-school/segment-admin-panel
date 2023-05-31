package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Question;

public class QuestionDaoImpl extends ReadWriteDaoImpl<Question, Long> implements QuestionDao {
  @Override
  public List<Question> findAll(List<Long> questionsIdList) {
    List<Question> questionList = em.createQuery("SELECT q FROM Question q WHERE q.id IN :questionsIdList")
        .setParameter("questionsIdList", questionsIdList)
        .getResultList();
    return questionList;
  }
}
