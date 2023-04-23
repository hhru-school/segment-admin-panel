package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.AnswerMapper;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.entity.Answer;
import ru.hhschool.segment.model.entity.Question;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerService {
  private final AnswerDao answerDao;
  private final QuestionDao questionDao;

  @Inject
  public AnswerService(AnswerDao answerDao, QuestionDao questionDao) {
    this.answerDao = answerDao;
    this.questionDao = questionDao;
  }

  public List<AnswerDto> getAllAnswerDtoListByListId(List<Long> answersIdList) {
    if (answersIdList == null) {
      return Collections.emptyList();
    }
    List<AnswerDto> answerDtoList = new ArrayList<>();
    answersIdList.forEach(answerId -> {
      Answer answer = answerDao.findById(answerId).orElseGet(null);
      answerDtoList.add(AnswerMapper.toDto(answer, getAllOpenQuestionForAnswer(answer.getOpenQuestionList())));
    });
    return answerDtoList;
  }

  public List<QuestionDto> getAllOpenQuestionForAnswer(List<Long> openQuestionIdList) {
    if (openQuestionIdList == null) {
      return Collections.emptyList();
    }
    List<QuestionDto> questionDtoList = new ArrayList<>();
    openQuestionIdList.forEach(questionId -> {
      Question question = questionDao.findById(questionId).orElseGet(null);
      questionDtoList.add(QuestionMapper.toDto(question, getAllAnswerDtoListByListId(question.getPossibleAnswerIdList())));
    });
    return questionDtoList;
  }
}
