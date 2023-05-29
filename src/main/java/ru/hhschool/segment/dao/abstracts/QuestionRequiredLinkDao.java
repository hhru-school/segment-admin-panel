package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.QuestionRequiredLink;

import java.util.List;

public interface QuestionRequiredLinkDao extends ReadWriteDao<QuestionRequiredLink, Long> {
  List<QuestionRequiredLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
  List<QuestionRequiredLink> findAllByLayerIdQuestionId(Long layerId, Long questionId);
  List<QuestionRequiredLink> findAllByQuestionId(Long segmentId);
  Long countById(Long layerId, Long segmentId);
}
