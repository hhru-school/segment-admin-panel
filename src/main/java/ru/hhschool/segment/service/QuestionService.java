package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionService {
  private final LayerDao layerDao;
  private final QuestionDao questionDao;

  private final QuestionActivatorLinkDao questionActivatorLinkDao;

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao, QuestionActivatorLinkDao questionActivatorLinkDao) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
  }


  @Transactional
  public Object getListQuestionDtoOfLayerAndParentsWithAnswers(Long layerId) {
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

    return questionActivatorLinkDao.findById(1L).get().getQuestion().getTitle();
  }
}
