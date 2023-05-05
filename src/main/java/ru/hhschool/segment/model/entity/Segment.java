package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "segments")
public class Segment implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "segment_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Type(type = "list-array")
  @Column(
      name = "role",
      columnDefinition = "bigint[]"
  )
  private List<Long> roleList;
  @Type(type = "list-array")
  @Column(
      name = "tag",
      columnDefinition = "text[]"
  )
  private List<String> tagList;
  @ManyToOne(fetch = FetchType.LAZY)
  private Segment parent;
  @Column(name = "layer_id")
  private Long layerId;
  @Column(name = "archived")
  private boolean archived;

}
