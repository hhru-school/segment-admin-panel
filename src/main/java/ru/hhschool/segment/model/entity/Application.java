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
  private List<Long> platformList;
  @Enumerated(EnumType.STRING)
  @Column(name = "state", nullable = false)
  private StateType state;
  @ManyToMany(mappedBy = "applications")
  List<Screen> screens;

  public Application() {}

  public Application(Long id, String title, String description, List<Long> platformList, StateType state, List<Screen> screens) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.platformList = platformList;
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

  public List<Long> getPlatformList() {
    return platformList;
  }

  public void setPlatformList(List<Long> platformList) {
    this.platformList = platformList;
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
