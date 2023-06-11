package ru.hhschool.segment.mapper.createlayer;


import ru.hhschool.segment.model.dto.createlayer.CreateLayerRequirementDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;
import ru.hhschool.segment.model.enums.StateType;


public class CreateLayerRequirementMapper {
  public static CreateLayerRequirementDto toDtoForLayerCreation(QuestionRequiredLink link) {
    Question question = link.getQuestion();
    CreateLayerRequirementDto createLayerRequirementDto = new CreateLayerRequirementDto(
        question.getId(),
        link.getId(),
        question.getTitle(),
        link.isQuestionRequired()
    );
    return createLayerRequirementDto;
  }
}
