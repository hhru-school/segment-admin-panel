package ru.hhschool.segment.model.dto.layer.create;

import java.util.List;

public class LayerCreateDto {
  private String title;
  private String description;
  private LayerCreateParentDto parentLayer;
  private List<LayerCreateSegmentDto> segments;

  public LayerCreateDto() {
  }

  public LayerCreateDto(String title, String description, LayerCreateParentDto parentLayer, List<LayerCreateSegmentDto> segments) {
    this.title = title;
    this.description = description;
    this.parentLayer = parentLayer;
    this.segments = segments;
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

  public LayerCreateParentDto getParentLayer() {
    return parentLayer;
  }

  public void setParentLayer(LayerCreateParentDto parentLayer) {
    this.parentLayer = parentLayer;
  }

  public List<LayerCreateSegmentDto> getSegments() {
    return segments;
  }

  public void setSegments(List<LayerCreateSegmentDto> segments) {
    this.segments = segments;
  }
}
