package ru.hhschool.segment.model.dto.questiondetailinfo;

import java.util.List;

public class QuestionDtoForQuestionDetailInfo {
  private Long id;
  private String title;
  private String description;
  private List<AnswerDtoForQuestionDetailInfo> answerDtoList;
}
