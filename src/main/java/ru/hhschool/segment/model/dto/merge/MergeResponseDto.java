package ru.hhschool.segment.model.dto.merge;

import ru.hhschool.segment.model.enums.LayerStateType;

public class MergeResponseDto {
  private Long id;
  private LayerStateType state;

  public MergeResponseDto(Long id, LayerStateType state) {
    this.id = id;
    this.state = state;
  }

  public MergeResponseDto() {

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
