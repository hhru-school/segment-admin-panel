package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public interface SegmentScreenEntrypointLinkDao extends ReadWriteDao<SegmentScreenEntrypointLink, Long> {
  List<SegmentScreenEntrypointLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
  List<SegmentScreenEntrypointLink> findAllBySegmentIdEntrypointId(Long segmentId, Long entrypointId);
  List<SegmentScreenEntrypointLink> findAll(Long layerId, Long segmentId, Long entrypointId);
  Long countById(Long layerId, Long segmentId);
}
