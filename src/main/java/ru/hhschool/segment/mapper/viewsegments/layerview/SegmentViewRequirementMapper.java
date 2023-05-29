package ru.hhschool.segment.mapper.viewsegments.layerview;


import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;


public class SegmentViewRequirementMapper {
  public static SegmentViewRequirementDto toDtoForSelectedSegmentViewPage(QuestionRequiredLink questionRequiredLink,
                                                                          Boolean requiredChanged,
                                                                          Boolean isNew){
    SegmentViewRequirementDto segmentViewRequirementDto = new SegmentViewRequirementDto();
    segmentViewRequirementDto.setId(questionRequiredLink.getQuestion().getId());
    segmentViewRequirementDto.setTitle(questionRequiredLink.getQuestion().getTitle());
    segmentViewRequirementDto.setRequired(questionRequiredLink.isQuestionRequired());
    segmentViewRequirementDto.setChanged(requiredChanged);
    segmentViewRequirementDto.setNew(isNew);
    return segmentViewRequirementDto;
  }
}
