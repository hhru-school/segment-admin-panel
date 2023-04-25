package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.ResumeField;

import java.util.List;

public interface QuestionActivatorLinkDao extends ReadWriteDao<QuestionActivatorLink, Long> {
  List<QuestionActivatorLink> findAll(Long layerId, Long entrypointId, ResumeField resumeField);

  List<QuestionActivatorLink> findAllQuestionActivatorLinkByLayerId(Long layerId);
}
