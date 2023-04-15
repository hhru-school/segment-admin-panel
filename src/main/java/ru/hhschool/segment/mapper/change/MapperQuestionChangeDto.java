package ru.hhschool.segment.mapper.change;

import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class MapperQuestionChangeDto {
    public static QuestionChangeDto questionChangeToDto(Question question) {
        QuestionChangeDto questionChangeDto = new QuestionChangeDto(
                question.getId(),
                question.getTitle(),
                question.getDescription(),
                new ArrayList<>()
        );

        return questionChangeDto;
    }

    public static List<QuestionChangeDto> questionChangeListToDtoList(List<Question> questionList) {
        return questionList
                .stream()
                .map(MapperQuestionChangeDto::questionChangeToDto)
                .toList();
    }
}
