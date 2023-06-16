package ru.hhschool.segment.model.dto.layer.create;

import java.util.List;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.model.enums.StateType;

public class LayerCreateScreenDto {
  private Long id;
  private Long segmentScreenEntrypointLinkId;
  private StateType state;
  private String title;
  private String description;
  private ScreenType type;
  private List<LinkCreateScreenQuestionDto> fields;
  private List<PlatformDto> appVersions;
  private Integer position;
  private Integer oldPosition;
  private Boolean isNew;


  public LayerCreateScreenDto() {
  }

  public LayerCreateScreenDto(
      Long id,
      Long segmentScreenEntrypointLinkId,
      StateType state,
      String title,
      String description,
      ScreenType type,
      List<LinkCreateScreenQuestionDto> fields,
      List<PlatformDto> appVersions,
      Integer position,
      Integer oldPosition,
      Boolean isNew
  ) {
    this.id = id;
    this.segmentScreenEntrypointLinkId = segmentScreenEntrypointLinkId;
    this.state = state;
    this.title = title;
    this.description = description;
    this.type = type;
    this.fields = fields;
    this.appVersions = appVersions;
    this.position = position;
    this.oldPosition = oldPosition;
    this.isNew = isNew;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getSegmentScreenEntrypointLinkId() {
    return segmentScreenEntrypointLinkId;
  }

  public void setSegmentScreenEntrypointLinkId(Long segmentScreenEntrypointLinkId) {
    this.segmentScreenEntrypointLinkId = segmentScreenEntrypointLinkId;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
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

  public ScreenType getType() {
    return type;
  }

  public void setType(ScreenType type) {
    this.type = type;
  }

  public List<LinkCreateScreenQuestionDto> getFields() {
    return fields;
  }

  public void setFields(List<LinkCreateScreenQuestionDto> fields) {
    this.fields = fields;
  }

  public List<PlatformDto> getAppVersions() {
    return appVersions;
  }

  public void setAppVersions(List<PlatformDto> appVersions) {
    this.appVersions = appVersions;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Integer getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }

  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
  }
}
