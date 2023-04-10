package ru.hhschool.segment.model.entity;

import ru.hhschool.segment.model.enums.HistoryType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private Long userId;
  private String nameDb;
  private LocalDateTime time;
  @Enumerated(EnumType.STRING)
  private HistoryType type;
  private String description;

  public History() {
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getNameDb() {
    return nameDb;
  }

  public void setNameDb(String nameDb) {
    this.nameDb = nameDb;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public HistoryType getType() {
    return type;
  }

  public void setType(HistoryType type) {
    this.type = type;
  }

  public History(Long userId, String nameDb, LocalDateTime time, HistoryType type, String description) {
    this.userId = userId;
    this.nameDb = nameDb;
    this.time = time;
    this.type = type;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
