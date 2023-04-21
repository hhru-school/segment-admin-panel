package ru.hhschool.segment.mapper.questionsinfo;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapperForQuestionsInfo {
  public static QuestionDto toDto(Question entity, List<AnswerDto> answerDtoList) {
    QuestionDto questionDto = new QuestionDto();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setAnswerDtoList(answerDtoList);
    return questionDto;
  }
}
