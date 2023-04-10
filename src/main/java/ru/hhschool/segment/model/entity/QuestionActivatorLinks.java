package ru.hhschool.segment.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class QuestionActivatorLinks implements Serializable {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @ManyToOne
  private Layer layer;
  @ManyToOne
  private Segment segment;
  @ManyToOne
  private Entrypoint entrypoint;
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Question question;
  private boolean questionRequired;
  private boolean questionVisibility;
}
