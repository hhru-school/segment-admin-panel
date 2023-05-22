package ru.hhschool.segment.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentMapper {
  public static SegmentDto segmentToDto(Segment segment, List<RoleDto> roleList) {
    SegmentDto segmentDto = new SegmentDto(
        segment.getId(),
        segment.getParentSegment() == null ? null : SegmentViewMapper.segmentToSegmentViewDto(segment.getParentSegment()),
        segment.getCreateTime(),
        segment.getTitle(),
        segment.getDescription(),
        roleList,
        segment.getTagList()
    );

    return segmentDto;
  }

  public static Segment dtoToSegment(SegmentCreateDto segmentCreateDto, Optional<Segment> parentSegment) {
    List<Long> rolesIdList =
        segmentCreateDto.getRoles()
            .stream()
            .map(RoleDto::getId)
            .toList();

    return new Segment(
        parentSegment.orElse(null),
        LocalDateTime.now().withNano(0),
        segmentCreateDto.getTitle(),
        segmentCreateDto.getDescription(),
        rolesIdList,
        segmentCreateDto.getTags()
    );
  }
}
