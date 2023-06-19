package ru.hhschool.segment.model.dto.merge;

import ru.hhschool.segment.model.dto.merge.enums.MergeErrorType;
import ru.hhschool.segment.model.enums.LayerStateType;

public class MergeResponseDto {
  private Long id;
  private LayerStateType state;
  private MergeErrorType errorType;

  public MergeResponseDto() {
  }

  public MergeResponseDto(Long id, LayerStateType state, MergeErrorType errorType) {
    this.id = id;
    this.state = state;
    this.errorType = errorType;
  }

  public MergeErrorType getErrorType() {
    return errorType;
  }

  public void setErrorType(MergeErrorType errorType) {
    this.errorType = errorType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LayerStateType getState() {
    return state;
  }

  public void setState(LayerStateType state) {
    this.state = state;
  }
}
