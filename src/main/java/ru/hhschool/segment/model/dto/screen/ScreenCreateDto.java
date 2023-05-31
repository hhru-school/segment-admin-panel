package ru.hhschool.segment.model.dto.screen;

import java.util.List;
import ru.hhschool.segment.model.enums.ScreenType;

public class ScreenCreateDto {
  private String title;
  private String description;
  private ScreenType type;
  private List<Long> appVersions;
  private List<Long> questions;

  public ScreenCreateDto() {
  }

  public ScreenCreateDto(String title, String description, ScreenType type, List<Long> appVersions, List<Long> questions) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.appVersions = appVersions;
    this.questions = questions;
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

  public List<Long> getAppVersions() {
    return appVersions;
  }

  public void setAppVersions(List<Long> appVersions) {
    this.appVersions = appVersions;
  }

  public List<Long> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Long> questions) {
    this.questions = questions;
  }
}
