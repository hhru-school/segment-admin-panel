package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Segment;

public interface SegmentDao extends ReadWriteDao<Segment, Long> {

  List<Segment> findAll(Long layerId);
}
