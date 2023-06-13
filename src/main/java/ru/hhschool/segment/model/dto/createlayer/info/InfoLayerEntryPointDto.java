package ru.hhschool.segment.model.dto.createlayer.info;

import java.util.List;

public class InfoLayerEntryPointDto {
  private Long entripointId;
  private String title;
  private String description;
  private List<InfoLayerScreenDto> screens;

  public InfoLayerEntryPointDto(Long entripointId, String title, String description, List<InfoLayerScreenDto> screens) {
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

  public List<InfoLayerScreenDto> getScreens() {
    return screens;
  }

  public void setScreens(List<InfoLayerScreenDto> screens) {
    this.screens = screens;
  }
}
