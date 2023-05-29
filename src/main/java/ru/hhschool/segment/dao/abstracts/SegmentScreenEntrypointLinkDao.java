package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public interface SegmentScreenEntrypointLinkDao extends ReadWriteDao<SegmentScreenEntrypointLink, Long> {
  List<SegmentScreenEntrypointLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
}
