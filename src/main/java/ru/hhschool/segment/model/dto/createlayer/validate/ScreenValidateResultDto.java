package ru.hhschool.segment.model.dto.createlayer.validate;

import java.util.Objects;

public class ScreenValidateResultDto {
  private Long screenId;
  private String screenTitle;

  public ScreenValidateResultDto(){}

  public ScreenValidateResultDto(Long screenId, String screenTitle) {
    this.screenId = screenId;
    this.screenTitle = screenTitle;
  }

  public Long getScreenId() {
    return screenId;
  }

  public void setScreenId(Long screenId) {
    this.screenId = screenId;
  }

  public String getScreenTitle() {
    return screenTitle;
  }

  public void setScreenTitle(String screenTitle) {
    this.screenTitle = screenTitle;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ScreenValidateResultDto that = (ScreenValidateResultDto) o;
    return Objects.equals(screenId, that.screenId) && Objects.equals(screenTitle, that.screenTitle);
  }

  @Override
  public int hashCode() {
    return Objects.hash(screenId, screenTitle);
  }
}
