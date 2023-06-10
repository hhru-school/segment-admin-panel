package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStateType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
  public List<Layer> findAll(List<LayerStateType> layerStatusList) {
    List<Layer> layerList = em.createQuery("""
            SELECT l
             FROM Layer l
             WHERE l.state IN :layerStatusList
             ORDER BY l.createTime DESC 
            """)
        .setParameter("layerStatusList", layerStatusList)
        .getResultList();
    return layerList;
  }

  @Override
  public Layer findLastStableLayer() {
    return em.createQuery("""
            SELECT l
             FROM Layer l
             WHERE l.state IN :layerStatus
             ORDER BY l.stabledTime DESC 
            """, Layer.class)
        .setParameter("layerStatus", LayerStateType.STABLE)
        .setMaxResults(1)
        .getSingleResult();
  }

}
