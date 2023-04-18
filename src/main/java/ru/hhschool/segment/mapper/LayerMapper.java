package ru.hhschool.segment.mapper;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStatus;

public class LayerMapper {
  public static LayerDto toDtoForMainPage(Layer entity) {
    LayerDto layerDto = new LayerDto();
    layerDto.setId(entity.getId());
    layerDto.setTitle(entity.getTitle());
    layerDto.setCreateTime(entity.getCreateTime());
    if (entity.isStable()) {
      layerDto.setLayerStatus(LayerStatus.STABLE);
    } else if (entity.isArchive()) {
      layerDto.setLayerStatus(LayerStatus.ARCHIVED);
    } else {
      layerDto.setLayerStatus(LayerStatus.EXPERIMENTAL);
    }
    return layerDto;
  }

  public static List<LayerDto> toDtoListForMainPage(Collection<Layer> entityCollection) {

    return entityCollection
        .stream()
        .map(LayerMapper::toDtoForMainPage)
        .sorted(Comparator.comparing(LayerDto::getGroupOfLayerStatus).thenComparing(LayerDto::getId, Comparator.reverseOrder()))
        .toList();
  }

  public static List<LayerDto> toDtoListForBasicPage(Collection<Layer> entityCollection) {

    return entityCollection
        .stream()
        .map(LayerMapper::toDtoForMainPage)
        .sorted(Comparator.comparing(LayerDto::getId, Comparator.reverseOrder()))
        .toList();
  }
}