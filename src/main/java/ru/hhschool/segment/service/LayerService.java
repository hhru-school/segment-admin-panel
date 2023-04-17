package ru.hhschool.segment.service;

import java.util.Optional;
import javax.inject.Inject;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;

public class LayerService {
  private final LayerDao layerDao;

  @Inject
  public LayerService(LayerDao layerDao) {
    this.layerDao = layerDao;
  }

  public Optional<LayerChangeDto> getLayerChanges(Long layerId) {
    Optional<Layer> layer = layerDao.findByIdFetchEager(layerId);
    if (layer.isEmpty()) {
      return Optional.empty();
    }

    LayerChangeDto layerChangeDto = MapperLayerChange.layerChangeToDto(layer.get());
    return Optional.of(layerChangeDto);
  }
}
