package ru.hhschool.segment.mapper.change;

import java.util.ArrayList;
import java.util.List;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.entity.Question;

public class MapperQuestionChange {
  public static QuestionChangeDto questionChangeToDto(Question question) {
    QuestionChangeDto questionChangeDto = new QuestionChangeDto(
        question.getId(),
        question.getTitle(),
        question.getDescription(),
        new ArrayList<>()
    );

    return questionChangeDto;
  }

  public static List<QuestionChangeDto> questionChangeListToDtoList(List<Question> questionList) {
    if (questionList == null) {
      return List.of();
    }
    return questionList
        .stream()
        .map(MapperQuestionChange::questionChangeToDto)
        .toList();
  }
}
