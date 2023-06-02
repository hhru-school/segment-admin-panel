package ru.hhschool.segment.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.inject.Inject;
import ru.hhschool.segment.dao.abstracts.AnswerDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.mapper.AnswerMapper;
import ru.hhschool.segment.mapper.QuestionMapper;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;
import ru.hhschool.segment.model.entity.Question;

public class AnswerService {
  private final AnswerDao answerDao;
  private final QuestionDao questionDao;

  @Inject
  public AnswerService(AnswerDao answerDao, QuestionDao questionDao) {
    this.answerDao = answerDao;
    this.questionDao = questionDao;
  }

  public List<AnswerDtoForQuestionsInfo> getAllAnswerDtoListByListId(List<Long> answersIdList, List<Question> questionList, int depth) {
    if (answersIdList == null) {
      return Collections.emptyList();
    }
    return answersIdList.stream()
        .map(answerDao::findById)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(answer -> AnswerMapper.toDtoForQuestionsInfo(answer, getAllOpenQuestionForAnswer(answer.getOpenQuestions(), questionList, depth)))
        .collect(Collectors.toList());
  }

  public List<QuestionDtoForQuestionsInfo> getAllOpenQuestionForAnswer(List<Long> openQuestionIdList, List<Question> questionList,int depth) {
    if (openQuestionIdList == null|| depth <= 0) {
      return Collections.emptyList();
    }
    return questionList.stream()
        .filter(question -> openQuestionIdList.contains(question.getId()))
        .map(question -> QuestionMapper.questionToDto(question, getAllAnswerDtoListByListId(question.getPossibleAnswers(), questionList, depth-1)))
        .toList();
  }
}
