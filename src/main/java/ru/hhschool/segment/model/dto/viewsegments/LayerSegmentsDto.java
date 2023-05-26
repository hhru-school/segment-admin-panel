package ru.hhschool.segment.model.dto.viewsegments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LayerSegmentsDto {
  private Long id;
  private String title;
  @JsonProperty("segmentList")
  private List<SegmentViewDto> segments;

  public LayerSegmentsDto() {}

  public LayerSegmentsDto(Long id, String title, List<SegmentViewDto> segments) {
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

  public List<SegmentViewDto> getSegments() {
    return segments;
  }

  public void setSegments(List<SegmentViewDto> segments) {
    this.segments = segments;
  }
}
