package ru.hhschool.segment.model.dto.createlayer.info;


public class InfoLayerRequirementDto {
  private Long questionId;
  private Long questionRequiredLinkId;
  private String title;
  private Boolean required;

  public InfoLayerRequirementDto() {

  }

  public InfoLayerRequirementDto(Long questionId, Long questionRequiredLinkId, String title, Boolean required) {
    this.questionId = questionId;
    this.questionRequiredLinkId = questionRequiredLinkId;
    this.title = title;
    this.required = required;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
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

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }
}
