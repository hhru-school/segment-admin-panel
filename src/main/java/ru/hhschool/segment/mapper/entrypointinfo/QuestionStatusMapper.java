package ru.hhschool.segment.mapper.entrypointinfo;

import ru.hhschool.segment.model.dto.entrypointinfo.QuestionStatusDto;
import ru.hhschool.segment.model.entity.Question;

public class QuestionStatusMapper {
  public static QuestionStatusDto questionToQuestionStatusDto(Question question) {
    QuestionStatusDto questionChangeDto = new QuestionStatusDto();

    questionChangeDto.setTitle(question.getTitle());

    return questionChangeDto;
  }
}
