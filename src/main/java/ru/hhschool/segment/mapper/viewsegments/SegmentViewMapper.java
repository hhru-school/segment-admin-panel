package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.Segment;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class SegmentViewMapper {

  private static SegmentViewDto toDtoForAllSegmentsPage(Segment segment){
    SegmentViewDto segmentViewDto = new SegmentViewDto(
        segment.getId(),
        segment.getTitle()
    );
    return segmentViewDto;
  }
  public static List<SegmentViewDto> toDtoListForAllSegmentsPage(Collection<Segment> segments){
    return segments.stream()
        .map(SegmentViewMapper::toDtoForAllSegmentsPage)
        .sorted(Comparator.comparing(SegmentViewDto::getTitle))
        .toList();
  }
}
