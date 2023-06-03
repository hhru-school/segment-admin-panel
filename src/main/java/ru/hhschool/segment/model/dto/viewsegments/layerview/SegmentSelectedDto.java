package ru.hhschool.segment.model.dto.viewsegments.layerview;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SegmentSelectedDto {
  private Long layerId;
  private String layerTitle;
  private Long segmentId;
  private StateType activeState;
  private StateType oldActiveState;
  private Long parentSegmentId;
  private String title;
  private String description;
  private List<Role> roles;
  private List<String> tags;
  private List<SegmentViewRequirementDto> requirements;
  private List<SegmentViewEntryPointDto> entryPoints;

  public SegmentSelectedDto(Long layerId, String layerTitle, Long segmentId, StateType activeState, StateType oldActiveState, Long parentSegmentId, String title, String description, List<Role> roles, List<String> tags, List<SegmentViewRequirementDto> requirements, List<SegmentViewEntryPointDto> entryPoints) {
    this.layerId = layerId;
    this.layerTitle = layerTitle;
    this.segmentId = segmentId;
    this.activeState = activeState;
    this.oldActiveState = oldActiveState;
    this.parentSegmentId = parentSegmentId;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
    this.requirements = requirements;
    this.entryPoints = entryPoints;
  }

  public SegmentSelectedDto() {

  }

  public Long getLayerId() {
    return layerId;
  }

  public void setLayerId(Long layerId) {
    this.layerId = layerId;
  }

  public String getLayerTitle() {
    return layerTitle;
  }

  public void setLayerTitle(String layerTitle) {
    this.layerTitle = layerTitle;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public StateType getActiveState() {
    return activeState;
  }

  public void setActiveState(StateType activeState) {
    this.activeState = activeState;
  }

  public StateType getOldActiveState() {
    return oldActiveState;
  }

  public void setOldActiveState(StateType oldActiveState) {
    this.oldActiveState = oldActiveState;
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
