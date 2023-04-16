package ru.hhschool.segment.mapper.change;

import java.util.ArrayList;
import java.util.List;
import ru.hhschool.segment.model.dto.change.AnswerChangeDto;
import ru.hhschool.segment.model.entity.Answer;

public class MapperAnswerChange {
  public static AnswerChangeDto answerChangeToDto(Answer answer) {
    AnswerChangeDto answerChangeDto = new AnswerChangeDto(
        answer.getId(),
        answer.getTitle(),
        answer.getAnswerType(),
        new ArrayList<>()
    );

    return answerChangeDto;
  }

  public static List<AnswerChangeDto> answerChangeListToDtoList(List<Answer> answerList) {
    return answerList
        .stream()
        .map(MapperAnswerChange::answerChangeToDto)
        .toList();
  }
}
