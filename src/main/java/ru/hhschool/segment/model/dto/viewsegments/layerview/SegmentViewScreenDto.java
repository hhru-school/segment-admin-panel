package ru.hhschool.segment.model.dto.viewsegments.layerview;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegmentViewScreenDto {
  private Long id;
  private String title;
  private String description;
  private ScreenType type;
  private StateType state;
  private StateType oldState;
  private Boolean isNew;
  private Integer oldPosition;
  private List<PlatformDto> appVersions;
  private List<SegmentViewQuestionDto> fields;

  public SegmentViewScreenDto() {

  }

  public SegmentViewScreenDto(Long id, String title, String description, ScreenType type, StateType state, StateType oldState, Boolean isNew, Integer oldPosition, List<PlatformDto> appVersions, List<SegmentViewQuestionDto> fields) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.state = state;
    this.oldState = oldState;
    this.isNew = isNew;
    this.oldPosition = oldPosition;
    this.appVersions = appVersions;
    this.fields = fields;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ScreenType getType() {
    return type;
  }

  public void setType(ScreenType type) {
    this.type = type;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public StateType getOldState() {
    return oldState;
  }

  public void setOldState(StateType oldState) {
    this.oldState = oldState;
  }

  public Boolean getIsNew() {
    return isNew;
  }

  public void setIsNew(Boolean aNew) {
    isNew = aNew;
  }

  public Integer getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }

  public List<PlatformDto> getAppVersions() {
    return appVersions;
  }

  public void setAppVersions(List<PlatformDto> appVersions) {
    this.appVersions = appVersions;
  }

  public List<SegmentViewQuestionDto> getFields() {
    return fields;
  }

  public void setFields(List<SegmentViewQuestionDto> fields) {
    this.fields = fields;
  }
}
