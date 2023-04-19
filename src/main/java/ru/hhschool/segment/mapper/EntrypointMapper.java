package ru.hhschool.segment.mapper;

import java.util.List;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.entity.Entrypoint;

public class EntrypointMapper {
  public static EntrypointDto entrypointToDto(Entrypoint entrypoint) {
    EntrypointDto entrypointDto = new EntrypointDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        entrypoint.getDescription(),
        entrypoint.getType(),
        entrypoint.getLayerId()
    );

    return entrypointDto;
  }

  public static List<EntrypointDto> entrypointListToDtoList(List<Entrypoint> entrypointList) {
    return entrypointList
        .stream()
        .map(EntrypointMapper::entrypointToDto)
        .toList();
  }
}
