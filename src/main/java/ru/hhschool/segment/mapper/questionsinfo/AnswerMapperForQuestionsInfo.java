package ru.hhschool.segment.mapper.questionsinfo;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.questioninfo.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Answer;

public class AnswerMapperForQuestionsInfo {
  public static AnswerDtoForQuestionsInfo toDto(Answer entity) {
    AnswerDtoForQuestionsInfo answerDto = new AnswerDtoForQuestionsInfo();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    return answerDto;
  }
}
