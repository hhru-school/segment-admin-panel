package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.LayerStatus;

public class QuestionMapper {
  public static LayerDto toDto(Question entity) {
    QuestionDto questionDto = new QuestionDto();
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
}
