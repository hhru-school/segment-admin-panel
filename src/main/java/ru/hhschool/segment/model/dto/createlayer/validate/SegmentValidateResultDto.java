package ru.hhschool.segment.model.dto.createlayer.validate;

import java.util.List;

public class SegmentValidateResultDto {
  private String error;
  private String entrypointTitle;
  private Long entrypointId;
  private String questionTitle;
  private Long questionId;
  private List<ScreenValidateResultDto> screens;

  public SegmentValidateResultDto(){};
  public SegmentValidateResultDto(String error, String entrypointTitle, Long entrypointId, String questionTitle, Long questionId, List<ScreenValidateResultDto> screens) {
    this.error = error;
    this.entrypointTitle = entrypointTitle;
    this.entrypointId = entrypointId;
    this.questionTitle = questionTitle;
    this.questionId = questionId;
    this.screens = screens;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getEntrypointTitle() {
    return entrypointTitle;
  }

  public void setEntrypointTitle(String entrypointTitle) {
    this.entrypointTitle = entrypointTitle;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public List<ScreenValidateResultDto> getScreens() {
    return screens;
  }

  public void setScreens(List<ScreenValidateResultDto> screens) {
    this.screens = screens;
  }
}
