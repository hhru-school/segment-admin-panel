package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.ResumeField;

public interface QuestionActivatorLinkDao extends ReadWriteDao<QuestionActivatorLink, Long> {
  List<QuestionActivatorLink> findAll(Long layerId, Long entrypointId, ResumeField resumeField);
  List<QuestionActivatorLink> findAllQusetionActivatorLinkByLayerId(Long layerId);
}
