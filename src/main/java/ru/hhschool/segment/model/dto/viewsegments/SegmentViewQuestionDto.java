package ru.hhschool.segment.model.dto.viewsegments;


import java.util.List;

public class SegmentViewQuestionDto {
  private Long questionId;
  private Boolean required;
  private String title;
  private List<SegmentViewEntryPointDto> entryPoints;

  public SegmentViewQuestionDto(Long questionId, Boolean required, String title, List<SegmentViewEntryPointDto> entryPoints) {
    this.questionId = questionId;
    this.required = required;
    this.title = title;
    this.entryPoints = entryPoints;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<SegmentViewEntryPointDto> getEntryPoints() {
    return entryPoints;
  }

  public void setEntryPoints(List<SegmentViewEntryPointDto> entryPoints) {
    this.entryPoints = entryPoints;
  }
}
