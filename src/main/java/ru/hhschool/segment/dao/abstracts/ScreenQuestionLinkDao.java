package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.ScreenQuestionLink;

public interface ScreenQuestionLinkDao extends ReadWriteDao<ScreenQuestionLink, Long> {
  Long countById(Long layerId, Long segmentId);
}
