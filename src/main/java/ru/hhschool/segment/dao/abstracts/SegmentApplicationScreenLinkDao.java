package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentApplicationScreenLink;

import java.util.List;

public interface SegmentApplicationScreenLinkDao extends ReadWriteDao<SegmentApplicationScreenLink, Long> {
  List<SegmentApplicationScreenLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
}
