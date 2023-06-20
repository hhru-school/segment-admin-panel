package ru.hhschool.segment.mapper.question;

import java.util.List;
import ru.hhschool.segment.model.dto.question.QuestionCreateDto;
import java.util.List;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

import java.util.List;

public class QuestionMapper {

  public static QuestionDtoForQuestionsInfo questionToDto(Question question, List<AnswerDtoForQuestionsInfo> answerDtoList) {
    QuestionDtoForQuestionsInfo questionDtoForQuestionsInfo = new QuestionDtoForQuestionsInfo();
    questionDtoForQuestionsInfo.setId(question.getId());
    questionDtoForQuestionsInfo.setTitle(question.getTitle());
    questionDtoForQuestionsInfo.setDescription(question.getDescription());
    questionDtoForQuestionsInfo.setType(question.getType());
    questionDtoForQuestionsInfo.setType(question.getType());
    questionDtoForQuestionsInfo.setAnswersType(question.getAnswerType());
    questionDtoForQuestionsInfo.setPossibleAnswersList(answerDtoList);
    return questionDtoForQuestionsInfo;
  }

  public static Question fromDto(QuestionCreateDto questionCreateDto, List<Long> answers) {
    return new Question(
        questionCreateDto.getTitle(),
        questionCreateDto.getDescription(),
        questionCreateDto.getType(),
        questionCreateDto.getAnswerType(),
        answers
    );
  }
}
