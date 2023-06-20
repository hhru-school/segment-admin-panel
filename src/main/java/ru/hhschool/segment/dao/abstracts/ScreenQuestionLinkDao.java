package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;

public interface ScreenQuestionLinkDao extends ReadWriteDao<ScreenQuestionLink, Long> {
  List<ScreenQuestionLink> findAll(Long layerId, Long segmentId, Long entrypointId, Long screenId);

  Long countById(Long layerId, Long segmentId);

  List<ScreenQuestionLink> findAll(Long layerId);
}
