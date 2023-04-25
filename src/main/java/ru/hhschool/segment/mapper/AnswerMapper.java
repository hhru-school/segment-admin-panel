package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Answer;

import java.util.List;

public class AnswerMapper {
  public static AnswerDtoForQuestionsInfo toDtoForQuestionsInfo(Answer entity, List<QuestionDtoForQuestionsInfo> openQuestionDtoList) {
    AnswerDtoForQuestionsInfo answerDtoForQuestionsInfoPage = new AnswerDtoForQuestionsInfo();
    answerDtoForQuestionsInfoPage.setId(entity.getId());
    answerDtoForQuestionsInfoPage.setTitle(entity.getTitle());
    answerDtoForQuestionsInfoPage.setOpenQuestonDtoList(openQuestionDtoList);
    answerDtoForQuestionsInfoPage.setDefault(entity.isDefault());
    answerDtoForQuestionsInfoPage.setAnswerType(entity.getAnswerType());
    answerDtoForQuestionsInfoPage.setPositiveTitle(entity.getPositiveTitle());
    answerDtoForQuestionsInfoPage.setSkipAtResult(entity.isSkipAtResult());
    return answerDtoForQuestionsInfoPage;
  }
}
