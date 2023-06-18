package ru.hhschool.segment.mapper.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.validate.QuestionValidateResultDto;
import ru.hhschool.segment.model.dto.createlayer.validate.ScreenValidateResultDto;

import java.util.List;

public class QuestionValidateResultMapper {
  public static QuestionValidateResultDto toDto(InfoLayerEntryPointDto entrypoint, InfoLayerQuestionDto question, List<ScreenValidateResultDto> screenValidateResultDtos) {
    QuestionValidateResultDto questionValidateResultDto = new QuestionValidateResultDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        question.getId(),
        question.getTitle(),
        screenValidateResultDtos
    );
    return questionValidateResultDto;
  }
  public static QuestionValidateResultDto toDto(InfoLayerQuestionDto question) {
    QuestionValidateResultDto questionValidateResultDto = new QuestionValidateResultDto(
        question.getId(),
        question.getTitle()
    );
    return questionValidateResultDto;
  }
}
