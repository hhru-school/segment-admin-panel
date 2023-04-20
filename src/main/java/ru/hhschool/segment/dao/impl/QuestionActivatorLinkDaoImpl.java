package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.ResumeField;

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
}
