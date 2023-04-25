package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuestionService {
  private final LayerDao layerDao;
  private final QuestionDao questionDao;
  private final QuestionActivatorLinkDao questionActivatorLinkDao;
  private final AnswerService answerService;
  private final FilterService filterService;

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao, QuestionActivatorLinkDao questionActivatorLinkDao, AnswerService answerService, FilterService filterService) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
    this.answerService = answerService;
    this.filterService = filterService;
  }

  @Transactional
  public List<QuestionActivatorLink> createActivatorLinkListOfLayerWithParents(Long layerId) {
    Optional<Layer> optionalSelectedLayer = layerDao.findById(layerId);
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptyList();
    }
    List<Layer> selectedLayerWithParents = new ArrayList<>(List.of(optionalSelectedLayer.get()));
    selectedLayerWithParents.addAll(layerDao.getAllParents(layerId));

    return selectedLayerWithParents.stream()
        .map(layer -> questionActivatorLinkDao.findAllQuestionActivatorLinkByLayerId(layer.getId()))
        .flatMap(Collection::stream)
        .toList();
  }


  @Transactional
  public List<Question> createListOfQuestionByLayerId(Long layerId) {
    Map<String, Question> questionMap = createActivatorLinkListOfLayerWithParents(layerId).stream()
        .map(QuestionActivatorLink::getQuestion)
        .collect(Collectors.toMap(Question::getTitle, Function.identity(), (existingValue, newValue) -> existingValue));
    List<Question> questionList = new ArrayList<>(questionMap.values());
    questionList.sort(Comparator.comparingLong(Question::getId));
    return questionList;
  }

  @Transactional
  public List<QuestionDtoForQuestionsInfo> getAllQuestionDtoListForQuestionsInfo(Long layerId, String searchString) {
    List<QuestionDtoForQuestionsInfo> questionDtoForQuestionsInfoList = new ArrayList<>();
    List<Question> questionList = createListOfQuestionByLayerId(layerId);
    questionList.forEach(question -> {
      List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswerIdList(), questionList);
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
    List<AnswerDtoForQuestionsInfo> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswerIdList(), questionList);
    return QuestionMapper.toDtoForQuestionsInfo(question, answerDtoList);
  }
}