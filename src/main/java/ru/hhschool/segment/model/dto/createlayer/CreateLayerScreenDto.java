package ru.hhschool.segment.model.dto.createlayer;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.enums.ScreenType;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateLayerScreenDto {
  private Long screenId;
  private Long segmentScreenEntrypointLinkId;
  private String title;
  private String description;
  private ScreenType type;
  private Integer position;
  private List<PlatformDto> appVersions;
  private List<CreateLayerQuestionDto> fields;

  public CreateLayerScreenDto() {

  }

  public CreateLayerScreenDto(Long screenId,
                              Long segmentScreenEntrypointLinkId,
                              String title,
                              String description,
                              ScreenType type,
                              Integer position,
                              List<PlatformDto> appVersions,
                              List<CreateLayerQuestionDto> fields) {
    this.screenId = screenId;
    this.segmentScreenEntrypointLinkId = segmentScreenEntrypointLinkId;
    this.title = title;
    this.description = description;
    this.type = type;
    this.position = position;
    this.appVersions = appVersions;
    this.fields = fields;
  }

  public Long getScreenId() {
    return screenId;
  }

  public void setScreenId(Long screenId) {
    this.screenId = screenId;
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

  public List<CreateLayerQuestionDto> getFields() {
    return fields;
  }

  public void setFields(List<CreateLayerQuestionDto> fields) {
    this.fields = fields;
  }
}
