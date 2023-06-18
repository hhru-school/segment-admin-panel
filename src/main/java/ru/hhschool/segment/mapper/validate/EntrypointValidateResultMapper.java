package ru.hhschool.segment.mapper.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.validate.EntrypointValidateResultDto;
import ru.hhschool.segment.model.dto.createlayer.validate.ScreenValidateResultDto;

import java.util.List;

public class EntrypointValidateResultMapper {
  public static EntrypointValidateResultDto toDto(InfoLayerEntryPointDto entrypoint, Integer position, List<ScreenValidateResultDto> screenValidateResultDtos) {
    EntrypointValidateResultDto entrypointValidateResultDto = new EntrypointValidateResultDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        position,
        screenValidateResultDtos
    );
    return entrypointValidateResultDto;
  }
}
