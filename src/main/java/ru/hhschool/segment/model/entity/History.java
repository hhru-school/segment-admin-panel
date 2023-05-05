package ru.hhschool.segment.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import ru.hhschool.segment.model.enums.HistoryType;

@Entity
@Table(name = "history")
public class History {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "history_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "user_id")
  private Long userId;
  @Column(name = "name_db")
  private String nameDb;
  @Column(name = "time", nullable = false)
  private LocalDateTime time;
  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private HistoryType type;
  @Column(name = "description")
  private String description;
}
