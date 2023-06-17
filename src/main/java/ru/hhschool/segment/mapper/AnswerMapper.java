package ru.hhschool.segment.mapper;

import java.util.List;
import ru.hhschool.segment.model.dto.question.AnswerCreateDto;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Answer;

public class AnswerMapper {
  public static AnswerDtoForQuestionsInfo toDtoForQuestionsInfo(Answer entity, List<QuestionDtoForQuestionsInfo> openQuestionDtoList) {
    AnswerDtoForQuestionsInfo answerDtoForQuestionsInfoPage = new AnswerDtoForQuestionsInfo();
    answerDtoForQuestionsInfoPage.setId(entity.getId());
    answerDtoForQuestionsInfoPage.setTitle(entity.getTitle());
    answerDtoForQuestionsInfoPage.setOpenQuestionList(openQuestionDtoList);
    answerDtoForQuestionsInfoPage.setDefaultAnswer(entity.isDefaultAnswer());
    answerDtoForQuestionsInfoPage.setAnswerType(entity.getType());
    answerDtoForQuestionsInfoPage.setPositiveTitle(entity.getPositiveTitle());
    answerDtoForQuestionsInfoPage.setSkipAtResult(entity.isSkipAtResult());
    return answerDtoForQuestionsInfoPage;
  }

  public static Answer fromDto(AnswerCreateDto answerCreateDto, List<Long> openQuestions) {
    return new Answer(
        answerCreateDto.getTitle(),
        answerCreateDto.getPositiveTitle(),
        answerCreateDto.getType(),
        answerCreateDto.isDefaultAnswer(),
        answerCreateDto.isSkipAtResult(),
        openQuestions
    );
  }
}
