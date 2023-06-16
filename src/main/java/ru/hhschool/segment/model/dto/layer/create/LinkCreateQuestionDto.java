package ru.hhschool.segment.model.dto.layer.create;

public class LinkCreateQuestionDto {
  private Long id;
  private Long questionRequiredLinkId;
  private String title;
  private boolean required;
  private boolean isChanged;
  private boolean isDisabled;
  private boolean isNew;

  public LinkCreateQuestionDto() {
  }

  public LinkCreateQuestionDto(
      Long id,
      Long questionRequiredLinkId,
      String title,
      boolean required,
      boolean isChanged,
      boolean isDisabled,
      boolean isNew
  ) {
    this.id = id;
    this.questionRequiredLinkId = questionRequiredLinkId;
    this.title = title;
    this.required = required;
    this.isChanged = isChanged;
    this.isDisabled = isDisabled;
    this.isNew = isNew;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQuestionRequiredLinkId() {
    return questionRequiredLinkId;
  }

  public void setQuestionRequiredLinkId(Long questionRequiredLinkId) {
    this.questionRequiredLinkId = questionRequiredLinkId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isRequired() {
    return required;
  }

  public void setIsRequired(boolean required) {
    this.required = required;
  }

  public boolean isChanged() {
    return isChanged;
  }

  public void setIsChanged(boolean changed) {
    isChanged = changed;
  }

  public boolean isDisabled() {
    return isDisabled;
  }

  public void setIsDisabled(boolean disabled) {
    isDisabled = disabled;
  }

  public boolean isNew() {
    return isNew;
  }

  public void setIsNew(boolean aNew) {
    isNew = aNew;
  }
}
