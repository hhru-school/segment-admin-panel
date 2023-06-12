package ru.hhschool.segment.mapper.createlayer.info;


import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerRequirementDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;


public class InfoLayerRequirementMapper {
  public static InfoLayerRequirementDto toDtoForLayerCreation(QuestionRequiredLink link) {
    Question question = link.getQuestion();
    InfoLayerRequirementDto infoLayerRequirementDto = new InfoLayerRequirementDto(
        question.getId(),
        link.getId(),
        question.getTitle(),
        link.isQuestionRequired()
    );
    return infoLayerRequirementDto;
  }
}
