import java.time.LocalDateTime;
import java.util.List;

public class Layer {
    private Long id;
    private Layer parentLayerId;
    private String title;
    private String description;
    private boolean stable;
    private boolean deleted;
    private LocalDateTime created_time;

    private List<SegmentEntrypoint> segmentEntrypointList;
    private List<Segment> segmentList;
    private List<Role> roleList;
    private List<Question> questionList;
    private List<Answer> answerList;
    private List<QuestionActivateLink> questionActivateLinkList;
}
