package ru.hhschool.segment.mappers.impl;

import org.springframework.stereotype.Component;
import ru.hhschool.segment.mappers.abstracts.BaseMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.entity.Layer;

import java.util.Collection;
import java.util.List;

@Component
public class LayerMapper implements BaseMapper<Layer, LayerDto> {
  @Override
  public Layer toEntity(LayerDto dto) {
    return null;
  }

  @Override
  public LayerDto toDto(Layer entity) {
    return null;
  }

  @Override
  public List<LayerDto> toDtoList(Collection<Layer> entityList) {
    return null;
  }

  @Override
  public List<Layer> toEntityList(Collection<LayerDto> dtoList) {
    return null;
  }
}
