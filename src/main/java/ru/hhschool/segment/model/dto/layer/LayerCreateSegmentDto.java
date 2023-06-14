package ru.hhschool.segment.model.dto.layer;

import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.enums.StateType;

public class LayerCreateSegmentDto {
  private Long id;
  private String title;
  private List<RoleDto> roles;
  private List<String> tags;
  private StateType activeState;
  private Boolean isNew;

  public LayerCreateSegmentDto() {
  }

  public LayerCreateSegmentDto(Long id, String title, List<RoleDto> roles, List<String> tags, StateType activeState, Boolean isNew) {
    this.id = id;
    this.title = title;
    this.roles = roles;
    this.tags = tags;
    this.activeState = activeState;
    this.isNew = isNew;
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

  public StateType getActiveState() {
    return activeState;
  }

  public void setActiveState(StateType activeState) {
    this.activeState = activeState;
  }

  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
  }
}
