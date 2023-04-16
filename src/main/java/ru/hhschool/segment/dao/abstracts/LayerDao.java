package ru.hhschool.segment.dao.abstracts;

import java.util.Optional;
import ru.hhschool.segment.model.entity.Layer;

public interface LayerDao extends ReadWriteDao<Layer, Long> {
  Optional<Layer> findByIdFetchEager(Long layerId);
}
