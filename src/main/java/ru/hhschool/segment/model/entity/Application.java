package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.StateType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "applications")
public class Application implements Serializable {
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
  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
          name = "screen_applications",
          joinColumns = { @JoinColumn(name = "screen_id") },
          inverseJoinColumns = { @JoinColumn(name = "application_id") }
  )
  Set<Screen> screens;

  public Application(Long id, String title, String description, List<Long> platformsList, StateType state, Set<Screen> screens) {
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

  public Set<Screen> getScreens() {
    return screens;
  }

  public void setScreens(Set<Screen> screens) {
    this.screens = screens;
  }
}
