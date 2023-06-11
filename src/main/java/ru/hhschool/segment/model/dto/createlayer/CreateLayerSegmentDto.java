package ru.hhschool.segment.model.dto.createlayer;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateLayerSegmentDto {
  private Long segmentId;
  private Long segmentStateLinkId;
  private StateType activeState;
  private String title;
  private String description;
  private List<Role> roles;
  private List<String> tags;
  private List<CreateLayerRequirementDto> fields;
  private List<CreateLayerEntryPointDto> entryPoints;

  public CreateLayerSegmentDto() {

  }

  public CreateLayerSegmentDto(Long segmentId,
                               Long segmentStateLinkId ,
                               StateType activeState,
                               String title,
                               String description,
                               List<Role> roles,
                               List<String> tags,
                               List<CreateLayerRequirementDto> fields,
                               List<CreateLayerEntryPointDto> entryPoints) {
    this.segmentId = segmentId;
    this.segmentStateLinkId = segmentStateLinkId;
    this.activeState = activeState;
    this.title = title;
    this.description = description;
    this.roles = roles;
    this.tags = tags;
    this.fields = fields;
    this.entryPoints = entryPoints;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
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

  public List<CreateLayerRequirementDto> getFields() {
    return fields;
  }

  public void setFields(List<CreateLayerRequirementDto> fields) {
    this.fields = fields;
  }

  public List<CreateLayerEntryPointDto> getEntryPoints() {
    return entryPoints;
  }

  public void setEntryPoints(List<CreateLayerEntryPointDto> entryPoints) {
    this.entryPoints = entryPoints;
  }
}
