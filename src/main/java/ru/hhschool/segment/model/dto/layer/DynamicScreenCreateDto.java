package ru.hhschool.segment.model.dto.layer;

import java.util.List;
import ru.hhschool.segment.model.enums.StateType;

public class DynamicScreenCreateDto {
  private String title;
  private String description;
  private Integer screenPosition;
  private StateType screenState;
  private Long segmentId;
  private Long entrypointId;
  private List<DynamicScreenQuestionCreateDto> questions;

  public DynamicScreenCreateDto() {
  }

  public DynamicScreenCreateDto(
      String title,
      String description,
      Integer screenPosition,
      Long segmentId,
      Long entrypointId,
      List<DynamicScreenQuestionCreateDto> questions
  ) {
    this.title = title;
    this.description = description;
    this.screenPosition = screenPosition;
    this.screenState = screenState;
    this.segmentId = segmentId;
    this.entrypointId = entrypointId;
    this.questions = questions;
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

  public Integer getScreenPosition() {
    return screenPosition;
  }

  public void setScreenPosition(Integer screenPosition) {
    this.screenPosition = screenPosition;
  }

  public StateType getScreenState() {
    return screenState;
  }

  public void setScreenState(StateType screenState) {
    this.screenState = screenState;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public Long getEntrypointId() {
    return entrypointId;
  }

  public void setEntrypointId(Long entrypointId) {
    this.entrypointId = entrypointId;
  }

  public List<DynamicScreenQuestionCreateDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<DynamicScreenQuestionCreateDto> questions) {
    this.questions = questions;
  }
}
