package ru.hhschool.segment.model.dto.viewsegments.layerview;

import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class SegmentViewEntryPointDto {
  private String title;
  private QuestionVisibilityType visibility;
  private Boolean status;

  public SegmentViewEntryPointDto(String title, QuestionVisibilityType visibility, Boolean status) {
    this.title = title;
    this.visibility = visibility;
    this.status = status;
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

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
