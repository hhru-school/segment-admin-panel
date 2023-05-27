package ru.hhschool.segment.model.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
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
  @Type(type = "list-array")
  @Column(
      name = "platforms",
      columnDefinition = "bigint[]"
  )
  private List<Long> platforms;

  public Screen() {
  }

  public Screen(String title, String description, ScreenType type, StateType state, List<Long> platforms) {
    this.title = title;
    this.description = description;
    this.type = type;
    this.state = state;
    this.platforms = platforms;
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

  public List<Long> getPlatforms() {
    return platforms;
  }

  public void setPlatforms(List<Long> platforms) {
    this.platforms = platforms;
  }
}
