package ru.hhschool.segment.model.dto.question;

import java.util.List;
import ru.hhschool.segment.model.enums.AnswerType;

public class AnswerCreateDto {
  private Long id;
  private String title;
  private String positiveTitle;
  private AnswerType type;
  private boolean defaultAnswer;
  private boolean skipAtResult;
  private List<QuestionCreateDto> openQuestions;

  public AnswerCreateDto() {
  }

  public AnswerCreateDto(
      Long id,
      String title,
      String positiveTitle,
      AnswerType type,
      boolean defaultAnswer,
      boolean skipAtResult,
      List<QuestionCreateDto> openQuestions
  ) {
    this.id = id;
    this.title = title;
    this.positiveTitle = positiveTitle;
    this.type = type;
    this.defaultAnswer = defaultAnswer;
    this.skipAtResult = skipAtResult;
    this.openQuestions = openQuestions;
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

  public String getPositiveTitle() {
    return positiveTitle;
  }

  public void setPositiveTitle(String positiveTitle) {
    this.positiveTitle = positiveTitle;
  }

  public AnswerType getType() {
    return type;
  }

  public void setType(AnswerType type) {
    this.type = type;
  }

  public boolean isDefaultAnswer() {
    return defaultAnswer;
  }

  public void setDefaultAnswer(boolean defaultAnswer) {
    this.defaultAnswer = defaultAnswer;
  }

  public boolean isSkipAtResult() {
    return skipAtResult;
  }

  public void setSkipAtResult(boolean skipAtResult) {
    this.skipAtResult = skipAtResult;
  }

  public List<QuestionCreateDto> getOpenQuestions() {
    return openQuestions;
  }

  public void setOpenQuestions(List<QuestionCreateDto> openQuestions) {
    this.openQuestions = openQuestions;
  }
}
