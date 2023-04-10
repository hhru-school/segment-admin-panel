package ru.hhschool.segment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class QuestionActivatorLinks {
  @Id
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @ManyToOne
  Layer layer;
  @ManyToOne
  Segment segment;
  @ManyToOne
  Entrypoint entrypoint;
  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  Question question;
  private boolean required;
  private boolean visibility;
}
