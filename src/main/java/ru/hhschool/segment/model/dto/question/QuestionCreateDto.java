package ru.hhschool.segment.model.dto.question;

import java.util.List;
import ru.hhschool.segment.model.enums.AnswersNumberType;
import ru.hhschool.segment.model.enums.QuestionType;

public class QuestionCreateDto {
  private Long id;
  private String title;
  private String description;
  private QuestionType type;
  private AnswersNumberType answerType;
  private List<AnswerCreateDto> possibleAnswers;

  public QuestionCreateDto() {
  }

  public QuestionCreateDto(
      Long id,
      String title,
      String description,
      QuestionType type,
      AnswersNumberType answerType,
      List<AnswerCreateDto> possibleAnswers
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.answerType = answerType;
    this.possibleAnswers = possibleAnswers;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public QuestionType getType() {
    return type;
  }

  public void setType(QuestionType type) {
    this.type = type;
  }

  public AnswersNumberType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswersNumberType answerType) {
    this.answerType = answerType;
  }

  public List<AnswerCreateDto> getPossibleAnswers() {
    return possibleAnswers;
  }

  public void setPossibleAnswers(List<AnswerCreateDto> possibleAnswers) {
    this.possibleAnswers = possibleAnswers;
  }
}
