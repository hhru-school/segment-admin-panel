package ru.hhschool.segment.mapper.screen;

import java.util.List;
import ru.hhschool.segment.model.dto.screen.ScreenFieldDto;
import ru.hhschool.segment.model.entity.Question;

public class ScreenFieldMapper {
  public static ScreenFieldDto fieldToDto(Question question) {
    ScreenFieldDto screenFieldDto = new ScreenFieldDto(
        question.getId(),
        question.getTitle()
    );
    return screenFieldDto;
  }

  public static List<ScreenFieldDto> fieldListToDtoList(List<Question> questionList) {
    if (questionList == null) {
      return List.of();
    }
    return questionList.stream()
        .map(ScreenFieldMapper::fieldToDto)
        .toList();
  }
}
