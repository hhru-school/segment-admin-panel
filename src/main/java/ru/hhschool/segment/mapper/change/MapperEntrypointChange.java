package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.entity.Entrypoint;

public class MapperEntrypointChange {
  public static EntrypointChangeDto entrypointChangeToDto(Entrypoint entrypoint) {
    EntrypointChangeDto entrypointChangeDto = new EntrypointChangeDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        entrypoint.getDescription()
    );

    return entrypointChangeDto;
  }

  public static List<EntrypointChangeDto> entrypointChangeListToDtoList(List<Entrypoint> entrypointList) {
    if (entrypointList == null) {
      return List.of();
    }
    return entrypointList
        .stream()
        .map(MapperEntrypointChange::entrypointChangeToDto)
        .toList();
  }
}
