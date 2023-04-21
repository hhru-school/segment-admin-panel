package ru.hhschool.segment.mapper.questionsinfo;

import ru.hhschool.segment.model.dto.questioninfo.AnswerDtoForQuestionsPage;
import ru.hhschool.segment.model.dto.questioninfo.QuestionDtoForQuestionsPage;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapperForQuestionsInfo {
  public static QuestionDtoForQuestionsPage toDto(Question entity, List<AnswerDtoForQuestionsPage> answerDtoList) {
    QuestionDtoForQuestionsPage questionDto = new QuestionDtoForQuestionsPage();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setDescription(entity.getDescription());
    questionDto.setAnswerDtoList(answerDtoList);
    return questionDto;
  }
}
