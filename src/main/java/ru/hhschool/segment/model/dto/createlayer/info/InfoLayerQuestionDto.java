package ru.hhschool.segment.model.dto.createlayer.info;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfoLayerQuestionDto {
  private Long id;
  private Long screenQuestionLinkId;
  private String title;
  private QuestionVisibilityType visibility;
  private Integer position;

  public InfoLayerQuestionDto() {

  }

  public InfoLayerQuestionDto(Long id,
                              Long screenQuestionLinkId,
                              String title,
                              QuestionVisibilityType visibility,
                              Integer position) {
    this.id = id;
    this.screenQuestionLinkId = screenQuestionLinkId;
    this.title = title;
    this.visibility = visibility;
    this.position = position;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getScreenQuestionLinkId() {
    return screenQuestionLinkId;
  }

  public void setScreenQuestionLinkId(Long screenQuestionLinkId) {
    this.screenQuestionLinkId = screenQuestionLinkId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public QuestionVisibilityType getVisibility() {
    return visibility;
  }

  public void setVisibility(QuestionVisibilityType visibility) {
    this.visibility = visibility;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }
}
