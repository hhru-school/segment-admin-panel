package ru.hhschool.segment.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Answer;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.entity.Segment;

@Transactional
public class LayerDaoImpl extends ReadWriteDaoImpl<Layer, Long> implements LayerDao {
  @Override
  public Optional<Layer> findByIdFetchEager(Long layerId) {
    Layer layer = em.find(Layer.class, layerId);

    if (layer == null) {
      return Optional.empty();
    }

    List<Entrypoint> entrypointList = em.createQuery(
            "SELECT e FROM Entrypoint e WHERE e.layerId = :layerId", Entrypoint.class)
        .setParameter("layerId", layerId)
        .getResultList();
    layer.setEntrypointList(entrypointList);

    List<Segment> segmentList = em.createQuery(
            "SELECT e FROM Segment e WHERE e.layerId = :layerId", Segment.class)
        .setParameter("layerId", layerId)
        .getResultList();
    layer.setSegmentList(segmentList);

    List<Question> questionList = em.createQuery(
            "SELECT e FROM Question e WHERE e.layerId = :layerId", Question.class)
        .setParameter("layerId", layerId)
        .getResultList();
    layer.setQuestionList(questionList);

    List<Answer> answerList = em.createQuery(
            "SELECT e FROM Answer e WHERE e.layerId = :layerId", Answer.class)
        .setParameter("layerId", layerId)
        .getResultList();
    layer.setAnswerList(answerList);

    List<QuestionActivatorLink> questionActivatorLinkList = em.createQuery(
            "SELECT qa FROM QuestionActivatorLink qa" +
                " LEFT JOIN FETCH qa.entrypoint " +
                " LEFT JOIN FETCH qa.segment " +
                " LEFT JOIN FETCH qa.question " +
                " WHERE qa.layerId = :layerId", QuestionActivatorLink.class)
        .setParameter("layerId", layerId)
        .getResultList();
    layer.setQuestionActivatorLinksList(questionActivatorLinkList);

    return Optional.of(layer);
  }

}
