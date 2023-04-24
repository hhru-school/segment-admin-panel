package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public class SegmentViewEntryPointMapper {

  public static SegmentViewEntryPointDto toDtoForSelectedSegmentViewPage(QuestionActivatorLink link, Boolean hasChanged){
    SegmentViewEntryPointDto segmentViewEntryPointDto = new SegmentViewEntryPointDto(
        link.getEntrypoint().getTitle(),
        link.getQuestionVisibility(),
        hasChanged
    );
    return segmentViewEntryPointDto;
  }
}
