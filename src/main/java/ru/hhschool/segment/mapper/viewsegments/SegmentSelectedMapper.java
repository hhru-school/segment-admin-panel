package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Segment;

import java.util.List;

public class SegmentSelectedMapper {
  public static SegmentSelectedDto toDtoForSelectedSegmentViewPage(Segment segment, List<SegmentViewQuestionDto> questions){
    SegmentSelectedDto segmentSelectedDto = new SegmentSelectedDto(
        segment.getId(),
        segment.getTitle(),
        questions
    );
    return segmentSelectedDto;
  }
}
