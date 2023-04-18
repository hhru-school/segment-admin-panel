package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Layer;
import java.util.List;
import java.util.Optional;

public interface LayerDao extends ReadWriteDao<Layer, Long> {
  Optional<Layer> findByIdFetchEager(Long layerId);
  List<Layer> getAllParents(Long id);
}
