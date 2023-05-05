package ru.hhschool.segment.model.entity;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.QuestionType;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "questions")
public class Question implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "question_title", nullable = false)
  private String title;
  @Column(name = "description")
  private String description;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_type", nullable = false)
  private QuestionType type;
  @Column(name = "question_required")
  private boolean required;
  @Enumerated(EnumType.STRING)
  @Column(name = "question_visibility")
  private QuestionVisibilityType questionVisibilityType;
  @Column(name = "resume_field")
  private boolean resumeField;
  @OneToMany(mappedBy = "question")
  private List<Answer> answerList;
}
