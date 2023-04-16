package ru.hhschool.segment.service;

import java.util.Optional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;

public class LayerService {
  private final LayerDao layerDao;
  private final Logger LOGGER = LoggerFactory.getLogger(LayerService.class);

  @Inject
  public LayerService(LayerDao layerDao) {
    this.layerDao = layerDao;
  }

  public LayerChangeDto getLayerChanges(Long layerId) {
    Optional<Layer> layer = layerDao.findByIdFetchEager(layerId);
    if (layer.isEmpty()) {
      LOGGER.error("LayerId: {} not found.", layerId);
      return null;
    }

    LayerChangeDto layerChangeDto = MapperLayerChange.layerChangeToDto(layer.get());
    return layerChangeDto;
  }
}
