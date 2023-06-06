package ru.hhschool.segment.mapper.viewsegments;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentViewMapper {

  public static SegmentViewDto segmentToSegmentViewDto(Segment segment) {
    SegmentViewDto segmentViewDto = new SegmentViewDto(
        segment.getId(),
        segment.getTitle()
    );
    return segmentViewDto;
  }

  public static List<SegmentViewDto> toDtoListForAllSegmentsPage(Collection<Segment> segments) {
    return segments.stream()
        .map(SegmentViewMapper::segmentToSegmentViewDto)
        .sorted(Comparator.comparing(SegmentViewDto::getTitle))
        .toList();
  }
}
