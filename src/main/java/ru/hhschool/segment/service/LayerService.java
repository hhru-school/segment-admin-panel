package ru.hhschool.segment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.basicInfo.LayerBasicInfoMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.entity.Layer;

import javax.inject.Inject;
import java.util.List;

public class LayerService {
  private final LayerDao layerDao;
  private final Logger LOGGER = LoggerFactory.getLogger(LayerService.class);

  @Inject
  public LayerService(LayerDao layerDao) {
    this.layerDao = layerDao;
  }

  public List<LayerDto> getLayerDtoListForMainPage() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }

  public LayerBasicInfoDto getLayerDtoForBasicInfoPage(Long id) {
    Layer layer = layerDao.findById(id);
    if (layer != null){
      LOGGER.error("LayerId: {} not found.", id);
      return LayerBasicInfoMapper.toDtoForBasicInfoPage(layer, layerDao.getAllParents(id));
    }
    return null;
  }
}
