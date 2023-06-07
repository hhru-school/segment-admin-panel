package ru.hhschool.segment.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStateType;

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
  public List<Layer> findAll(List<LayerStateType> layerStateTypes) {
    if (layerStateTypes == null || layerStateTypes.size() == 0) {
      return findAll();
    }
    List<Layer> layerList = em.createQuery("""
            SELECT l
             FROM Layer l
             WHERE l.state IN :layerStatusList
             ORDER BY l.createTime DESC 
            """)
        .setParameter("layerStatusList", layerStateTypes)
        .getResultList();
    return layerList;
  }

}
