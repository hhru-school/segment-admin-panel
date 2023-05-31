package ru.hhschool.segment.model.dto.screen;

import java.util.List;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.model.enums.StateType;

public class ScreenDto {
  private Long id;
  private String title;
  private String description;
  private ScreenType type;
  private StateType state;
  private List<ScreenFieldDto> fields;
  private List<ScreenPlatformDto> appVersions;

  public ScreenDto() {
  }

  public ScreenDto(
      Long id,
      String title,
      String description,
      ScreenType type,
      StateType state,
      List<ScreenFieldDto> fields,
      List<ScreenPlatformDto> appVersions
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.state = state;
    this.fields = fields;
    this.appVersions = appVersions;
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

  public List<ScreenFieldDto> getFields() {
    return fields;
  }

  public void setFields(List<ScreenFieldDto> fields) {
    this.fields = fields;
  }

  public List<ScreenPlatformDto> getAppVersions() {
    return appVersions;
  }

  public void setAppVersions(List<ScreenPlatformDto> appVersions) {
    this.appVersions = appVersions;
  }
}
