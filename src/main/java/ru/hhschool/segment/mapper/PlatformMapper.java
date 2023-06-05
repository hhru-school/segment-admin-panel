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
package ru.hhschool.segment.mapper;

import java.util.Comparator;
import java.util.List;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.entity.Platform;

public class PlatformMapper {

  private static PlatformDto toSegmentViewPlatformDto(Platform platform) {
    PlatformDto segmentViewApplicationDto = new PlatformDto(
        platform.getPlatform(),
        platform.getApplicationVersion()
    );
    return segmentViewApplicationDto;
  }

  public static List<PlatformDto> toDtoForSelectedSegmentViewPage(List<Platform> platforms) {
    return platforms.stream()
        .map(platform -> toSegmentViewPlatformDto(platform))
        .sorted(Comparator.comparing(PlatformDto::getPlatform))
        .toList();
  }
}
