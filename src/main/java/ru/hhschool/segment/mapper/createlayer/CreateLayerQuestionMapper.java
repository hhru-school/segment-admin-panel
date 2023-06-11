package ru.hhschool.segment.mapper.createlayer;

import ru.hhschool.segment.model.dto.createlayer.CreateLayerQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.enums.ScreenType;

import java.util.Objects;

public class CreateLayerQuestionMapper {
  public static CreateLayerQuestionDto toDtoForLayerCreation(ScreenQuestionLink link){
    Question question = link.getQuestion();
    CreateLayerQuestionDto createLayerQuestionDto = new CreateLayerQuestionDto(
        question.getId(),
        link.getId(),
        question.getTitle(),
        link.getQuestionVisibility(),
        link.getQuestionPosition()
    );
    return createLayerQuestionDto;
  }
}
