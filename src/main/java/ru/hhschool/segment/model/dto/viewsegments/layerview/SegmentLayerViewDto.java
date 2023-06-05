package ru.hhschool.segment.model.dto.viewsegments.layerview;

import java.util.List;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.enums.StateType;

public class SegmentLayerViewDto {
  private Long id;
  private String title;
  private List<Role> roles;
  private List<String> tags;
  private SegmentViewChangeState changeState;
  private StateType activeState;

  public SegmentLayerViewDto() {
  }

  public SegmentLayerViewDto(Long id, String title, List<Role> roles, List<String> tags, SegmentViewChangeState changeState, StateType activeState) {
    this.id = id;
    this.title = title;
    this.roles = roles;
    this.tags = tags;
    this.changeState = changeState;
    this.activeState = activeState;
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

  public SegmentViewChangeState getChangeState() {
    return changeState;
  }

  public void setChangeState(SegmentViewChangeState changeState) {
    this.changeState = changeState;
  }

  public StateType getActiveState() {
    return activeState;
  }

  public void setActiveState(StateType activeState) {
    this.activeState = activeState;
  }
}
