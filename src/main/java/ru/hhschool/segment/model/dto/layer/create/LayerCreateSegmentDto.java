package ru.hhschool.segment.model.dto.layer.create;

import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.enums.StateType;

public class LayerCreateSegmentDto {
  private Long id;
  private Long segmentStateLinkId;
  private StateType activeState;
  private String title;
  private String description;
  private List<RoleDto> roles;
  private List<String> tags;
  private List<LinkCreateQuestionDto> fields;
  private List<LayerCreateEntrypointDto> entryPoints;
  private Boolean isNew;

  public LayerCreateSegmentDto() {
  }

  public LayerCreateSegmentDto(
      Long id,
      Long segmentStateLinkId,
      StateType activeState,
      String title,
      String description,
      List<RoleDto> roles,
      List<String> tags,
      List<LinkCreateQuestionDto> fields,
      List<LayerCreateEntrypointDto> entryPoints,
      Boolean isNew
  ) {
    this.id = id;
    this.segmentStateLinkId = segmentStateLinkId;
    this.activeState = activeState;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
    this.fields = fields;
    this.entryPoints = entryPoints;
    this.isNew = isNew;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSegmentStateLinkId() {
    return segmentStateLinkId;
  }

  public void setSegmentStateLinkId(Long segmentStateLinkId) {
    this.segmentStateLinkId = segmentStateLinkId;
  }

  public StateType getActiveState() {
    return activeState;
  }

  public void setActiveState(StateType activeState) {
    this.activeState = activeState;
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

  public List<LinkCreateQuestionDto> getFields() {
    return fields;
  }

  public void setFields(List<LinkCreateQuestionDto> fields) {
    this.fields = fields;
  }

  public List<LayerCreateEntrypointDto> getEntryPoints() {
    return entryPoints;
  }

  public void setEntryPoints(List<LayerCreateEntrypointDto> entryPoints) {
    this.entryPoints = entryPoints;
  }

  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
  }
}
