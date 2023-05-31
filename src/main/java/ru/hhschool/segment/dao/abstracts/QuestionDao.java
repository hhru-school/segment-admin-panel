package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Question;

public interface QuestionDao extends ReadWriteDao<Question, Long> {
  List<Question> findAll(List<Long> questionsIdList);
}
