package ru.hhschool.segment.mapper.basicinfo;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.LayerStateType;
import ru.hhschool.segment.model.enums.LayerStatus;

public class LayerBasicInfoMapper {
  public static LayerBasicInfoDto toDtoForBasicInfoPage(Layer entity, Collection<Layer> parents) {
    LayerBasicInfoDto layerBasicInfoDto = new LayerBasicInfoDto();
    layerBasicInfoDto.setId(entity.getId());
    layerBasicInfoDto.setTitle(entity.getTitle());
    layerBasicInfoDto.setDescription(entity.getDescription());
    layerBasicInfoDto.setCreateTime(entity.getCreateTime());
    if (entity.getState().equals(LayerStateType.STABLE)) {
      layerBasicInfoDto.setLayerStatus(LayerStatus.STABLE);
    } else if (entity.getState().equals(LayerStateType.ARCHIVE)) {
      layerBasicInfoDto.setLayerStatus(LayerStatus.ARCHIVED);
    } else {
      layerBasicInfoDto.setLayerStatus(LayerStatus.EXPERIMENTAL);
    }
    layerBasicInfoDto.setParentLayersList(toDtoListForBasicPage(parents));
    return layerBasicInfoDto;
  }

  public static List<LayerDto> toDtoListForBasicPage(Collection<Layer> entityCollection) {

    return entityCollection
        .stream()
        .map(LayerMapper::toDtoForMainPage)
        .sorted(Comparator.comparing(LayerDto::getId, Comparator.reverseOrder()))
        .toList();
  }
}
