package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.entity.Platform;

import java.util.Comparator;
import java.util.List;

public class PlatformMapper {

  private static PlatformDto toSegmentViewPlatformDto(Platform platform){
    PlatformDto platformDto = new PlatformDto(
        platform.getId(),
        platform.getPlatform(),
        platform.getApplicationVersion()
    );
    return platformDto;
  }

  public static List<PlatformDto> toDtoForSelectedSegmentViewPage(List<Platform> platforms){
    return platforms.stream()
        .map(platform -> toSegmentViewPlatformDto(platform))
        .sorted(Comparator.comparing(PlatformDto::getPlatform))
        .toList();
  }
}
