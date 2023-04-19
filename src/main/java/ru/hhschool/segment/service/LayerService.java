package ru.hhschool.segment.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
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
  public Optional<LayerChangeDto> mergeLayerWithParent(Long layerId) throws NotFoundException, IllegalStateException {
    Optional<Layer> layerOptional = layerDao.findById(layerId);

    Layer layer = layerOptional
        .orElseThrow(() -> new NotFoundException("Not found."));

    if (layer.isStable()) {
      throw new IllegalStateException("Already stable.");
    }

    /**
     * если parent == null это базовый слой, записываем его сразу.
     */
    if (layer.getParent() == null) {
      layer.setStable(true);
      layerDao.update(layer);

      return Optional.of(LayerChangeMapper.layerChangeToDto(layer, ConflictStatus.NONE));
    }

    Layer layerParent = layerDao.findById(layer.getParent().getId())
        .orElseThrow(() -> new IllegalStateException("Error. Parent not found."));

    if (layerId.equals(layerParent.getId())) {
      throw new IllegalStateException("Error recursion. LayerId equals ParentId.");
    }

    Optional<LayerChangeDto> layerChangeDto = Optional.empty();
    List<Layer> layerStableChildList = List.of();

    do {
      layerStableChildList = layerDao.findStableChildById(layerParent.getId());
      /**
       * Детей в состоянии stable не обнаружено, сохраняем.
       */
      if (layerStableChildList.size() == 0) {
        /**
         * Если это прямой родитель сохраняем, иначе.
         * Это не прямой родитель, а ребенок, относительно него ищем конфликты.
         */
        if (layer.getParent().getId().equals(layerParent.getId())) {
          break;
        }
      }
      if (layerStableChildList.size() > 1) {
        throw new IllegalStateException("Error. More that one Stable child.");
      }
      /**
       * у наследника нашлись еще stable наследники.
       */
      if (layerStableChildList.size() > 0) {
        layerParent = layerStableChildList.get(0);

        /**
         * Сравниваем с каждым наследником. Независимо есть stable наследники у него или нет.
         */
        layerChangeDto = LayerConflictChecker.getConflict(layer, layerParent);
        if (layerChangeDto.isPresent() && layerChangeDto.get().isConflict()) {
          return layerChangeDto;
        }
      }
      /**
       * Если у нас еще найдены stable наследники.
       * И при этом не обнаружено конфликтов с прошлым наследником.
       */
    } while (layerStableChildList.size() > 0 && layerChangeDto.isPresent());

    /**
     * Были проходы по stable наследникам
     * переписывает родителя на layerParent.id
     */
    if (layerChangeDto.isPresent()) {
      layer.setParent(layerParent);
    }
    layer.setStable(true);
    layerDao.update(layer);
    return Optional.of(LayerChangeMapper.layerChangeToDto(layer, ConflictStatus.NONE));
  }

}
