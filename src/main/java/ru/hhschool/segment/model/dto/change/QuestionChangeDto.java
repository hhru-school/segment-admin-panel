package ru.hhschool.segment.model.dto.change;

import java.util.List;

public class QuestionChangeDto implements ConflictSetter {
  private Long id;
  private String title;
  private String description;
  private List<AnswerChangeDto> answerList;
  private boolean conflict;

  public QuestionChangeDto() {
  }

  public QuestionChangeDto(Long id, String title, String description, List<AnswerChangeDto> answerList) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.answerList = answerList;
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

  public List<AnswerChangeDto> getAnswerList() {
    return answerList;
  }

  public void setAnswerList(List<AnswerChangeDto> answerList) {
    this.answerList = answerList;
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

    QuestionChangeDto that = (QuestionChangeDto) o;

    return title.equals(that.title);
  }

  @Override
  public int hashCode() {
    return title.hashCode();
  }
}
