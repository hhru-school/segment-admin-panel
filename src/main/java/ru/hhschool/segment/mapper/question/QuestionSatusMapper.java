package ru.hhschool.segment.mapper.question;

import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.model.enums.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class QuestionSatusMapper {
  public static List<QuestionType> toTypeList(List<String> questionTypeStringList) {
    if (questionTypeStringList == null) {
      return List.of();
    }
    List<QuestionType> questionTypeList = new ArrayList<>();
    for (String questionTypeString : questionTypeStringList) {
      try {
        questionTypeList.add(QuestionType.valueOf(questionTypeString.toUpperCase()));
      } catch (IllegalArgumentException err) {
        throw new HttpBadRequestException("Неверно заданный параметр questionType.");
      }
    }
    return questionTypeList;
  }
}
