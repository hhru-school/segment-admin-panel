package ru.hhschool.segment.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionService {
  private final LayerDao layerDao;
  private final QuestionDao questionDao;

  private  final QuestionActivatorLinkDao questionActivatorLinkDao;

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao, QuestionActivatorLinkDao questionActivatorLinkDao) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
  }

  @Transactional
  public List<List<QuestionActivatorLink>> getListQuestionDtoOfLayerAndParentsWithAnswers(Long layerId) {
    Optional<Layer> optionalSelectedLayer = layerDao.findById(layerId);
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptyList();
    }
    List<List<QuestionActivatorLink>> questionActivatorLinkList = new ArrayList<>();
    List<Layer> selectedLayerWithParents = new ArrayList<>(List.of(optionalSelectedLayer.get()));
    selectedLayerWithParents.addAll(layerDao.getAllParents(layerId));
    selectedLayerWithParents.forEach(layer -> {
      questionActivatorLinkList.add(questionActivatorLinkDao.findAllQusetionActivatorLinkByLayerId(layer.getId()));
      System.out.println(questionActivatorLinkList);
    });
    return questionActivatorLinkList;
  }
}
