import java.time.LocalDateTime;
import java.util.List;

public class Commit {
  private Long id;
  private Commit parentCommitId;
  private String title;
  private String description;
  private boolean stable;
  private boolean deleted;
  private LocalDateTime created_time;

  private List<SegmentEntrypoint> segmentEntrypointList;
  private List<Segment> segmentList;
  private List<Question> questionList;
  private List<Answer> answerList;
  private List<QuestionActivateLink> questionActivateLinkList;
}
