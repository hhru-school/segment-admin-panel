package ru.hhschool.segment.mapper.createlayer;

import ru.hhschool.segment.model.dto.createlayer.CreateLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerScreenDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Entrypoint;

import java.util.List;

public class CreateLayerEntryPointMapper {

  public static CreateLayerEntryPointDto toDtoForLayerCreation(Entrypoint entrypoint, List<CreateLayerScreenDto> screens){
    CreateLayerEntryPointDto createLayerEntryPointDto = new CreateLayerEntryPointDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        entrypoint.getDescription(),
        screens
    );
    return createLayerEntryPointDto;
  }
}
