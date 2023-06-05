package ru.hhschool.segment.model.dto.layer;

import java.time.LocalDateTime;
import ru.hhschool.segment.model.enums.LayerStateType;

public class LayerForListDto {
  private Long id;
  private String title;
  private String description;
  private LayerStateType state;
  private LocalDateTime createTime;

  public LayerForListDto() {
  }

  public LayerForListDto(Long id, String title, String description, LayerStateType state, LocalDateTime createTime) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.state = state;
    this.createTime = createTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public LayerStateType getState() {
    return state;
  }

  public void setState(LayerStateType state) {
    this.state = state;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

}
