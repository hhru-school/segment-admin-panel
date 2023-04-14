package ru.hhschool.segment.model.dto;

import ru.hhschool.segment.model.enums.LayerStatus;

import java.time.LocalDateTime;

public class LayerDto {
  private Long id;
  private String title;
  private LocalDateTime createTime;
  private LayerStatus layerStatus;

  public LayerDto() {
  }

  public LayerDto(Long id, String title, LocalDateTime createTime, LayerStatus layerStatus) {
    this.id = id;
    this.title = title;
    this.createTime = createTime;
    this.layerStatus = layerStatus;
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

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public LayerStatus getLayerStatus() {
    return layerStatus;
  }

  public void setLayerStatus(LayerStatus layerStatus) {
    this.layerStatus = layerStatus;
  }

  public LayerStatus.Group getGroupOfStatusLayer() {
    return layerStatus.getGroup();
  }
}
