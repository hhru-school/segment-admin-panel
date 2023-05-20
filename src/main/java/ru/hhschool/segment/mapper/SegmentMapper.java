package ru.hhschool.segment.mapper;

import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.SegmentDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentMapper {
  public static SegmentDto segmentToDto(Segment segment, List<RoleDto> roleList) {
    SegmentDto segmentDto = new SegmentDto(
        segment.getId(),
        segment.getParentSegment() == null ? null : segment.getParentSegment().getId(),
        segment.getCreateTime(),
        segment.getTitle(),
        segment.getDescription(),
        roleList,
        segment.getTagList()
    );

    return segmentDto;
  }
}
