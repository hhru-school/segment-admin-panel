package ru.hhschool.segment.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import org.hibernate.NonUniqueResultException;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.basicInfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.change.LayerChangeMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.ConflictStatus;
import ru.hhschool.segment.util.LayerConflictChecker;

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

    return Optional.of(LayerChangeMapper.layerChangeToDto(layer.get(), ConflictStatus.NONE));
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

  @Transactional
  public Optional<LayerChangeDto> joinLayer(Long layerId) throws NotFoundException, IllegalStateException {
    Optional<Layer> layerOptional = layerDao.findById(layerId);
    if (layerOptional.isEmpty()) {
      throw new NotFoundException("Not found.");
    }

    Layer layer = layerOptional.get();
    if (layer.isStable()) {
      throw new IllegalStateException("Already stable.");
    }

    if (layer.getParent() == null) {
      //TODO сразу делаем Save Stable
      return Optional.empty();
    }

    Layer layerStableChild = layer;
    Optional<LayerChangeDto> layerChangeDto = Optional.empty();
    do {
      if (layerId.equals(layer.getParent().getId())) {
        throw new IllegalStateException("LayerId equals ParentId ошибка в данных");
      }
      Optional<Layer> layerStableChildOptional;
      try {
        layerStableChildOptional = layerDao.findStableChildById(layer.getParent().getId());
      } catch (NonUniqueResultException e) {
        throw new IllegalStateException("More that one Stable child.");
      }
      //TODO перепроверить логику, выборки парентов, гдето ошибка, сохранение без поиска конфликтов..
      if (layerStableChildOptional.isEmpty()) {
        //TODO сразу делаем Save Stable
        return Optional.empty();
      }
      layerStableChild = layerStableChildOptional.get();

      layerChangeDto = LayerConflictChecker.getConflict(layer, layerStableChild);
      if (layerChangeDto.isPresent() && layerChangeDto.get().isConflict()) {
        return layerChangeDto;
      }

      if (layerChangeDto.isPresent()) {
        //TODO recursion joinNext()
        // while ^^
      }
      // TODO надо запомнить parentId и проверить нет ли над ним еще одного наследника!
    } while (layerChangeDto.isPresent());

    // TODO Save Stable + переписывает родителя на layerStableChild.id
    return Optional.of(LayerChangeMapper.layerChangeToDto(layerStableChild, ConflictStatus.NONE));
  }

}
