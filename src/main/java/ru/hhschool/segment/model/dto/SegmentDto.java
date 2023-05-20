package ru.hhschool.segment.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SegmentDto {
  private Long id;
  private Long parentSegmentId;
  private LocalDateTime createTime;
  private String title;
  private String description;
  private List<RoleDto> roleList;
  private List<String> tagList;

  public SegmentDto() {
  }

  public SegmentDto(
      Long id,
      Long parentSegmentId,
      LocalDateTime createTime,
      String title,
      String description,
      List<RoleDto> roleList,
      List<String> tagList
  ) {
    this.id = id;
    this.parentSegmentId = parentSegmentId;
    this.createTime = createTime;
    this.title = title;
    this.description = description;
    this.roleList = roleList;
    this.tagList = tagList;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getParentSegmentId() {
    return parentSegmentId;
  }

  public void setParentSegmentId(Long parentSegmentId) {
    this.parentSegmentId = parentSegmentId;
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

  public List<RoleDto> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<RoleDto> roleList) {
    this.roleList = roleList;
  }

  public List<String> getTagList() {
    return tagList;
  }

  public void setTagList(List<String> tagList) {
    this.tagList = tagList;
  }
}
