package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.entity.Question;

public class QuestionMapper {
  public static QuestionDto toDto(Question entity) {
    QuestionDto questionDto = new QuestionDto();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setAnswerDtoList(AnswerMapper.toAnswerDtoListById(entity.getPossibleAnswerIdList()));
    return questionDto;
  }
}
