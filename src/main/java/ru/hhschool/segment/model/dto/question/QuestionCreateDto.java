package ru.hhschool.segment.model.dto.question;

import java.util.List;
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.QuestionType;

public class QuestionCreateDto {
  private String title;
  private String description;
  private QuestionType type;
  private AnswersNumberType answerType;
  private List<AnswerCreateDto> possibleAnswers;

}
