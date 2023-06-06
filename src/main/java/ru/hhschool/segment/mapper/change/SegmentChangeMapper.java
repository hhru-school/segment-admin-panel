package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentChangeMapper {
  public static SegmentChangeDto segmentChangeToDto(Segment segment) {
    return null;
  }

  public static List<SegmentChangeDto> segmentChangeListToDtoList(List<Segment> segmentList) {
    if (segmentList == null) {
      return List.of();
    }
    return segmentList
        .stream()
        .map(SegmentChangeMapper::segmentChangeToDto)
        .toList();
  }
}
