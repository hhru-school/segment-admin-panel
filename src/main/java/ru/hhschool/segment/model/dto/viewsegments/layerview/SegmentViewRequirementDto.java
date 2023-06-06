package ru.hhschool.segment.model.dto.viewsegments.layerview;

import ru.hhschool.segment.model.enums.StateType;


public class SegmentViewRequirementDto {
  private Long id;
  private String title;
  private Boolean required;
  private Boolean isChanged;
  private StateType state;
  private Boolean isNew;

  public SegmentViewRequirementDto() {

  }

  public SegmentViewRequirementDto(Long id, String title, Boolean required, Boolean isChanged, StateType state, Boolean isNew) {
    this.id = id;
    this.title = title;
    this.required = required;
    this.isChanged = isChanged;
    this.state = state;
    this.isNew = isNew;
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
  public Boolean getRequired() {
    return required;
  }

  public void setRequired(Boolean required) {
    this.required = required;
  }
  public Boolean getIsChanged() {
    return isChanged;
  }

  public void setIsChanged(Boolean isChanged) {
    this.isChanged = isChanged;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public Boolean getIsNew() {
    return isNew;
  }

  public void setIsNew(Boolean aNew) {
    isNew = aNew;
  }
}
