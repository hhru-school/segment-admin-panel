package ru.hhschool.segment.mapper.change;

import java.util.List;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentChangeMapper {
  public static SegmentChangeDto segmentChangeToDto(Segment segment) {
    Long parentId = segment.getParent() == null ? null : segment.getParent().getId();

    SegmentChangeDto segmentChangeDto = new SegmentChangeDto(
        segment.getId(),
        parentId,
        segment.getTitle(),
        segment.getDescription(),
        segment.isArchived()
    );

    return segmentChangeDto;
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
