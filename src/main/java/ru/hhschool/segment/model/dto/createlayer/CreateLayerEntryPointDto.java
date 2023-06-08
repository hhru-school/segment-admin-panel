package ru.hhschool.segment.model.dto.createlayer;

import java.util.List;

public class CreateLayerEntryPointDto {
  private Long entripointId;
  private String title;
  private String description;
  private List<CreateLayerScreenDto> screens;

  public CreateLayerEntryPointDto(Long entripointId, String title, String description, List<CreateLayerScreenDto> screens) {
    this.entripointId = entripointId;
    this.title = title;
    this.description = description;
    this.screens = screens;
  }

  public Long getEntripointId() {
    return entripointId;
  }

  public void setEntripointId(Long entripointId) {
    this.entripointId = entripointId;
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

  public List<CreateLayerScreenDto> getScreens() {
    return screens;
  }

  public void setScreens(List<CreateLayerScreenDto> screens) {
    this.screens = screens;
  }
}
