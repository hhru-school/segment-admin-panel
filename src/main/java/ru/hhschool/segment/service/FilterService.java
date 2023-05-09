package ru.hhschool.segment.service;

import com.sun.jdi.Value;
import ru.hhschool.segment.model.dto.questioninfopage.AnswerDtoForQuestionsInfo;
import ru.hhschool.segment.model.dto.questioninfopage.QuestionDtoForQuestionsInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterService {
    Map<String, QuestionDtoForQuestionsInfo> filtredQuestionDtoMap = new HashMap<>();

    public List<QuestionDtoForQuestionsInfo> filterQuestionDtoListByString(String searchString, List<QuestionDtoForQuestionsInfo> questionDtoList) {
        filtredQuestionDtoMap.clear();
        String searchStringLower = searchString.toLowerCase();
        questionDtoList.forEach(questionDto -> {
            checkQuestion(questionDto, searchStringLower);
            checkAnswer(questionDto, searchStringLower);
        });
        List<QuestionDtoForQuestionsInfo> list = new ArrayList<QuestionDtoForQuestionsInfo>(filtredQuestionDtoMap.values());
        return list;
    }

    public void checkQuestion(QuestionDtoForQuestionsInfo questionDto, String searchStringLower) {
        String titleLower = questionDto.getTitle().toLowerCase();
        String descriptionLower = questionDto.getDescription().toLowerCase();
        if (titleLower.contains(searchStringLower) || descriptionLower.contains(searchStringLower)) {
            questionDto.setSearchedObject(true);
            filtredQuestionDtoMap.putIfAbsent(questionDto.getTitle(), questionDto);
        }
    }

    public void checkAnswer(QuestionDtoForQuestionsInfo questionDto, String searchStringLower) {
        questionDto.getAnswerDtoList().forEach(answerDto -> {
            String titleLower = answerDto.getTitle().toLowerCase();
            if (titleLower.contains(searchStringLower)) {
                answerDto.setSearchedObject(true);
                filtredQuestionDtoMap.putIfAbsent(questionDto.getTitle(), questionDto);
            }
            checkOpenQuestion(answerDto, questionDto, searchStringLower);
        });
    }

    public void checkOpenQuestion(AnswerDtoForQuestionsInfo answerDto, QuestionDtoForQuestionsInfo mainQuestionDto, String searchStringLower) {
        answerDto.getOpenQuestonDtoList().forEach(questionDto -> {
            String titleLower = questionDto.getTitle().toLowerCase();
            String descriptionLower = questionDto.getDescription().toLowerCase();
            if (titleLower.contains(searchStringLower) || descriptionLower.contains(searchStringLower)) {
                questionDto.setSearchedObject(true);
                filtredQuestionDtoMap.putIfAbsent(mainQuestionDto.getTitle(), mainQuestionDto);
            }
            checkAnswer(questionDto, searchStringLower);
        });
    }
}

