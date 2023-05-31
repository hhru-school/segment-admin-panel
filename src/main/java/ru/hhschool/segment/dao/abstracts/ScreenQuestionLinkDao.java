package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.ScreenQuestionLink;

import java.util.List;

public interface ScreenQuestionLinkDao extends ReadWriteDao<ScreenQuestionLink, Long> {
  List<ScreenQuestionLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
}
