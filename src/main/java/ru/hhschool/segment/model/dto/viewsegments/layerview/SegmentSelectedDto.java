package ru.hhschool.segment.model.dto.viewsegments.layerview;

import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

public class SegmentSelectedDto {
  private Long id;
  private StateType activeState;
  private Long parentId;
  private String title;
  private String description;
  private List<Role> roles;
  private List<String> tags;
  private List<SegmentViewRequirementDto> requirements;
  private List<SegmentViewEntryPointDto> entryPoints;

  public SegmentSelectedDto(Long id, StateType activeState, Long parentId, String title, String description, List<Role> roles, List<String> tags, List<SegmentViewRequirementDto> requirements, List<SegmentViewEntryPointDto> entryPoints) {
    this.id = id;
    this.activeState = activeState;
    this.parentId = parentId;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
    this.requirements = requirements;
    this.entryPoints = entryPoints;
  }

  public SegmentSelectedDto() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public StateType getActiveState() {
    return activeState;
  }

  public void setActiveState(StateType activeState) {
    this.activeState = activeState;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
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

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<SegmentViewRequirementDto> getRequirements() {
    return requirements;
  }

  public void setRequirements(List<SegmentViewRequirementDto> requirements) {
    this.requirements = requirements;
  }

  public List<SegmentViewEntryPointDto> getEntryPoints() {
    return entryPoints;
  }

  public void setEntryPoints(List<SegmentViewEntryPointDto> entryPoints) {
    this.entryPoints = entryPoints;
  }
}
