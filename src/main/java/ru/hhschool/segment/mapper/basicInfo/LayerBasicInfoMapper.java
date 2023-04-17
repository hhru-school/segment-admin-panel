package ru.hhschool.segment.mapper.basicInfo;

import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStatus;

import java.util.Collection;

public class LayerBasicInfoMapper {
  public static LayerBasicInfoDto toDtoForBasicInfoPage(Layer entity, Collection<Layer> parents) {
    LayerBasicInfoDto layerBasicInfoDto = new LayerBasicInfoDto();
    layerBasicInfoDto.setId(entity.getId());
    layerBasicInfoDto.setTitle(entity.getTitle());
    layerBasicInfoDto.setDescription(entity.getDescription());
    layerBasicInfoDto.setCreateTime(entity.getCreateTime());
    if (entity.isStable()) {
      layerBasicInfoDto.setLayerStatus(LayerStatus.STABLE);
    } else if (entity.isArchive()) {
      layerBasicInfoDto.setLayerStatus(LayerStatus.ARCHIVED);
    } else {
      layerBasicInfoDto.setLayerStatus(LayerStatus.EXPERIMENTAL);
    }
    layerBasicInfoDto.setParentLayersList(LayerMapper.toDtoListForBasicPage(parents));
    return layerBasicInfoDto;
  }
}
