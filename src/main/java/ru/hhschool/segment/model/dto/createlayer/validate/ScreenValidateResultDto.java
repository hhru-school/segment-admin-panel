package ru.hhschool.segment.model.dto.createlayer.validate;

public class ScreenValidateResultDto {
  private String screenTitle;
  private Long screenId;
  public ScreenValidateResultDto(){}

  public ScreenValidateResultDto(String screenTitle, Long screenId) {
    this.screenTitle = screenTitle;
    this.screenId = screenId;
  }

  public String getScreenTitle() {
    return screenTitle;
  }

  public void setScreenTitle(String screenTitle) {
    this.screenTitle = screenTitle;
  }

  public Long getScreenId() {
    return screenId;
  }

  public void setScreenId(Long screenId) {
    this.screenId = screenId;
  }
}
