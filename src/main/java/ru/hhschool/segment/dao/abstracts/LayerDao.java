package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Layer;

public interface LayerDao extends ReadWriteDao<Layer, Long> {
  List<Layer> getAllParents(Long id);

  List<Layer> findStableChildById(Long layerId);
}
