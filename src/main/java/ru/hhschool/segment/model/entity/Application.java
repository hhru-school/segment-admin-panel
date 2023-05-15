package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.StateType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "applications")
public class Application {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "application_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description", nullable = false)
  private String description;
  @Type(type = "list-array")
  @Column(
          name = "platforms",
          columnDefinition = "bigint[]"
  )
  private List<Long> platformsList;
  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false)
  private StateType state;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
          name = "screen_applications",
          joinColumns = { @JoinColumn(name = "screen_id") },
          inverseJoinColumns = { @JoinColumn(name = "application_id") }
  )
  List<Screen> screens;

  public Application(Long id, String title, String description, List<Long> platformsList, StateType state, List<Screen> screens) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.platformsList = platformsList;
    this.state = state;
    this.screens = screens;
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

  public List<Long> getPlatformsList() {
    return platformsList;
  }

  public void setPlatformsList(List<Long> platformsList) {
    this.platformsList = platformsList;
  }

  public StateType getState() {
    return state;
  }

  public void setState(StateType state) {
    this.state = state;
  }

  public List<Screen> getScreens() {
    return screens;
  }

  public void setScreens(List<Screen> screens) {
    this.screens = screens;
  }
}
