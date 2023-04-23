package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.dto.questiondetailinfo.QuestionDtoForQuestionDetailInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
  public Set<QuestionDtoForQuestionsInfoPage> getSetQuestionDtoOfLayerAndParentsWithAnswers(Long layerId, String searchString) {
    Optional<Layer> optionalSelectedLayer = layerDao.findById(layerId);
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptySet();
    }
    List<Layer> selectedLayerWithParents = new ArrayList<>(List.of(optionalSelectedLayer.get()));
    selectedLayerWithParents.addAll(layerDao.getAllParents(layerId));
    Set<QuestionDtoForQuestionsInfoPage> questionDtoForQuestionsInfoPageSet = selectedLayerWithParents
        .stream()
        .map(layer -> questionActivatorLinkDao.findAllQuestionActivatorLinkByLayerId(layer.getId()))
        .flatMap(Collection::stream)
        .map(this::createQuestionDtoWithAnswers)
        .map(QuestionMapper::toDtoForQuestionsInfo)
        .collect(Collectors.toSet());
    if (searchString == null || searchString.equals("")) {
      return questionDtoForQuestionsInfoPageSet;
    }

    return filterService.filterQuestionDtoSetByString(searchString,questionDtoForQuestionsInfoPageSet);
  }

  public QuestionDto createQuestionDtoWithAnswers(QuestionActivatorLink questionActivatorLink) {
    Question question = questionActivatorLink.getQuestion();
    List<AnswerDto> answerDtoList = answerService.getAllAnswerDtoListByListId(question.getPossibleAnswerIdList());
    return QuestionMapper.toDto(questionActivatorLink.getQuestion(), answerDtoList);
  }

  @Transactional
  public QuestionDtoForQuestionDetailInfo сreateQuestionDtoWithAnswersAndStatus(Long layerId, Long questionId) {
    QuestionActivatorLink questionActivatorLink = questionActivatorLinkDao.findQuestionActivatorLinkByLayerIdAndQuestionId(layerId, questionId);
    QuestionDto questionDto = createQuestionDtoWithAnswers(questionActivatorLink);
    return QuestionMapper.toDtoForQuestionDetailInfo(questionDto);
  }
}
