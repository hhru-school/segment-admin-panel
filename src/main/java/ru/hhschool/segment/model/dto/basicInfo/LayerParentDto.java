package ru.hhschool.segment.model.dto.basicInfo;

import java.time.LocalDateTime;

public class LayerParentDto {
  private Long id;
  private String title;
  private LocalDateTime createTime;
  private BasicLayerStatus layerStatus;

  public LayerParentDto() {
  }

  public LayerParentDto(Long id, String title, LocalDateTime createTime, BasicLayerStatus layerStatus) {
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

  public BasicLayerStatus getLayerStatus() {
    return layerStatus;
  }

  public void setLayerStatus(BasicLayerStatus layerStatus) {
    this.layerStatus = layerStatus;
  }
}
