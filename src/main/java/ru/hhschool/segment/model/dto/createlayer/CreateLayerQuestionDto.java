package ru.hhschool.segment.model.dto.createlayer;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateLayerQuestionDto {
  private Long questionId;
  private Long screenQuestionLinkId;
  private String title;
  private QuestionVisibilityType visibility;
  private Integer position;

  public CreateLayerQuestionDto() {

  }

  public CreateLayerQuestionDto(Long questionId,
                                Long screenQuestionLinkId,
                                String title,
                                QuestionVisibilityType visibility,
                                Integer position) {
    this.questionId = questionId;
    this.screenQuestionLinkId = screenQuestionLinkId;
    this.title = title;
    this.visibility = visibility;
    this.position = position;
  }

  public Long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(Long questionId) {
    this.questionId = questionId;
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
