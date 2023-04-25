package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class SegmentViewQuestionMapper {
  public static SegmentViewQuestionDto toDtoForSelectedSegmentViewPage(Question question, Boolean required, Boolean requiredChanged, Boolean questionChanged, List<SegmentViewEntryPointDto> entryPoints){
    SegmentViewQuestionDto segmentViewQuestionDto = new SegmentViewQuestionDto(
        question.getId(),
        required,
        requiredChanged,
        questionChanged,
        question.getTitle(),
        entryPoints
    );
    return segmentViewQuestionDto;
  }
}
