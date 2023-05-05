package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.enums.ResumeField;

public interface QuestionActivatorLinkDao extends ReadWriteDao<QuestionActivatorLink, Long> {
  List<QuestionActivatorLink> findAll(Long layerId, Long entrypointId, ResumeField resumeField);

  List<QuestionActivatorLink> findAll(Long layerId, Long segmentId);

  Optional<QuestionActivatorLink> findExactly(Long layerId, Long segmentId, String questionTitle, Long entryPointId);

  List<QuestionActivatorLink> findAllByLayerId(Long layerId, ResumeField resumeField);
}
