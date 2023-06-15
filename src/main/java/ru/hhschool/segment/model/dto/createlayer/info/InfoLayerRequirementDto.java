package ru.hhschool.segment.model.dto.createlayer.info;


public class InfoLayerRequirementDto {
  private Long id;
  private Long questionRequiredLinkId;
  private String title;
  private Boolean required;

  public InfoLayerRequirementDto() {

  }

  public InfoLayerRequirementDto(Long id, Long questionRequiredLinkId, String title, Boolean required) {
    this.id = id;
    this.questionRequiredLinkId = questionRequiredLinkId;
    this.title = title;
    this.required = required;
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

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }
}
