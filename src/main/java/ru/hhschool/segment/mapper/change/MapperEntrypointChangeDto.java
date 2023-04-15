package ru.hhschool.segment.mapper.change;

import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.entity.Entrypoint;

import java.util.List;

public class MapperEntrypointChangeDto {
    public static EntrypointChangeDto entrypointChangeToDto(Entrypoint entrypoint) {
        EntrypointChangeDto entrypointChangeDto = new EntrypointChangeDto(
                entrypoint.getId(),
                entrypoint.getTitle(),
                entrypoint.getDescription()
        );

        return entrypointChangeDto;
    }

    public static List<EntrypointChangeDto> entrypointChangeListToDtoList(List<Entrypoint> entrypointList) {
        return entrypointList
                .stream()
                .map(MapperEntrypointChangeDto::entrypointChangeToDto)
                .toList();
    }
}
