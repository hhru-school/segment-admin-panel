package ru.hhschool.segment.service;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionFilterService {
  Map<String, QuestionDtoForQuestionsInfo> filtredQuestionDtoMap = new HashMap<>();

  public List<QuestionDtoForQuestionsInfo> filterQuestionDtoListByString(String searchQuery, List<QuestionDtoForQuestionsInfo> questionDtoList) {
    filtredQuestionDtoMap.clear();
    String searchStringLower = searchQuery.toLowerCase().trim();
    questionDtoList.forEach(questionDto -> {
      checkQuestion(questionDto, searchStringLower);
      checkAnswer(questionDto, searchStringLower);
    });

    return new ArrayList<>(filtredQuestionDtoMap.values());
  }

  public void checkQuestion(QuestionDtoForQuestionsInfo questionDto, String searchStringLower) {
    String titleLower = questionDto.getTitle().toLowerCase().trim();

    if (titleLower.contains(searchStringLower)) {
      questionDto.setSearchedObject(true);
      filtredQuestionDtoMap.putIfAbsent(questionDto.getTitle(), questionDto);
    }
  }

  public void checkAnswer(QuestionDtoForQuestionsInfo questionDto, String searchStringLower) {
    questionDto.getPossibleAnswersList().forEach(answerDto -> {
      String titleLower = answerDto.getTitle().toLowerCase().trim();
      if (titleLower.contains(searchStringLower)) {
        answerDto.setSearchedObject(true);
        filtredQuestionDtoMap.putIfAbsent(questionDto.getTitle(), questionDto);
      }
      checkOpenQuestion(answerDto, questionDto, searchStringLower);
    });
  }

  public void checkOpenQuestion(AnswerDtoForQuestionsInfo answerDto, QuestionDtoForQuestionsInfo mainQuestionDto, String searchStringLower) {
    answerDto.getOpenQuestionList().forEach(questionDto -> {
      String titleLower = questionDto.getTitle().toLowerCase().trim();

      if (titleLower.contains(searchStringLower)) {
        questionDto.setSearchedObject(true);
        filtredQuestionDtoMap.putIfAbsent(mainQuestionDto.getTitle(), mainQuestionDto);
      }
      checkAnswer(questionDto, searchStringLower);
    });
  }
}
