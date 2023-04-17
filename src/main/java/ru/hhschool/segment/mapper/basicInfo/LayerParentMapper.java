package ru.hhschool.segment.mapper.basicInfo;

import ru.hhschool.segment.model.dto.basicInfo.BasicLayerStatus;
import ru.hhschool.segment.model.dto.basicInfo.LayerParentDto;
import ru.hhschool.segment.model.entity.Layer;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class LayerParentMapper {
  private static LayerParentDto toDtoForBasicPage(Layer entity) {
    LayerParentDto layerParentDto = new LayerParentDto();
    layerParentDto.setId(entity.getId());
    layerParentDto.setTitle(entity.getTitle());
    layerParentDto.setCreateTime(entity.getCreateTime());
    if (entity.isStable()) {
      layerParentDto.setLayerStatus(BasicLayerStatus.STABLE);
    } else if (entity.isArchive()) {
      layerParentDto.setLayerStatus(BasicLayerStatus.EXPERIMENTAL);
    } else {
      layerParentDto.setLayerStatus(BasicLayerStatus.ARCHIVED);
    }
    return layerParentDto;
  }

  public static List<LayerParentDto> toDtoListForBasicPage(Collection<Layer> entityCollection) {
    return entityCollection
        .stream()
        .map(LayerParentMapper::toDtoForBasicPage)
        .sorted(Comparator.comparing(LayerParentDto::getId, Comparator.reverseOrder()))
        .toList();
  }
}
