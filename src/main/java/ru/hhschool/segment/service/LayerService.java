package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;

public class LayerService {
  private final LayerDao layerDao;

  @Inject
  public LayerService(LayerDao layerDao) {
    this.layerDao = layerDao;
  }

  public List<LayerDto> getLayerDtoListForMainPage() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }

  @Transactional
  public Optional<LayerChangeDto> getLayerChanges(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      return Optional.empty();
    }

    return layer.map(MapperLayerChange::layerChangeToDto);
  }

  @Transactional
  public Optional<LayerBasicInfoDto> getLayerDtoForBasicInfoPage(Long id) {
    Optional<Layer> layer = layerDao.findById(id);
    if (layer.isEmpty()) {
      return Optional.empty();
    }
    LayerBasicInfoDto layerBasicInfoDto = LayerBasicInfoMapper.toDtoForBasicInfoPage(layer.get(), layerDao.getAllParents(id));
    return Optional.of(layerBasicInfoDto);
  }

//  private List<Layer> getAllParents(Layer layer) {
//    List<Layer> layerList = new ArrayList<>();
//    while (layer.getParent() != null) {
//      Layer parentLayer = layer.getParent();
//      layerList.add(parentLayer);
//      layer = parentLayer;
//    }
//    return layerList;
//  }
}
