package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Layer;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayerDaoImpl extends ReadWriteDaoImpl<Layer, Long> implements LayerDao {
  @Override
  @Transactional
  public List<Layer> getAllParents(Long id) {
    List<Layer> layerList = new ArrayList<>();
    Layer basicLayer = em.find(Layer.class, id);
    if (basicLayer == null){
      return Collections.EMPTY_LIST;
    }
    layerList.add(basicLayer);
    while (basicLayer.getParent() != null){
      Layer parentLayer = basicLayer.getParent();
      layerList.add(parentLayer);
      basicLayer = parentLayer;
    }
    return layerList;
  }
}
