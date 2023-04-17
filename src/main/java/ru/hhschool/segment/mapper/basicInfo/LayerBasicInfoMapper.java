package ru.hhschool.segment.mapper.basicInfo;

import ru.hhschool.segment.model.dto.basicInfo.BasicLayerStatus;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.entity.Layer;

import java.util.Collection;

public class LayerBasicInfoMapper {
  public static LayerBasicInfoDto toDtoForBasicInfoPage(Layer entity, Collection<Layer> parents) {
    LayerBasicInfoDto layerBasicInfoDto = new LayerBasicInfoDto();
    layerBasicInfoDto.setId(entity.getId());
    layerBasicInfoDto.setTitle(entity.getTitle());
    layerBasicInfoDto.setDescription(entity.getDescription());
    layerBasicInfoDto.setCreateTime(entity.getCreateTime());
    if (entity.isStable()) {
      layerBasicInfoDto.setLayerStatus(BasicLayerStatus.STABLE);
    } else if (entity.isArchive()) {
      layerBasicInfoDto.setLayerStatus(BasicLayerStatus.ARCHIVED);
    } else {
      layerBasicInfoDto.setLayerStatus(BasicLayerStatus.EXPERIMENTAL);
    }
    layerBasicInfoDto.setParentLayersList(LayerParentMapper.toDtoListForBasicPage(parents));
    return layerBasicInfoDto;
  }
}
