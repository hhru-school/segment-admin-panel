package ru.hhschool.segment.model.dto.change;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionActivatorLinkChangeDto {
  private Long id;
  private boolean questionRequired;
  private QuestionVisibilityType questionVisibility;
  private String entrypointTitle;
  private String segmentTitle;
  private String questionTitle;
  private boolean conflict;

  public QuestionActivatorLinkChangeDto() {
  }

  public QuestionActivatorLinkChangeDto(
      Long id,
      boolean questionRequired,
      QuestionVisibilityType questionVisibility,
      String entrypointTitle,
      String segmentTitle,
      String questionTitle
  ) {
    this.id = id;
    this.questionRequired = questionRequired;
    this.questionVisibility = questionVisibility;
    this.entrypointTitle = entrypointTitle;
    this.segmentTitle = segmentTitle;
    this.questionTitle = questionTitle;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isQuestionRequired() {
    return questionRequired;
  }

  public void setQuestionRequired(boolean questionRequired) {
    this.questionRequired = questionRequired;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }

  public String getEntrypointTitle() {
    return entrypointTitle;
  }

  public void setEntrypointTitle(String entrypointTitle) {
    this.entrypointTitle = entrypointTitle;
  }

  public String getSegmentTitle() {
    return segmentTitle;
  }

  public void setSegmentTitle(String segmentTitle) {
    this.segmentTitle = segmentTitle;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public boolean isConflict() {
    return conflict;
  }

  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }
}
