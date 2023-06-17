package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.mapper.question.QuestionMapper;
import ru.hhschool.segment.mapper.question.QuestionSatusMapper;
import ru.hhschool.segment.model.dto.question.QuestionCreateDto;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.entity.Question;

public class QuestionService {
  private final QuestionDao questionDao;
  private final AnswerService answerService;
  private final QuestionFilterService questionFilterService;
  private static final int MAX_DEPTH_OF_TREE = 3;

  @Inject
  public QuestionService(QuestionDao questionDao, AnswerService answerService, QuestionFilterService questionFilterService) {
    this.questionDao = questionDao;
    this.answerService = answerService;
    this.questionFilterService = questionFilterService;
  }

  @Transactional
  public List<QuestionDtoForQuestionsInfo> getAllQuestionDtoListForQuestionsInfo(String searchQuery, List<String> questionTypeStringList) {
    List<QuestionDtoForQuestionsInfo> questionDtoForQuestionsInfoList = new ArrayList<>();
    List<Question> questionList;
    if (questionTypeStringList == null || questionTypeStringList.size() == 0) {
      questionList = questionDao.findAll();
    } else {
      questionList = questionDao.findAllByType(QuestionSatusMapper.toTypeList(questionTypeStringList));
    }
    List<Question> finalQuestionList = questionList;
    questionList.forEach(question -> {
      List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswers(), finalQuestionList, MAX_DEPTH_OF_TREE);
      QuestionDtoForQuestionsInfo questionDto = QuestionMapper.questionToDto(question, answerDtoList);
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
    Optional<Question> question = questionList.stream()
        .filter(question1 -> Objects.equals(question1.getId(), questionId))
        .findFirst();
    if (question.isEmpty()) {
      throw new HttpBadRequestException("Вопроса с таким Id  не существует");
    }
    List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.get().getPossibleAnswers(), questionList, 3);
    return QuestionMapper.questionToDto(question.get(), answerDtoList);
  }

  public Optional<ScreenDto> add(QuestionCreateDto questionCreateDto) {
    return null;
  }
}
