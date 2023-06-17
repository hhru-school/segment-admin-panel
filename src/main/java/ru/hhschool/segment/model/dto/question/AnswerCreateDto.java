package ru.hhschool.segment.model.dto.question;

import java.util.List;
import ru.hhschool.segment.model.enums.AnswerType;

public class AnswerCreateDto {
  private String title;
  private String positiveTitle;
  private AnswerType type;
  private boolean defaultAnswer;
  private boolean skipAtResult;
  private List<QuestionCreateDto> openQuestions;
}
