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

  public AnswerDtoForQuestionDetailInfo() {
  }

  public AnswerDtoForQuestionDetailInfo(Long id, String title, List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList, String positiveTitle, AnswerType answerType, boolean isDefault, boolean skipAtResult) {
    this.id = id;
    this.title = title;
    this.openQuestonDtoList = openQuestonDtoList;
    this.positiveTitle = positiveTitle;
    this.answerType = answerType;
    this.isDefault = isDefault;
    this.skipAtResult = skipAtResult;
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

  public List<QuestionDtoForQuestionsInfoPage> getOpenQuestonDtoList() {
    return openQuestonDtoList;
  }

  public void setOpenQuestonDtoList(List<QuestionDtoForQuestionsInfoPage> openQuestonDtoList) {
    this.openQuestonDtoList = openQuestonDtoList;
  }

  public String getPositiveTitle() {
    return positiveTitle;
  }

  public void setPositiveTitle(String positiveTitle) {
    this.positiveTitle = positiveTitle;
  }

  public AnswerType getAnswerType() {
    return answerType;
  }

  public void setAnswerType(AnswerType answerType) {
    this.answerType = answerType;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
  }

  public boolean isSkipAtResult() {
    return skipAtResult;
  }

  public void setSkipAtResult(boolean skipAtResult) {
    this.skipAtResult = skipAtResult;
  }
}
