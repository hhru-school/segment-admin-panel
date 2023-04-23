package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.entity.Answer;

import java.util.Collection;
import java.util.List;

public class AnswerMapper {
  public static AnswerDto toDto(Answer entity, List<QuestionDto> openQuestionDtoList) {
    AnswerDto answerDto = new AnswerDto();
    answerDto.setId(entity.getId());
    answerDto.setTitle(entity.getTitle());
    answerDto.setOpenQuestonDtoList(openQuestionDtoList);
    return answerDto;
  }

  public static AnswerDtoForQuestionsInfoPage toDtoForQuestionsInfo(Answer entity, List<QuestionDto> openQuestionDtoList) {
    AnswerDto answerDto = toDto(entity, openQuestionDtoList);
    AnswerDtoForQuestionsInfoPage answerDtoForQuestionsInfoPage = new AnswerDtoForQuestionsInfoPage();
    answerDtoForQuestionsInfoPage.setId(answerDto.getId());
    answerDtoForQuestionsInfoPage.setTitle(answerDto.getTitle());
    answerDtoForQuestionsInfoPage.setOpenQuestonDtoList(QuestionMapper.toDtoListForQuestionsInfo(openQuestionDtoList));
    return answerDtoForQuestionsInfoPage;

  }

  public static AnswerDtoForQuestionsInfoPage toDtoForQuestionsInfo(AnswerDto answerDto) {
    AnswerDtoForQuestionsInfoPage answerDtoForQuestionsInfoPage = new AnswerDtoForQuestionsInfoPage();
    answerDtoForQuestionsInfoPage.setId(answerDto.getId());
    answerDtoForQuestionsInfoPage.setTitle(answerDto.getTitle());
    answerDtoForQuestionsInfoPage.setOpenQuestonDtoList(QuestionMapper.toDtoListForQuestionsInfo(answerDto.getOpenQuestonDtoList()));
    return answerDtoForQuestionsInfoPage;

  }

  public static List<AnswerDtoForQuestionsInfoPage> toDtoListForQuestionsInfo(Collection<AnswerDto> answerDtoCollection) {

    return answerDtoCollection
        .stream()
        .map(AnswerMapper::toDtoForQuestionsInfo)
        .toList();
  }
}
