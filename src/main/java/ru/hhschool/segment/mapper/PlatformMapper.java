package ru.hhschool.segment.mapper;

import java.util.List;
import ru.hhschool.segment.model.dto.platform.PlatformDto;
import ru.hhschool.segment.model.entity.Platform;

public class PlatformMapper {
  public static PlatformDto platformToDto(Platform platform) {
    PlatformDto platformDto = new PlatformDto(
        platform.getId(),
        platform.getPlatform().toString(),
        platform.getApplicationVersion()
    );
    return platformDto;
  }

  public static List<PlatformDto> platformListToDtoList(List<Platform> platformList) {
    if (platformList == null) {
      return List.of();
    }
    return platformList.stream()
        .map(PlatformMapper::platformToDto)
        .toList();
  }
}
