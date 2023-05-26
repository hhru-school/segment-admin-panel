package ru.hhschool.segment.mapper.viewsegments;

import java.util.List;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;

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
