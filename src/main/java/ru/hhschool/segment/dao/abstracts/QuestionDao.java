package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public interface QuestionDao extends ReadWriteDao<Question, Long> {
  List<Question> findAllQuestionByLayerId(Long layerId);
}
