package ru.hhschool.segment.model.dto.questiondetailinfo;

import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.enums.AnswerType;

import java.util.List;

public class AnswerDtoForQuestionDetailInfo {
  private Long id;
  private String title;
  private List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList;
  private String positiveTitle;
  private AnswerType answerType;
  private boolean isDefault;
  private boolean skipAtResult;
}
