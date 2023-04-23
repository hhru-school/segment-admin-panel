package ru.hhschool.segment.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Layer;

public class LayerDaoImpl extends ReadWriteDaoImpl<Layer, Long> implements LayerDao {
  @Override
  public List<Layer> getAllParents(Long id) {
    Layer basicLayer = em.find(Layer.class, id);
    if (basicLayer == null) {
      return Collections.EMPTY_LIST;
    }
    List<Layer> layerList = new ArrayList<>();
    while (basicLayer.getParent() != null) {
      Layer parentLayer = basicLayer.getParent();
      layerList.add(parentLayer);
      basicLayer = parentLayer;
    }
    return layerList;
  }

  @Override
  public Optional<Layer> findStableChildById(Long layerId) {
    Layer layerStableChild = em.createQuery("""
              SELECT l
              FROM Layer l
               WHERE l.parent.id = :layerId
                AND l.stable = TRUE
            """, Layer.class)
        .setParameter("layerId", layerId)
        .getSingleResult();

    return Optional.of(layerStableChild);
  }

}
