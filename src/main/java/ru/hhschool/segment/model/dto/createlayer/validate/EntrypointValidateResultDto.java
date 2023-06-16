package ru.hhschool.segment.model.dto.createlayer.validate;

import java.util.List;

public class EntrypointValidateResultDto {
  private Long entryPointId;
  private String entryPointTitle;
  private Integer screenPosition;
  private List<ScreenValidateResultDto> screens;

  public EntrypointValidateResultDto(){};

  public EntrypointValidateResultDto(Long entryPointId, String entryPointTitle, Integer screenPosition, List<ScreenValidateResultDto> screens) {
    this.entryPointId = entryPointId;
    this.entryPointTitle = entryPointTitle;
    this.screenPosition = screenPosition;
    this.screens = screens;
  }

  public Long getEntryPointId() {
    return entryPointId;
  }

  public void setEntryPointId(Long entryPointId) {
    this.entryPointId = entryPointId;
  }

  public String getEntryPointTitle() {
    return entryPointTitle;
  }

  public void setEntryPointTitle(String entryPointTitle) {
    this.entryPointTitle = entryPointTitle;
  }

  public Integer getScreenPosition() {
    return screenPosition;
  }

  public void setScreenPosition(Integer screenPosition) {
    this.screenPosition = screenPosition;
  }

  public List<ScreenValidateResultDto> getScreens() {
    return screens;
  }

  public void setScreens(List<ScreenValidateResultDto> screens) {
    this.screens = screens;
  }
}
