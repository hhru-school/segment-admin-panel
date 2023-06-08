package ru.hhschool.segment.model.dto.layer;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class DynamicScreenQuestionCreateDto {
  private Long questionId;
  private Integer questionPosition;
  private QuestionVisibilityType questionVisibility;

  public DynamicScreenQuestionCreateDto() {
  }

  public DynamicScreenQuestionCreateDto(Long questionId, Integer questionPosition, QuestionVisibilityType questionVisibility) {
    this.questionId = questionId;
    this.questionPosition = questionPosition;
    this.questionVisibility = questionVisibility;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public Integer getQuestionPosition() {
    return questionPosition;
  }

  public void setQuestionPosition(Integer questionPosition) {
    this.questionPosition = questionPosition;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }
}
