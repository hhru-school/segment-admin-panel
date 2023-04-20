package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;

import javax.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class QuestionService {
  private final LayerDao layerDao;
  private final QuestionDao questionDao;

  @Inject
  public QuestionService(LayerDao layerDao, QuestionDao questionDao) {
    this.layerDao = layerDao;
    this.questionDao = questionDao;
  }

  public Set<Question> getListQuestionDtoOfLayerAndParentsWithAnswers(Long layerId) {
    Optional<Layer> optionalSelectedLayer = Optional.ofNullable(layerDao.findById(layerId));
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptySet();
    }
    Layer selectedLayer = optionalSelectedLayer.get();
    List<Layer> parentsOfSelectedLayer = layerDao.getAllParents(layerId);
    Set<Question> questionSet = new HashSet<>(selectedLayer.getQuestionList());
    parentsOfSelectedLayer.forEach(layer -> {
      questionSet.addAll(layer.getQuestionList());
    });
    return questionSet;
  }
}
