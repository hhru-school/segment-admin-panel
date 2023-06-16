package ru.hhschool.segment.model.dto.layer.create;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class LinkCreateScreenQuestionDto {
  private Long id;
  private Long screenQuestionLinkId;
  private String title;
  private QuestionVisibilityType visibility;
  private QuestionVisibilityType oldVisibility;
  private Integer position;
  private Integer oldPosition;
  private boolean isNew;

  public LinkCreateScreenQuestionDto() {
  }

  public LinkCreateScreenQuestionDto(
      Long id,
      Long screenQuestionLinkId,
      String title,
      QuestionVisibilityType visibility,
      QuestionVisibilityType oldVisibility,
      Integer position,
      Integer oldPosition,
      boolean isNew
  ) {
    this.id = id;
    this.screenQuestionLinkId = screenQuestionLinkId;
    this.title = title;
    this.visibility = visibility;
    this.oldVisibility = oldVisibility;
    this.position = position;
    this.oldPosition = oldPosition;
    this.isNew = isNew;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getScreenQuestionLinkId() {
    return screenQuestionLinkId;
  }

  public void setScreenQuestionLinkId(Long screenQuestionLinkId) {
    this.screenQuestionLinkId = screenQuestionLinkId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public QuestionVisibilityType getVisibility() {
    return visibility;
  }

  public void setVisibility(QuestionVisibilityType visibility) {
    this.visibility = visibility;
  }

  public QuestionVisibilityType getOldVisibility() {
    return oldVisibility;
  }

  public void setOldVisibility(QuestionVisibilityType oldVisibility) {
    this.oldVisibility = oldVisibility;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Integer getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setNew(boolean aNew) {
    isNew = aNew;
  }
}
