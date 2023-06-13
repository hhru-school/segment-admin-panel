package ru.hhschool.segment.model.dto.createlayer.validate;

import java.util.List;

public class QuestionValidateResultDto {
  private Long entrypointId;
  private String entrypointTitle;
  private Long questionId;
  private String questionTitle;
  private List<ScreenValidateResultDto> screens;

  public QuestionValidateResultDto(){};

  public QuestionValidateResultDto(Long entrypointId, String entrypointTitle, Long questionId, String questionTitle, List<ScreenValidateResultDto> screens) {
    this.entrypointId = entrypointId;
    this.entrypointTitle = entrypointTitle;
    this.questionId = questionId;
    this.questionTitle = questionTitle;
    this.screens = screens;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  public String getEntrypointTitle() {
    return entrypointTitle;
  }

  public void setEntrypointTitle(String entrypointTitle) {
    this.entrypointTitle = entrypointTitle;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public List<ScreenValidateResultDto> getScreens() {
    return screens;
  }

  public void setScreens(List<ScreenValidateResultDto> screens) {
    this.screens = screens;
  }
}
