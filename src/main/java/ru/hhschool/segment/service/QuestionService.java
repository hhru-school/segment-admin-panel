package ru.hhschool.segment.service;

import org.springframework.transaction.annotation.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;

import javax.inject.Inject;
import java.util.ArrayList;
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

  @Transactional
  public Set<Question> getListQuestionDtoOfLayerAndParentsWithAnswers(Long layerId) {
    Optional<Layer> optionalSelectedLayer = Optional.ofNullable(layerDao.findById(layerId));
    if (optionalSelectedLayer.isEmpty()) {
      return Collections.emptySet();
    }
    Set<Question> questionSet = new HashSet<>();
    List<Layer> selectedLayerWithParents = new ArrayList<>(List.of(optionalSelectedLayer.get()));
    selectedLayerWithParents.addAll(layerDao.getAllParents(layerId));
    selectedLayerWithParents.forEach(layer -> {
      questionSet.addAll(questionDao.findAllQuestionByLayerId(layer.getId()));
    });
    return questionSet;
  }
}
