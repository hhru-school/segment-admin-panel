package ru.hhschool.segment.mapper.questionsinfo;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.dto.questioninfo.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfo.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapperForQuestionsInfo {
  public static QuestionDtoForQuestionsInfo toDto(Question entity, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    QuestionDtoForQuestionsInfo  questionDto = new QuestionDtoForQuestionsInfo ();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setAnswerDtoList(answerDtoList);
    return questionDto;
  }
}
