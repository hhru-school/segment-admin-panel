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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.hhschool.segment.model.enums.AnswerType;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "answers")
public class Answer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id", nullable = false, unique = true)
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "positive_title")
  private String positiveTitle;
  @Enumerated(EnumType.STRING)
  @Column(name = "answer_type")
  private AnswerType answerType;
  @Column(name = "is_default_answer", nullable = false)
  private boolean isDefault;
  @Column(name = "skip_at_result")
  private boolean skipAtResult;
  @Type(type = "list-array")
  @Column(
      name = "open_questions",
      columnDefinition = "bigint[]"
  )
  private List<Long> openQuestionList;
  @ManyToOne(fetch = FetchType.LAZY)
  private Question question;
}
