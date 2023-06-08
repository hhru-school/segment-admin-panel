package ru.hhschool.segment.model.dto.layer;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class ScreenQuestionLinkCreateDto {
  private Long oldScreenQuestionLinkId;
  private Long segmentId;
  private Long entrypointId;
  private Long screenId;
  private Long questionId;
  private Integer questionPosition;
  private QuestionVisibilityType questionVisibility;

  public ScreenQuestionLinkCreateDto() {
  }

  public ScreenQuestionLinkCreateDto(
      Long oldScreenQuestionLinkId,
      Long segmentId,
      Long entrypointId,
      Long screenId,
      Long questionId,
      Integer questionPosition,
      QuestionVisibilityType questionVisibility
  ) {
    this.oldScreenQuestionLinkId = oldScreenQuestionLinkId;
    this.segmentId = segmentId;
    this.entrypointId = entrypointId;
    this.screenId = screenId;
    this.questionId = questionId;
    this.questionPosition = questionPosition;
    this.questionVisibility = questionVisibility;
  }

  public Long getOldScreenQuestionLinkId() {
    return oldScreenQuestionLinkId;
  }

  public void setOldScreenQuestionLinkId(Long oldScreenQuestionLinkId) {
    this.oldScreenQuestionLinkId = oldScreenQuestionLinkId;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  public Long getScreenId() {
    return screenId;
  }

  public void setScreenId(Long screenId) {
    this.screenId = screenId;
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
