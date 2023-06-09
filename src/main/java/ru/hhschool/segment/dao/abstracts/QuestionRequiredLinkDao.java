package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.QuestionRequiredLink;

import java.util.List;

public interface QuestionRequiredLinkDao extends ReadWriteDao<QuestionRequiredLink, Long> {
  List<QuestionRequiredLink> findAll(Long layerId, Long segmentId);

  Long countById(Long layerId, Long segmentId);

  List<QuestionRequiredLink> findAll(Long layerId);
}
