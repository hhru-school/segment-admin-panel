package ru.hhschool.segment.model.dto.segment;

import java.util.List;

public class SegmentCreateDto {
  private Long parentSegmentId;
  private String title;
  private String description;
  private List<Long> rolesId;
  private List<String> tags;

  public SegmentCreateDto() {
  }

  public SegmentCreateDto(Long parentSegmentId, String title, String description, List<Long> rolesId, List<String> tags) {
    this.parentSegmentId = parentSegmentId;
    this.title = title;
    this.description = description;
    this.rolesId = rolesId;
    this.tags = tags;
  }

  public Long getParentSegmentId() {
    return parentSegmentId;
  }

  public void setParentSegmentId(Long parentSegmentId) {
    this.parentSegmentId = parentSegmentId;
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

  public List<Long> getRolesId() {
    return rolesId;
  }

  public void setRolesId(List<Long> rolesId) {
    this.rolesId = rolesId;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
