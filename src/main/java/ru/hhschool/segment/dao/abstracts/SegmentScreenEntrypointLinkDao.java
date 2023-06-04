package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

public interface SegmentScreenEntrypointLinkDao extends ReadWriteDao<SegmentScreenEntrypointLink, Long> {
  Long countById(Long layerId, Long segmentId);
}
