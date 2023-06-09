package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.QuestionType;

import java.util.List;

public class QuestionDaoImpl extends ReadWriteDaoImpl<Question, Long> implements QuestionDao {
  @Override
  public List<Question> findAllByType(List<QuestionType> questionTypeList) {
    return (List<Question>) em.createQuery("""
            SELECT q
             FROM Question q
             WHERE q.type IN :questionTypeList
             ORDER BY q.id ASC 
            """)
        .setParameter("questionTypeList", questionTypeList)
        .getResultList();
  }
}
