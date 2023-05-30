package ru.hhschool.segment.model.dto.viewsegments.layerview;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ru.hhschool.segment.model.enums.ScreenType;

import java.util.List;

@JsonPropertyOrder({"id", "title", "description", "type", "new", "oldPosition", "platforms", "questions"})
public class SegmentViewScreenDto {
  private Long id;
  private String title;
  private String description;
  private ScreenType type;
  private Boolean isNew;
  private Integer oldPosition;
  private List<SegmentViewPlatformDto> platforms;
  private List<SegmentViewQuestionDto> questions;

  public SegmentViewScreenDto() {

  }

  public SegmentViewScreenDto(Long id, String title, String description, ScreenType type, Boolean isNew, Integer oldPosition, List<SegmentViewPlatformDto> platforms, List<SegmentViewQuestionDto> questions) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.isNew = isNew;
    this.oldPosition = oldPosition;
    this.platforms = platforms;
    this.questions = questions;
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
  public Boolean getNew() {
    return isNew;
  }

  public void setNew(Boolean aNew) {
    isNew = aNew;
  }

  public Integer getOldPosition() {
    return oldPosition;
  }

  public void setOldPosition(Integer oldPosition) {
    this.oldPosition = oldPosition;
  }

  public List<SegmentViewPlatformDto> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<SegmentViewPlatformDto> platforms) {
    this.platforms = platforms;
  }

  public List<SegmentViewQuestionDto> getQuestions() {
    return questions;
  }

  public void setQuestions(List<SegmentViewQuestionDto> questions) {
    this.questions = questions;
  }
}
