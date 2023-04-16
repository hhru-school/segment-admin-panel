package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Segment;

public class MapperSegmentChange {
  public static SegmentChangeDto segmentChangeToDto(Segment segment) {
    SegmentChangeDto segmentChangeDto = new SegmentChangeDto(
        segment.getId(),
        segment.getTitle(),
        segment.getDescription(),
        segment.isArchived()
    );

    return segmentChangeDto;
  }

  public static List<SegmentChangeDto> segmentChangeListToDtoList(List<Segment> segmentList) {
    return segmentList
        .stream()
        .map(MapperSegmentChange::segmentChangeToDto)
        .toList();
  }
}
