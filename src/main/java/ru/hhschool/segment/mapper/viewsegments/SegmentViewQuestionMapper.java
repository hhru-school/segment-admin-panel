package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class SegmentViewQuestionMapper {
  public static SegmentViewQuestionDto toDtoForSelectedSegmentViewPage(Question question, List<SegmentViewEntryPointDto> entryPoints){
    SegmentViewQuestionDto segmentViewQuestionDto = new SegmentViewQuestionDto(
        question.getId(),
        question.isRequired(),
        question.getTitle(),
        entryPoints
    );
    return segmentViewQuestionDto;
  }
}
