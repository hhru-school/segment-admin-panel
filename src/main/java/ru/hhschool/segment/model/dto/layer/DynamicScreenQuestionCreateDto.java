package ru.hhschool.segment.model.dto.layer;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class DynamicScreenQuestionCreateDto {
  private Long questionId;
  private QuestionVisibilityType questionVisibility;

  public DynamicScreenQuestionCreateDto() {
  }

  public DynamicScreenQuestionCreateDto(Long questionId, QuestionVisibilityType questionVisibility) {
    this.questionId = questionId;
    this.questionVisibility = questionVisibility;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }
}
