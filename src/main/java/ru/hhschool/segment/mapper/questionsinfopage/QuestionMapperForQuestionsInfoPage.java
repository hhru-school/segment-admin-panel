package ru.hhschool.segment.mapper.questionsinfopage;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapperForQuestionsInfoPage {
  public static QuestionDtoForQuestionsInfoPage toDto(Question entity, List<AnswerDtoForQuestionsInfoPage> answerDtoList) {
    QuestionDtoForQuestionsInfoPage questionDto = new QuestionDtoForQuestionsInfoPage();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setDescription(entity.getDescription());
    questionDto.setAnswerDtoList(answerDtoList);
    return questionDto;
  }

}
