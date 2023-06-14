package ru.hhschool.segment.mapper.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.validate.QuestionValidateResultDto;
import ru.hhschool.segment.model.dto.createlayer.validate.ScreenValidateResultDto;

import java.util.List;

public class QuestionValidateResultMapper {
  public static QuestionValidateResultDto toDto(InfoLayerEntryPointDto entrypoint, InfoLayerQuestionDto question, List<ScreenValidateResultDto> screenValidateResultDtos) {
    QuestionValidateResultDto questionValidateResultDto = new QuestionValidateResultDto(
        entrypoint.getEntripointId(),
        entrypoint.getTitle(),
        question.getQuestionId(),
        question.getTitle(),
        screenValidateResultDtos
    );
    return questionValidateResultDto;
  }
}
