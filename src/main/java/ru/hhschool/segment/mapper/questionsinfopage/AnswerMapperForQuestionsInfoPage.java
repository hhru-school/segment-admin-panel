package ru.hhschool.segment.mapper.questionsinfopage;

import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.entity.Answer;

import java.util.List;

public class AnswerMapperForQuestionsInfoPage {
  public static AnswerDtoForQuestionsInfoPage toDto(Answer entity,List<QuestionDtoForQuestionsInfoPage> openQuestionDtoList) {
    AnswerDtoForQuestionsInfoPage answerDto = new AnswerDtoForQuestionsInfoPage();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    answerDto.setOpenQuestonDtoList(openQuestionDtoList);
    return answerDto;
  }
}
