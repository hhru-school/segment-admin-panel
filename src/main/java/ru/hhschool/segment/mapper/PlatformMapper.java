package ru.hhschool.segment.mapper;

import java.util.Comparator;
import java.util.List;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.entity.Platform;

public class PlatformMapper {

  private static PlatformDto toDto(Platform platform) {
    PlatformDto platformDto = new PlatformDto(
        platform.getId(),
        platform.getPlatform(),
        platform.getApplicationVersion()
    );
    return platformDto;
  }

  public static List<PlatformDto> toDtoList(List<Platform> platforms) {
    return platforms.stream()
        .map(platform -> toDto(platform))
        .sorted(Comparator.comparing(PlatformDto::getPlatform))
        .toList();
  }
}
