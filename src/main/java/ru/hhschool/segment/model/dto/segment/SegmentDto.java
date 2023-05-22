package ru.hhschool.segment.model.dto.segment;

import java.time.LocalDateTime;
import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;

public class SegmentDto {
  private Long id;
  private SegmentViewDto parentSegment;
  private LocalDateTime createTime;
  private String title;
  private String description;
  private List<RoleDto> roles;
  private List<String> tags;

  public SegmentDto() {
  }

  public SegmentDto(
      Long id,
      SegmentViewDto parentSegment,
      LocalDateTime createTime,
      String title,
      String description,
      List<RoleDto> roles,
      List<String> tags
  ) {
    this.id = id;
    this.parentSegment = parentSegment;
    this.createTime = createTime;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SegmentViewDto getParentSegment() {
    return parentSegment;
  }

  public void setParentSegment(SegmentViewDto parentSegment) {
    this.parentSegment = parentSegment;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
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
