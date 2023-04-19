package ru.hhschool.segment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;

public class EntrypointService {
  private final EntrypointDao entrypointDao;
  private final LayerDao layerDao;

  @Inject
  public EntrypointService(EntrypointDao entrypointDao, LayerDao layerDao) {
    this.entrypointDao = entrypointDao;
    this.layerDao = layerDao;
  }

  public List<EntrypointDto> getAllEntrypoint() {
    return EntrypointMapper.entrypointListToDtoList(entrypointDao.findAll());
  }

  @Transactional
  public Set<EntrypointDto> getAllEntrypointByLayerId(Long layerId) {
    Optional<Layer> layer = Optional.ofNullable(layerDao.findById(layerId));
    if (layer.isEmpty()) {
      return Set.of();
    }
    List<Layer> layerParentList = layerDao.getAllParents(layerId);

    Set<EntrypointDto> entrypointDtoSet = new HashSet<>();
    for (int i = layerParentList.size() - 1; i >= 0; i--) {
      saveEntrypointFromLayerToSet(entrypointDtoSet, layerParentList.get(i).getId());
    }
    saveEntrypointFromLayerToSet(entrypointDtoSet, layerId);

    return entrypointDtoSet;
  }

  /**
   * Собираем элементы по слоям Каждый слой layerId накладывается на другой, затирая старое значение или создавая новое.
   * Важно! У entrypointDto обязательно определен Equals и HashCode на Title + Description.
   *
   * @param entrypointDtoSet
   * @param layerId
   */
  private void saveEntrypointFromLayerToSet(Set<EntrypointDto> entrypointDtoSet, Long layerId) {
    List<Entrypoint> entrypointList = entrypointDao.findAllByLayerId(layerId);
    List<EntrypointDto> entrypointDtoList = EntrypointMapper.entrypointListToDtoList(entrypointList);
    for (EntrypointDto entrypointDto : entrypointDtoList) {
      if (entrypointDtoSet.contains(entrypointDto)) {
        entrypointDtoSet.remove(entrypointDto);
      }
      entrypointDtoSet.add(entrypointDto);
    }
  }
}
