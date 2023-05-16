package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Segment;

import java.util.Collection;
import java.util.List;

public interface SegmentDao extends ReadWriteDao<Segment, Long>{

  List<Segment> findAll(Long layerId);
}
