package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.model.enums.StateType;

@Entity
@Table(name = "screens")
public class Screen {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "screen_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "type", nullable = false)
  private ScreenType type;
  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false)
  private StateType state;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinTable(
      name = "screen_applications",
      joinColumns = {@JoinColumn(name = "application_id")},
      inverseJoinColumns = {@JoinColumn(name = "screen_id")}
  )
  private Application application;

  public Screen() {
  }

  public Screen(Long id, String title, String description, ScreenType type, StateType state, Application application) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.type = type;
    this.state = state;
    this.application = application;
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

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public Application getApplication() {
    return application;
  }

  public void setApplications(Application application) {
    this.application = application;
  }
}
