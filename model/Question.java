import java.util.List;

public class Question {
  private Long id;
  private Commit commit;
  private String title;
  private String description;
  private QuestionType questionType;
  private Boolean active;
  private boolean required;
  private List<Answer> possibleAnswers;
}