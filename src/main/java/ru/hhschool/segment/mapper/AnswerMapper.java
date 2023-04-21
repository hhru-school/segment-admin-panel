package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.entity.Answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerMapper {
  public static AnswerDto toDto(Answer entity) {
    AnswerDto answerDto = new AnswerDto();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    return answerDto;
  }

  public static List<AnswerDto> toAnswerDtoListById(List<Long> listId) {
    List<AnswerDto> answerDtoList = new ArrayList<>();
    listId.stream().forEach(aLong -> {

    });
    return answerDtoList;
  }
}
