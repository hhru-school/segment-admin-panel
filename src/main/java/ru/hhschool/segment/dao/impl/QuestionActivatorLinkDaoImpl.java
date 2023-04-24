package ru.hhschool.segment.dao.impl;

import java.util.*;

import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.ResumeField;

import javax.persistence.NoResultException;

public class QuestionActivatorLinkDaoImpl extends ReadWriteDaoImpl<QuestionActivatorLink, Long> implements QuestionActivatorLinkDao {

  public List<QuestionActivatorLink> findAll(Long layerId, Long entrypointId, ResumeField resumeField) {
    List<QuestionActivatorLink> entrypointList = em.createQuery(
            """
                SELECT e FROM QuestionActivatorLink e 
                WHERE e.layerId = :layerId
                 AND e.entrypoint.id = :entrypointId
                 AND e.question.resumeField = :resumeField
                """, QuestionActivatorLink.class)
        .setParameter("layerId", layerId)
        .setParameter("entrypointId", entrypointId)
        .setParameter("resumeField", resumeField.isResumeField())
        .getResultList();

    return entrypointList;
  }

  @Override
  public List<QuestionActivatorLink> findQALInSpace(List<Layer> layerSpace, Long segmentId) {
    List<QuestionActivatorLink> questionActivatorLinkList = new ArrayList<>();
    for(Layer layer : layerSpace){
      questionActivatorLinkList.addAll(
          em.createQuery("SELECT e FROM QuestionActivatorLink e " +
                  "WHERE e.layerId = :layerId " +
                  "AND e.segment.id = :segmentId")
              .setParameter("layerId", layer.getId())
              .setParameter("segmentId", segmentId)
              .getResultList()
      );
    }
    return questionActivatorLinkList;
  }

  @Override
  public Optional<QuestionActivatorLink> findExactly(Long layerId, Long segmentId, Long questionId, Long entryPointId) {
    try {
      return Optional.of(em.createQuery("SELECT e FROM QuestionActivatorLink e WHERE " +
              "e.layerId = :layerId " +
              "AND e.segment.id = :segmentId " +
              "AND e.question.id = :questionId " +
              "AND e.entrypoint.id = :entrypointId", QuestionActivatorLink.class)
          .setParameter("layerId", layerId)
          .setParameter("segmentId", segmentId)
          .setParameter("questionId", questionId)
          .setParameter("entrypointId", entryPointId)
          .getSingleResult());
    } catch (NoResultException nre){
      return Optional.empty();
    }
  }

  public List<QuestionActivatorLink> findAllByLayerId(Long layerId,ResumeField resumeField) {

    return em.createQuery(
            """
                SELECT e FROM QuestionActivatorLink e 
                WHERE e.layerId = :layerId
                 AND e.question.resumeField = :resumeField
                """, QuestionActivatorLink.class)
        .setParameter("layerId", layerId)
        .setParameter("resumeField", resumeField.isResumeField())
        .getResultList();
  }
}
