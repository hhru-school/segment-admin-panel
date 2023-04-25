package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapper {

  public static QuestionDtoForQuestionsInfo toDtoForQuestionsInfo(Question question, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    QuestionDtoForQuestionsInfo questionDtoForQuestionsInfoPage = new QuestionDtoForQuestionsInfo();
    questionDtoForQuestionsInfoPage.setId(question.getId());
    questionDtoForQuestionsInfoPage.setTitle(question.getTitle());
    questionDtoForQuestionsInfoPage.setDescription(question.getDescription());
    questionDtoForQuestionsInfoPage.setAnswerDtoList(answerDtoList);
    questionDtoForQuestionsInfoPage.setType(question.getType());
    return questionDtoForQuestionsInfoPage;
  }
}
