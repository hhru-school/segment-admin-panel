package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.SegmentStateLink;

import java.util.List;
import java.util.Optional;

public interface SegmentStateLinkDao extends ReadWriteDao<SegmentStateLink, Long> {
  List<SegmentStateLink> findAllByLayerId(Long layerId);
  Optional<SegmentStateLink> findById(Long layerId, Long segmentId);
}
