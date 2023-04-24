package ru.hhschool.segment.model.dto.change;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

public class QuestionActivatorLinkChangeDto implements ConflictSetter {
  private Long id;
  private boolean questionRequired;
  private QuestionVisibilityType questionVisibility;
  private String entrypointTitle;
  private String segmentTitle;
  @JsonIgnore
  private Long segmentParentId;
  private String questionTitle;
  private boolean conflict;

  public QuestionActivatorLinkChangeDto() {
  }

  public QuestionActivatorLinkChangeDto(
      Long id,
      boolean questionRequired,
      QuestionVisibilityType questionVisibility,
      String entrypointTitle,
      String segmentTitle,
      Long segmentParentId, String questionTitle
  ) {
    this.id = id;
    this.questionRequired = questionRequired;
    this.questionVisibility = questionVisibility;
    this.entrypointTitle = entrypointTitle;
    this.segmentTitle = segmentTitle;
    this.segmentParentId = segmentParentId;
    this.questionTitle = questionTitle;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isQuestionRequired() {
    return questionRequired;
  }

  public void setQuestionRequired(boolean questionRequired) {
    this.questionRequired = questionRequired;
  }

  public QuestionVisibilityType getQuestionVisibility() {
    return questionVisibility;
  }

  public void setQuestionVisibility(QuestionVisibilityType questionVisibility) {
    this.questionVisibility = questionVisibility;
  }

  public String getEntrypointTitle() {
    return entrypointTitle;
  }

  public void setEntrypointTitle(String entrypointTitle) {
    this.entrypointTitle = entrypointTitle;
  }

  public String getSegmentTitle() {
    return segmentTitle;
  }

  public void setSegmentTitle(String segmentTitle) {
    this.segmentTitle = segmentTitle;
  }

  public String getQuestionTitle() {
    return questionTitle;
  }

  public void setQuestionTitle(String questionTitle) {
    this.questionTitle = questionTitle;
  }

  public boolean isConflict() {
    return conflict;
  }

  @Override
  public void setConflict(boolean conflict) {
    this.conflict = conflict;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    QuestionActivatorLinkChangeDto that = (QuestionActivatorLinkChangeDto) o;

    if (!entrypointTitle.equals(that.entrypointTitle)) {
      return false;
    }
    if (!segmentTitle.equals(that.segmentTitle)) {
      return false;
    }
    if (!Objects.equals(segmentParentId, that.segmentParentId)) {
      return false;
    }
    return questionTitle.equals(that.questionTitle);
  }

  @Override
  public int hashCode() {
    int result = entrypointTitle.hashCode();
    result = 31 * result + segmentTitle.hashCode();
    result = 31 * result + (segmentParentId != null ? segmentParentId.hashCode() : 0);
    result = 31 * result + questionTitle.hashCode();
    return result;
  }
}
