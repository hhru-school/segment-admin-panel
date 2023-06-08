package ru.hhschool.segment.model.dto.viewsegments.layerview;

import java.util.List;

public class SegmentViewEntryPointDto {
  private Long id;
  private String title;
  private String description;
  private List<SegmentViewScreenDto> screens;

  public SegmentViewEntryPointDto(Long id, String title, String description, List<SegmentViewScreenDto> screens) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.screens = screens;
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

  public List<SegmentViewScreenDto> getScreens() {
    return screens;
  }

  public void setScreens(List<SegmentViewScreenDto> screens) {
    this.screens = screens;
  }
}
