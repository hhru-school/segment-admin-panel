package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.model.entity.SegmentStateLink;

public interface SegmentStateLinkDao extends ReadWriteDao<SegmentStateLink, Long> {
  List<SegmentStateLink> findAllByLayerId(Long layerId);
  List<SegmentStateLink> findAllByLayerIdSegmentId(Long layerId, Long segmentId);
  List<SegmentStateLink> findAllBySegmentId(Long segmentId);
  List<SegmentStateLink> findAll(Long layerId, String searchQuery);

  Optional<SegmentStateLink> findById(Long layerId, Long segmentId);
  Optional<SegmentStateLink> findInSpace(Long layerId, Long segmentId);
}
