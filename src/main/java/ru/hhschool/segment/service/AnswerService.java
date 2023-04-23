package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.AnswerMapper;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.entity.Question;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    return answersIdList.stream()
        .map(answerDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(answer -> AnswerMapper.toDto(answer, getAllOpenQuestionForAnswer(answer.getOpenQuestionList())))
        .collect(Collectors.toList());
  }


  public List<QuestionDto> getAllOpenQuestionForAnswer(List<Long> openQuestionIdList) {
    if (openQuestionIdList == null) {
      return Collections.emptyList();
    }
    return openQuestionIdList.stream()
        .map(questionDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(question -> QuestionMapper.toDto(question, getAllAnswerDtoListByListId(question.getPossibleAnswerIdList())))
        .collect(Collectors.toList());
  }
}
