package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
public class Answer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  private String title;
  private String positiveTitle;
  private String Type;
  private boolean isDefault;
  private boolean skipAtResult;
  @ManyToOne
  private Layer layer;
  @Type(type = "list-array")
  @Column(
      name = "openquestionlist",
      columnDefinition = "bigint[]"
  )
  private List<Long> openQuestionList;

  public Answer(String title, String positiveTitle, String type, boolean isDefault, boolean skipAtResult, Layer layer, List<Long> openQuestionList) {
    this.title = title;
    this.positiveTitle = positiveTitle;
    Type = type;
    this.isDefault = isDefault;
    this.skipAtResult = skipAtResult;
    this.layer = layer;
    this.openQuestionList = openQuestionList;
  }

  public Answer() {
  }

  public String getType() {
    return Type;
  }

  public void setType(String type) {
    Type = type;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
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

  public String getPositiveTitle() {
    return positiveTitle;
  }

  public void setPositiveTitle(String positiveTitle) {
    this.positiveTitle = positiveTitle;
  }

  public boolean isSkipAtResult() {
    return skipAtResult;
  }

  public void setSkipAtResult(boolean skipAtResult) {
    this.skipAtResult = skipAtResult;
  }

  public Layer getLayer() {
    return layer;
  }

  public void setLayer(Layer layer) {
    this.layer = layer;
  }

  public List<Long> getOpenQuestionList() {
    return openQuestionList;
  }

  public void setOpenQuestionList(List<Long> openQuestionList) {
    this.openQuestionList = openQuestionList;
  }
}
