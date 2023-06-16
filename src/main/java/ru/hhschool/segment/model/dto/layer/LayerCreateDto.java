package ru.hhschool.segment.model.dto.layer;

import java.util.List;

public class LayerCreateDto {
  private String title;
  private String description;
  private LayerCreateParentDto parentLayer;
  private List<LayerCreateSegmentDto> segments;
  private List<SegmentStateLinkCreateDto> segmentStateLinks;
  private List<QuestionRequiredLinkCreateDto> questionRequiredLinks;
  private List<ScreenQuestionLinkCreateDto> screenQuestionLinks;
  private List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks;
  private List<DynamicScreenCreateDto> dynamicScreens;
  private List<Long> platformsId;

  public LayerCreateDto() {
  }

  public LayerCreateDto(
      String title,
      String description,
      LayerCreateParentDto parentLayer,
      List<LayerCreateSegmentDto> segments,
      List<SegmentStateLinkCreateDto> segmentStateLinks,
      List<QuestionRequiredLinkCreateDto> questionRequiredLinks,
      List<ScreenQuestionLinkCreateDto> screenQuestionLinks,
      List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks,
      List<DynamicScreenCreateDto> dynamicScreens,
      List<Long> platformsId
  ) {
    this.title = title;
    this.description = description;
    this.parentLayer = parentLayer;
    this.segments = segments;
    this.segmentStateLinks = segmentStateLinks;
    this.questionRequiredLinks = questionRequiredLinks;
    this.screenQuestionLinks = screenQuestionLinks;
    this.segmentScreenEntrypointLinks = segmentScreenEntrypointLinks;
    this.dynamicScreens = dynamicScreens;
    this.platformsId = platformsId;
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

  public LayerCreateParentDto getParentLayer() {
    return parentLayer;
  }

  public void setParentLayer(LayerCreateParentDto parentLayer) {
    this.parentLayer = parentLayer;
  }

  public List<LayerCreateSegmentDto> getSegments() {
    return segments;
  }

  public void setSegments(List<LayerCreateSegmentDto> segments) {
    this.segments = segments;
  }

  public List<SegmentStateLinkCreateDto> getSegmentStateLinks() {
    return segmentStateLinks;
  }

  public void setSegmentStateLinks(List<SegmentStateLinkCreateDto> segmentStateLinks) {
    this.segmentStateLinks = segmentStateLinks;
  }

  public List<QuestionRequiredLinkCreateDto> getQuestionRequiredLinks() {
    return questionRequiredLinks;
  }

  public void setQuestionRequiredLinks(List<QuestionRequiredLinkCreateDto> questionRequiredLinks) {
    this.questionRequiredLinks = questionRequiredLinks;
  }

  public List<ScreenQuestionLinkCreateDto> getScreenQuestionLinks() {
    return screenQuestionLinks;
  }

  public void setScreenQuestionLinks(List<ScreenQuestionLinkCreateDto> screenQuestionLinks) {
    this.screenQuestionLinks = screenQuestionLinks;
  }

  public List<SegmentScreenEntrypointLinkCreateDto> getSegmentScreenEntrypointLinks() {
    return segmentScreenEntrypointLinks;
  }

  public void setSegmentScreenEntrypointLinks(List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks) {
    this.segmentScreenEntrypointLinks = segmentScreenEntrypointLinks;
  }

  public List<DynamicScreenCreateDto> getDynamicScreens() {
    return dynamicScreens;
  }

  public void setDynamicScreens(List<DynamicScreenCreateDto> dynamicScreens) {
    this.dynamicScreens = dynamicScreens;
  }

  public List<Long> getPlatformsId() {
    return platformsId;
  }

  public void setPlatformsId(List<Long> platformsId) {
    this.platformsId = platformsId;
  }
}
