package ru.hhschool.segment.service;

import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfoPage;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfoPage;

import java.util.HashSet;
import java.util.Set;

public class FilterService {
  Set<QuestionDtoForQuestionsInfoPage> filtredQuestionDtoSet = new HashSet<>();

  public Set<QuestionDtoForQuestionsInfoPage> filterQuestionDtoSetByString(String searchString, Set<QuestionDtoForQuestionsInfoPage> questionDtoSet) {
    filtredQuestionDtoSet.clear();
    String searchStringLower = searchString.toLowerCase();
    questionDtoSet.forEach(questionDto -> {
      checkQuestion(questionDto, searchStringLower);
      checkAnswer(questionDto, searchStringLower);
    });
    return filtredQuestionDtoSet;
  }

  public void checkQuestion(QuestionDtoForQuestionsInfoPage questionDto, String searchStringLower) {
    String titleLower = questionDto.getTitle().toLowerCase();
    String descriptionLower = questionDto.getDescription().toLowerCase();
    if (titleLower.contains(searchStringLower) || descriptionLower.contains(searchStringLower)) {
      questionDto.setSearchedObject(true);
      filtredQuestionDtoSet.add(questionDto);
    }
  }

  public void checkAnswer(QuestionDtoForQuestionsInfoPage questionDto, String searchStringLower) {
    questionDto.getAnswerDtoList().forEach(answerDto -> {
      String titleLower = answerDto.getTitle().toLowerCase();
      if (titleLower.contains(searchStringLower)) {
        answerDto.setSearchedObject(true);
        filtredQuestionDtoSet.add(questionDto);
      }
      checkOpenQuestion(answerDto, questionDto, searchStringLower);
    });
  }

  public void checkOpenQuestion(AnswerDtoForQuestionsInfoPage answerDto, QuestionDtoForQuestionsInfoPage mainQuestionDto, String searchStringLower) {
    answerDto.getOpenQuestonDtoList().forEach(questionDto -> {
      String titleLower = questionDto.getTitle().toLowerCase();
      String descriptionLower = questionDto.getDescription().toLowerCase();
      if (titleLower.contains(searchStringLower) || descriptionLower.contains(searchStringLower)) {
        questionDto.setSearchedObject(true);
        filtredQuestionDtoSet.add(mainQuestionDto);
      }
      checkAnswer(questionDto,searchStringLower);
    });
  }
}

