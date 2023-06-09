package ru.hhschool.segment.mapper.viewsegments.layerview;


import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;
import ru.hhschool.segment.model.enums.StateType;


public class SegmentViewRequirementMapper {
  public static SegmentViewRequirementDto toDtoForSelectedSegmentViewPage(QuestionRequiredLink questionRequiredLink,
                                                                          Boolean requiredChanged,
                                                                          StateType state,
                                                                          Boolean isNew){
    SegmentViewRequirementDto segmentViewRequirementDto = new SegmentViewRequirementDto();
    segmentViewRequirementDto.setId(questionRequiredLink.getQuestion().getId());
    segmentViewRequirementDto.setTitle(questionRequiredLink.getQuestion().getTitle());
    segmentViewRequirementDto.setRequired(questionRequiredLink.isQuestionRequired());
    segmentViewRequirementDto.setIsChanged(requiredChanged);
    segmentViewRequirementDto.setState(state);
    segmentViewRequirementDto.setIsNew(isNew);
    return segmentViewRequirementDto;
  }
}
