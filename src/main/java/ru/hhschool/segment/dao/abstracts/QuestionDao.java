package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.QuestionType;

import java.util.List;

public interface QuestionDao extends ReadWriteDao<Question, Long> {
  List<Question> findAllByType(List<QuestionType> questionTypeList);
}
