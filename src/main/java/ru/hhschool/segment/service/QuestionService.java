package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.questionsinfo.QuestionMapperForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfo.QuestionDtoForQuestionsPage;
import ru.hhschool.segment.model.entity.Layer;
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

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao, QuestionActivatorLinkDao questionActivatorLinkDao, AnswerService answerService) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
    this.answerService = answerService;
  }


  @Transactional
  public Object getSetQuestionDtoOfLayerAndParentsWithAnswers(Long layerId) {
    Optional<Layer> optionalSelectedLayer = layerDao.findById(layerId);
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptyList();
    }
    List<List<QuestionActivatorLink>> questionActivatorLinkListList = new ArrayList<>();
    List<Layer> selectedLayerWithParents = new ArrayList<>(List.of(optionalSelectedLayer.get()));
    selectedLayerWithParents.addAll(layerDao.getAllParents(layerId));
    selectedLayerWithParents.forEach(layer -> {
      questionActivatorLinkListList.add(questionActivatorLinkDao.findAllQusetionActivatorLinkByLayerId(layer.getId()));
    });

    Set<QuestionDtoForQuestionsPage> questionDtoList = questionActivatorLinkListList
        .stream()
        .flatMap(Collection::stream)
        .map(questionActivatorLink -> QuestionMapperForQuestionsInfo.toDto(questionActivatorLink.getQuestion(), answerService.getAllAsnwerDtoListByListId(questionActivatorLink.getQuestion().getPossibleAnswerIdList())))
        .collect(Collectors.toSet());
    return questionDtoList;
  }
}
