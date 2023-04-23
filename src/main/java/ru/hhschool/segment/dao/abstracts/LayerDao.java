package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.model.entity.Layer;

public interface LayerDao extends ReadWriteDao<Layer, Long> {
  List<Layer> getAllParents(Long id);

  Optional<Layer> findStableChildById(Long layerId);
}
