package ru.hhschool.segment.model.dto.createlayer.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoLayerScreenDto {
  private Long id;
  private Long segmentScreenEntrypointLinkId;
  private String title;
  private String description;
  private ScreenType type;
  private StateType state;
  private Integer position;
  private List<PlatformDto> appVersions;
  private List<InfoLayerQuestionDto> fields;

  public InfoLayerScreenDto() {

  }

  public InfoLayerScreenDto(Long id,
                            Long segmentScreenEntrypointLinkId,
                            String title,
                            String description,
                            ScreenType type,
                            StateType state,
                            Integer position,
                            List<PlatformDto> appVersions,
                            List<InfoLayerQuestionDto> fields) {
    this.id = id;
    this.segmentScreenEntrypointLinkId = segmentScreenEntrypointLinkId;
    this.title = title;
    this.description = description;
    this.type = type;
    this.state = state;
    this.position = position;
    this.appVersions = appVersions;
    this.fields = fields;
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

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public List<PlatformDto> getAppVersions() {
    return appVersions;
  }

  public void setAppVersions(List<PlatformDto> appVersions) {
    this.appVersions = appVersions;
  }

  public List<InfoLayerQuestionDto> getFields() {
    return fields;
  }

  public void setFields(List<InfoLayerQuestionDto> fields) {
    this.fields = fields;
  }
}
