package ru.hhschool.segment.model.dto.screen;

import java.util.List;
import ru.hhschool.segment.model.enums.ScreenType;

public class ScreenCreateDto {
  private String title;
  private String description;
  private ScreenType type;
  private List<Long> platformsId;
  private List<Long> questionsId;

  public ScreenCreateDto() {
  }

  public ScreenCreateDto(String title, String description, ScreenType type, List<Long> platformsId, List<Long> questionsId) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.platformsId = platformsId;
    this.questionsId = questionsId;
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

  public List<Long> getPlatformsId() {
    return platformsId;
  }

  public void setPlatformsId(List<Long> platformsId) {
    this.platformsId = platformsId;
  }

  public List<Long> getQuestionsId() {
    return questionsId;
  }

  public void setQuestionsId(List<Long> questionsId) {
    this.questionsId = questionsId;
  }
}
