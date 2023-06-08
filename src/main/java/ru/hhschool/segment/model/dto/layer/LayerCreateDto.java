package ru.hhschool.segment.model.dto.layer;

import java.util.List;

public class LayerCreateDto {
  private String title;
  private String description;
  private Long parentLayerId;
  private List<SegmentStateLinkCreateDto> segmentStateLinks;
  private List<QuestionRequiredLinkCreateDto> questionRequiredLinks;
  private List<ScreenQuestionLinkCreateDto> screenQuestionLinks;
  private List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks;

  public LayerCreateDto() {
  }

  public LayerCreateDto(
      String title,
      String description,
      Long parentLayerId,
      List<SegmentStateLinkCreateDto> segmentStateLinks,
      List<QuestionRequiredLinkCreateDto> questionRequiredLinks,
      List<ScreenQuestionLinkCreateDto> screenQuestionLinks,
      List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks
  ) {
    this.title = title;
    this.description = description;
    this.parentLayerId = parentLayerId;
    this.segmentStateLinks = segmentStateLinks;
    this.questionRequiredLinks = questionRequiredLinks;
    this.screenQuestionLinks = screenQuestionLinks;
    this.segmentScreenEntrypointLinks = segmentScreenEntrypointLinks;
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

  public Long getParentLayerId() {
    return parentLayerId;
  }

  public void setParentLayerId(Long parentLayerId) {
    this.parentLayerId = parentLayerId;
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
}
