package ru.hhschool.segment.mapper.change;

import java.util.ArrayList;
import java.util.List;
import ru.hhschool.segment.model.dto.change.AnswerChangeDto;
import ru.hhschool.segment.model.entity.Answer;

public class AnswerChangeMapper {
  public static AnswerChangeDto answerChangeToDto(Answer answer) {
    AnswerChangeDto answerChangeDto = new AnswerChangeDto(
        answer.getId(),
        answer.getTitle(),
        answer.getType(),
        new ArrayList<>()
    );

    return answerChangeDto;
  }

  public static List<AnswerChangeDto> answerChangeListToDtoList(List<Answer> answerList) {
    if (answerList == null) {
      return List.of();
    }
    return answerList
        .stream()
        .map(AnswerChangeMapper::answerChangeToDto)
        .toList();
  }
}
