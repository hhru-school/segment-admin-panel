package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.questionsinfopage.AnswerMapperForQuestionsInfoPage;
import ru.hhschool.segment.mapper.questionsinfopage.QuestionMapperForQuestionsInfoPage;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
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

  public List<AnswerDtoForQuestionsInfoPage> getAllAnswerDtoListByListId(List<Long> answersIdList) {
    List<AnswerDtoForQuestionsInfoPage> answerDtoList = new ArrayList<>();
    answersIdList.forEach(answerId -> {
      Answer answer = answerDao.findById(answerId).orElseGet(null);
      answerDtoList.add(AnswerMapperForQuestionsInfoPage.toDto(answer, getAllOpenQuestionForAnswer(answer.getOpenQuestionList())));
    });
    return answerDtoList;
  }

  public List<QuestionDtoForQuestionsInfoPage> getAllOpenQuestionForAnswer(List<Long> openQuestionIdList) {
    if (openQuestionIdList == null) {
      return Collections.emptyList();
    }
    List<QuestionDtoForQuestionsInfoPage> questionDtoList = new ArrayList<>();
    openQuestionIdList.forEach(questionId -> {
      Question question = questionDao.findById(questionId).orElseGet(null);
      questionDtoList.add(QuestionMapperForQuestionsInfoPage.toDto(question, getAllAnswerDtoListByListId(question.getPossibleAnswerIdList())));
    });
    return questionDtoList;
  }
}
