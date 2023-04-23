package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.AnswerDto;
import ru.hhschool.segment.model.dto.QuestionDto;
import ru.hhschool.segment.model.dto.questiondetailinfo.QuestionDtoForQuestionDetailInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.entity.Question;

import java.util.Collection;
import java.util.List;

public class QuestionMapper {
  public static QuestionDto toDto(Question entity, List<AnswerDto> answerDtoList) {
    QuestionDto questionDto = new QuestionDto();
    questionDto.setId(entity.getId());
    questionDto.setTitle(entity.getTitle());
    questionDto.setDescription(entity.getDescription());
    questionDto.setAnswerDtoList(answerDtoList);
    questionDto.setQuestionVisibilityType(entity.getQuestionVisibilityType());
    questionDto.setRequired(entity.isRequired());
    questionDto.setType(entity.getType());
    return questionDto;
  }

  public static QuestionDtoForQuestionsInfoPage toDtoForQuestionsInfo(QuestionDto questionDto) {
    QuestionDtoForQuestionsInfoPage questionDtoForQuestionsInfoPage = new QuestionDtoForQuestionsInfoPage();
    questionDtoForQuestionsInfoPage.setId(questionDto.getId());
    questionDtoForQuestionsInfoPage.setTitle(questionDto.getTitle());
    questionDtoForQuestionsInfoPage.setDescription(questionDto.getDescription());
    questionDtoForQuestionsInfoPage.setAnswerDtoList(AnswerMapper.toDtoListForQuestionsInfo(questionDto.getAnswerDtoList()));
    return questionDtoForQuestionsInfoPage;
  }

  public static List<QuestionDtoForQuestionsInfoPage> toDtoListForQuestionsInfo(Collection<QuestionDto> questionDtoCollection) {

    return questionDtoCollection
        .stream()
        .map(QuestionMapper::toDtoForQuestionsInfo)
        .toList();
  }

  public static QuestionDtoForQuestionDetailInfo toDtoForQuestionDetailInfo(QuestionDto questionDto) {
    QuestionDtoForQuestionDetailInfo questionDtoForQuestionDetailInfo = new QuestionDtoForQuestionDetailInfo();
    questionDtoForQuestionDetailInfo.setId(questionDto.getId());
    questionDtoForQuestionDetailInfo.setTitle(questionDto.getTitle());
    questionDtoForQuestionDetailInfo.setDescription(questionDto.getDescription());
    questionDtoForQuestionDetailInfo.setAnswerDtoList(AnswerMapper.toDtoListForQuestionDetailInfo(questionDto.getAnswerDtoList()));
    questionDtoForQuestionDetailInfo.setQuestionVisibilityType(questionDto.getQuestionVisibilityType());
    questionDtoForQuestionDetailInfo.setRequired(questionDto.isRequired());
    questionDtoForQuestionDetailInfo.setType(questionDto.getType());
    return questionDtoForQuestionDetailInfo;
  }

  public static List<QuestionDtoForQuestionDetailInfo> toDtoListForQuestionDetailInfo(Collection<QuestionDto> questionDtoCollection) {

    return questionDtoCollection
        .stream()
        .map(QuestionMapper::toDtoForQuestionDetailInfo)
        .toList();
  }

}

