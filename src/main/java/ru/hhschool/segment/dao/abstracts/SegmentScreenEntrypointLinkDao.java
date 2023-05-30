package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

public interface SegmentScreenEntrypointLinkDao extends ReadWriteDao<SegmentScreenEntrypointLink, Long> {
  List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId);
  List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId, Long entrypointId);
  Long countById(Long layerId, Long segmentId);
}
