package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Layer;

import java.util.List;

public interface LayerDao extends ReadWriteDao<Layer, Long> {
  public List<Layer> getAllParents(Long id);
}
