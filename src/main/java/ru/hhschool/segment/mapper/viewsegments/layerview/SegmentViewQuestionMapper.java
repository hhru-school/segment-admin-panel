package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.enums.ScreenType;

import java.util.Objects;

public class SegmentViewQuestionMapper {
  public static SegmentViewQuestionDto toDtoForSelectedSegmentViewPage(Question question,
                                                                       Long viewLayerId,
                                                                       Boolean isNew,
                                                                       ScreenQuestionLink link){
    SegmentViewQuestionDto segmentViewQuestionDto = new SegmentViewQuestionDto();
    segmentViewQuestionDto.setId(question.getId());
    segmentViewQuestionDto.setTitle(question.getTitle());
    segmentViewQuestionDto.setIsNew(isNew);
    segmentViewQuestionDto.setVisibility(link.getQuestionVisibility());
    if (link.getLayer().getId().equals(viewLayerId) && link.getOldScreenQuestionLink() != null){
      boolean positionChanged = !Objects.equals(link.getQuestionPosition(), link.getOldScreenQuestionLink().getQuestionPosition());
      boolean visibilityChanged = !Objects.equals(link.getQuestionVisibility(), link.getOldScreenQuestionLink().getQuestionVisibility());
      if (link.getScreen().getType().equals(ScreenType.DYNAMIC) && positionChanged) {
        segmentViewQuestionDto.setOldPosition(link.getOldScreenQuestionLink().getQuestionPosition());
      }
      if (visibilityChanged) {
        segmentViewQuestionDto.setOldVisibility(link.getOldScreenQuestionLink().getQuestionVisibility());
      }
    }
    return segmentViewQuestionDto;
  }
}
