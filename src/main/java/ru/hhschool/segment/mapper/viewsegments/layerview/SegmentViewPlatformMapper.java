package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewPlatformDto;
import ru.hhschool.segment.model.entity.Platform;

import java.util.Comparator;
import java.util.List;

public class SegmentViewPlatformMapper {

  private static SegmentViewPlatformDto toSegmentViewPlatformDto(Platform platform){
    SegmentViewPlatformDto segmentViewApplicationDto = new SegmentViewPlatformDto(
        platform.getPlatform(),
        platform.getApplicationVersion()
    );
    return segmentViewApplicationDto;
  }

  public static List<SegmentViewPlatformDto> toDtoForSelectedSegmentViewPage(List<Platform> platforms){
    return platforms.stream()
        .map(platform -> toSegmentViewPlatformDto(platform))
        .sorted(Comparator.comparing(SegmentViewPlatformDto::getPlatform))
        .toList();
  }
}
