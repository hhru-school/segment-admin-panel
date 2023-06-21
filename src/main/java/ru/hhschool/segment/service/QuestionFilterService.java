package ru.hhschool.segment.service;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionFilterService {
  Map<Long, QuestionDtoForQuestionsInfo> filtredQuestionDtoMap = new HashMap<>();

  public List<QuestionDtoForQuestionsInfo> filterQuestionDtoListByString(String searchQuery, List<QuestionDtoForQuestionsInfo> questionDtoList) {
    filtredQuestionDtoMap.clear();
    String searchStringLower = searchQuery.toLowerCase().trim();
    questionDtoList.forEach(questionDto -> {
      checkQuestion(questionDto, searchStringLower);
      checkAnswer(questionDto, questionDto, searchStringLower);
    });

    return new ArrayList<>(filtredQuestionDtoMap.values());
  }

  public void checkQuestion(QuestionDtoForQuestionsInfo questionDto, String searchStringLower) {
    String titleLower = questionDto.getTitle().toLowerCase().trim();
    if (titleLower.contains(searchStringLower)) {
      questionDto.setSearchedObject(true);
      filtredQuestionDtoMap.putIfAbsent(questionDto.getId(), questionDto);
    }
  }

  public void checkAnswer(QuestionDtoForQuestionsInfo questionDto, QuestionDtoForQuestionsInfo mainQuestionDto, String searchStringLower) {
    questionDto.getPossibleAnswersList().forEach(answerDto -> {
      String titleLower = answerDto.getTitle().toLowerCase().trim();
      if (titleLower.contains(searchStringLower)) {
        answerDto.setSearchedObject(true);
        filtredQuestionDtoMap.putIfAbsent(mainQuestionDto.getId(), mainQuestionDto);
      }
      checkOpenQuestion(answerDto, questionDto, searchStringLower);
    });
  }

  public void checkOpenQuestion(AnswerDtoForQuestionsInfo answerDto, QuestionDtoForQuestionsInfo mainQuestionDto, String searchStringLower) {
    answerDto.getOpenQuestionList().forEach(questionDto -> {
      String titleLower = questionDto.getTitle().toLowerCase().trim();
      if (titleLower.contains(searchStringLower)) {
        questionDto.setSearchedObject(true);
        filtredQuestionDtoMap.putIfAbsent(mainQuestionDto.getId(), mainQuestionDto);
      }
      checkAnswer(questionDto, mainQuestionDto, searchStringLower);
    });
  }
}
