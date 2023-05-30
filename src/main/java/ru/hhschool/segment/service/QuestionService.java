package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionService {
  private final QuestionDao questionDao;
  private final AnswerService answerService;
  private final QuestionFilterService questionFilterService;

  @Inject
  public QuestionService(QuestionDao questionDao, AnswerService answerService, QuestionFilterService questionFilterService) {
    this.questionDao = questionDao;
    this.answerService = answerService;
    this.questionFilterService = questionFilterService;
  }

  @Transactional
  public List<QuestionDtoForQuestionsInfo> getAllQuestionDtoListForQuestionsInfo(String searchQuery) {
    List<QuestionDtoForQuestionsInfo> questionDtoForQuestionsInfoList = new ArrayList<>();
    List<Question> questionList = questionDao.findAll();
    questionList.forEach(question -> {
      List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswers(), questionList, 3);
      QuestionDtoForQuestionsInfo questionDto = QuestionMapper.toDtoForQuestionsInfo(question, answerDtoList);
      questionDtoForQuestionsInfoList.add(questionDto);
    });
    if (searchQuery == null || searchQuery.equals("")) {
      return questionDtoForQuestionsInfoList;
    }
    return questionFilterService.filterQuestionDtoListByString(searchQuery, questionDtoForQuestionsInfoList);
  }

  @Transactional
  public QuestionDtoForQuestionsInfo getQuestionDtoForQuestionInfo(Long questionId) {
    List<Question> questionList = questionDao.findAll();
    Question question = questionList.stream()
        .filter(question1 -> Objects.equals(question1.getId(), questionId))
        .findFirst()
        .orElseGet(null);
    List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswers(), questionList, 3);
    return QuestionMapper.toDtoForQuestionsInfo(question, answerDtoList);
  }
}
