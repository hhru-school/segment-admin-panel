package ru.hhschool.segment.model.dto.segment;

import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;

public class SegmentCreateDto {
  private SegmentViewDto parentSegment;
  private String title;
  private String description;
  private List<RoleDto> roles;
  private List<String> tags;

  public SegmentCreateDto() {
  }

  public SegmentCreateDto(SegmentViewDto parentSegment, String title, String description, List<RoleDto> roles, List<String> tags) {
    this.parentSegment = parentSegment;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
  }

  public SegmentViewDto getParentSegment() {
    return parentSegment;
  }

  public void setParentSegment(SegmentViewDto parentSegment) {
    this.parentSegment = parentSegment;
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

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}
