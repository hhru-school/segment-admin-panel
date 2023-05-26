package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

public class QuestionService {
  private final LayerDao layerDao;
  private final QuestionDao questionDao;
  private final AnswerService answerService;
  private final FilterService filterService;

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao, AnswerService answerService, FilterService filterService) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
    this.answerService = answerService;
    this.filterService = filterService;
  }


  @Transactional
  public List<Question> createListOfQuestionByLayerId(Long layerId) {
    return null;
  }

  @Transactional
  public List<QuestionDtoForQuestionsInfo> getAllQuestionDtoListForQuestionsInfo(Long layerId, String searchString) {
    List<QuestionDtoForQuestionsInfo> questionDtoForQuestionsInfoList = new ArrayList<>();
    List<Question> questionList = createListOfQuestionByLayerId(layerId);
    questionList.forEach(question -> {
      List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswers(), questionList, 3);
      QuestionDtoForQuestionsInfo questionDto = QuestionMapper.toDtoForQuestionsInfo(question, answerDtoList);
      questionDtoForQuestionsInfoList.add(questionDto);
    });
    if (searchString == null || searchString.equals("")) {
      return questionDtoForQuestionsInfoList;
    }
    return filterService.filterQuestionDtoListByString(searchString, questionDtoForQuestionsInfoList);
  }

  @Transactional
  public QuestionDtoForQuestionsInfo getQuestionDtoForQuestionInfo(Long layerId, Long questionId) {
    List<Question> questionList = createListOfQuestionByLayerId(layerId);
    Question question = questionList.stream()
        .filter(question1 -> Objects.equals(question1.getId(), questionId))
        .findFirst()
        .orElseGet(null);
    List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswers(), questionList, 3);
    return QuestionMapper.toDtoForQuestionsInfo(question, answerDtoList);
  }
}