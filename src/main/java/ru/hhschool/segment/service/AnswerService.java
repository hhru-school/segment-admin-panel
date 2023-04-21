package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.mapper.questionsinfo.AnswerMapperForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfo.AnswerDtoForQuestionsPage;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AnswerService {
  private final AnswerDao answerDao;

  @Inject
  public AnswerService(AnswerDao answerDao) {
    this.answerDao = answerDao;
  }

  public List<AnswerDtoForQuestionsPage> getAllAnswerDtoListByListId(List<Long> answersIdList) {
    List<AnswerDtoForQuestionsPage> answerDtoList = new ArrayList<>();
    answersIdList.forEach(answerId -> {
      answerDtoList.add(AnswerMapperForQuestionsInfo.toDto(answerDao.findById(answerId).orElseGet(null)));
    });
    return answerDtoList;
  }
}
