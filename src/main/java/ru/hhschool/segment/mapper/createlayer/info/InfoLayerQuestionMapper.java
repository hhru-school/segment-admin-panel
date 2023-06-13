package ru.hhschool.segment.mapper.createlayer.info;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;

public class InfoLayerQuestionMapper {
  public static InfoLayerQuestionDto toDtoForLayerCreation(ScreenQuestionLink link){
    Question question = link.getQuestion();
    InfoLayerQuestionDto infoLayerQuestionDto = new InfoLayerQuestionDto(
        question.getId(),
        link.getId(),
        question.getTitle(),
        link.getQuestionVisibility(),
        link.getQuestionPosition()
    );
    return infoLayerQuestionDto;
  }
}
