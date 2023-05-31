package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.enums.ScreenType;

public class SegmentViewQuestionMapper {
  public static SegmentViewQuestionDto toDtoForSelectedSegmentViewPage(Question question,
                                                                       Boolean isNew,
                                                                       ScreenQuestionLink link){
    SegmentViewQuestionDto segmentViewQuestionDto = new SegmentViewQuestionDto();
    segmentViewQuestionDto.setId(question.getId());
    segmentViewQuestionDto.setTitle(question.getTitle());
    segmentViewQuestionDto.setNew(isNew);
    segmentViewQuestionDto.setVisibility(link.getQuestionVisibility());
    if (link.getOldScreenQuestionLink() != null){
      if (link.getScreen().getType().equals(ScreenType.DYNAMIC)) {
        segmentViewQuestionDto.setOldPosition(link.getOldScreenQuestionLink().getQuestionPosition());
      }
      segmentViewQuestionDto.setOldVisibility(link.getOldScreenQuestionLink().getQuestionVisibility());
    }
    return segmentViewQuestionDto;
  }
}
