package ru.hhschool.segment.mapper.questionsinfo;

import ru.hhschool.segment.model.dto.questioninfo.AnswerDtoForQuestionsPage;
import ru.hhschool.segment.model.entity.Answer;

public class AnswerMapperForQuestionsInfo {
  public static AnswerDtoForQuestionsPage toDto(Answer entity) {
    AnswerDtoForQuestionsPage answerDto = new AnswerDtoForQuestionsPage();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    return answerDto;
  }
}
