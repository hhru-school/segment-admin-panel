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


}
