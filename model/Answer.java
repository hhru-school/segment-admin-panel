import java.util.List;

public class Answer {
  private Long id;
    private Layer layer;
    private List<Question> openQuestions;
  private String title;
  private String positiveTitle;
  private AnswerType answerType;
  private boolean isDefaultAnswer;
}