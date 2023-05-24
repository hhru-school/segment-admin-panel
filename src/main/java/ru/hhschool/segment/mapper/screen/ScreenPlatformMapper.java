package ru.hhschool.segment.mapper.screen;

import java.util.List;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformDto;
import ru.hhschool.segment.model.entity.Platform;

public class ScreenPlatformMapper {
  public static ScreenPlatformDto platformToDto(Platform platform) {
    ScreenPlatformDto screenPlatformDto = new ScreenPlatformDto(
        platform.getId(),
        platform.getPlatform().toString(),
        platform.getApplicationVersion()
    );
    return screenPlatformDto;
  }

  public static List<ScreenPlatformDto> platformListToDtoList(List<Platform> platformList) {
    return platformList.stream()
        .map(ScreenPlatformMapper::platformToDto)
        .toList();
  }
}
