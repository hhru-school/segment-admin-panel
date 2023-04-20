package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.model.entity.Entrypoint;

public class EntrypointDaoImpl extends ReadWriteDaoImpl<Entrypoint, Long> implements EntrypointDao {
  @Override
  public List<Entrypoint> findAll(Long layerId) {
    List<Entrypoint> entrypointList = em.createQuery(
            "SELECT e FROM Entrypoint e WHERE e.layerId = :layerId", Entrypoint.class)
        .setParameter("layerId", layerId)
        .getResultList();

    return entrypointList;
  }
}
