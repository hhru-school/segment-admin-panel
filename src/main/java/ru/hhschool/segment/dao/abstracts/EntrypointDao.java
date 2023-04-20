package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Entrypoint;

public interface EntrypointDao extends ReadWriteDao<Entrypoint, Long> {
  List<Entrypoint> findAll(Long layerId);
}
