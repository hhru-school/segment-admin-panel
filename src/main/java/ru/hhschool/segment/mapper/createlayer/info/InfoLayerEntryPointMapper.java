package ru.hhschool.segment.mapper.createlayer.info;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerScreenDto;
import ru.hhschool.segment.model.entity.Entrypoint;

import java.util.List;

public class InfoLayerEntryPointMapper {

  public static InfoLayerEntryPointDto toDtoForLayerCreation(Entrypoint entrypoint, List<InfoLayerScreenDto> screens){
    InfoLayerEntryPointDto infoLayerEntryPointDto = new InfoLayerEntryPointDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        entrypoint.getDescription(),
        screens
    );
    return infoLayerEntryPointDto;
  }
}
