package ru.hhschool.segment.model.dto.viewsegments.layerview;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegmentViewQuestionDto {
  private Long id;
  private String title;
  private Boolean isNew;
  private QuestionVisibilityType visibility;
  private QuestionVisibilityType oldVisibility;
  private Integer oldPosition;

  public SegmentViewQuestionDto(Long id,
                                String title,
                                Boolean isNew,
                                QuestionVisibilityType visibility,
                                QuestionVisibilityType oldVisibility,
                                Integer oldPosition) {
    this.id = id;
    this.title = title;
    this.isNew = isNew;
    this.visibility = visibility;
    this.oldVisibility = oldVisibility;
    this.oldPosition = oldPosition;
  }

  public SegmentViewQuestionDto() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
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

  public Integer getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }
}
