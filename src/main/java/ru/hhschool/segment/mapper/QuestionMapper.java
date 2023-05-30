package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapper {

  public static QuestionDtoForQuestionsInfo toDtoForQuestionsInfo(Question question, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    QuestionDtoForQuestionsInfo questionDtoForQuestionsInfo = new QuestionDtoForQuestionsInfo();
    questionDtoForQuestionsInfo.setId(question.getId());
    questionDtoForQuestionsInfo.setTitle(question.getTitle());
    questionDtoForQuestionsInfo.setDescription(question.getDescription());
    questionDtoForQuestionsInfo.setType(question.getType());
    questionDtoForQuestionsInfo.setAnswersType(question.getAnswerType());
    questionDtoForQuestionsInfo.setAnswerDtoList(answerDtoList);
    return questionDtoForQuestionsInfo;
  }
}
