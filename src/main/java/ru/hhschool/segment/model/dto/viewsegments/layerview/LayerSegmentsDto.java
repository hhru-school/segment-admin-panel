package ru.hhschool.segment.model.dto.viewsegments.layerview;

import java.util.List;

public class LayerSegmentsDto {
  private Long id;
  private String title;
  private List<SegmentLayerViewDto> segments;

  public LayerSegmentsDto() {
  }

  public LayerSegmentsDto(Long id, String title, List<SegmentLayerViewDto> segments) {
    this.id = id;
    this.title = title;
    this.segments = segments;
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

  public List<SegmentLayerViewDto> getSegments() {
    return segments;
  }

  public void setSegments(List<SegmentLayerViewDto> segments) {
    this.segments = segments;
  }
}
