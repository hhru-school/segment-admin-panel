package ru.hhschool.segment.mapper.change;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.hhschool.segment.model.dto.change.AnswerChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.entity.Answer;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.ConflictStatus;

/**
 * в этих методах идет формирование и группировка изменений в сущностях,
 * на их основании заполняем layerChangeDto
 * setEntrypointChange(layer, layerChangeDto);
 * setSegmentChange(layer, layerChangeDto);
 * setQuestionAndAnswerChange(layer, layerChangeDto);
 * setQuestionActivatorLinkChange(layer, layerChangeDto);
 */
public class LayerChangeMapper {

  public static LayerChangeDto layerChangeToDto(Layer layer, ConflictStatus conflict) {
    Long parentId = layer.getParent() == null ? null : layer.getParent().getId();
    LayerChangeDto layerChangeDto = new LayerChangeDto(
        layer.getId(),
        parentId,
        conflict.isConflict()
    );

    setEntrypointChange(layer, layerChangeDto);
    setSegmentChange(layer, layerChangeDto);
    setQuestionAndAnswerChange(layer, layerChangeDto);
    setQuestionActivatorLinkChange(layer, layerChangeDto);
    setUsedEntrypointTitleMap(layer, layerChangeDto);

    return layerChangeDto;
  }

  private static void setQuestionActivatorLinkChange(Layer layer, LayerChangeDto layerChangeDto) {

  }

  private static void setUsedEntrypointTitleMap(Layer layer, LayerChangeDto layerChangeDto) {

  }

  private static void setQuestionAndAnswerChange(Layer layer, LayerChangeDto layerChangeDto) {

  }

  private static Map<Long, QuestionChangeDto> questionListToMap(List<Question> questionList) {
    Map<Long, QuestionChangeDto> questionMap = Map.of();
    if (questionList != null && questionList.size() > 0) {
      questionMap = questionList
          .stream()
          .map(QuestionChangeMapper::questionChangeToDto)
          .collect(Collectors.toMap(QuestionChangeDto::getId, questionDto -> questionDto));
    }
    return questionMap;
  }

  private static Map<Long, AnswerChangeDto> answerListToMap(List<Answer> answerList) {
    Map<Long, AnswerChangeDto> answerMap = Map.of();
    if (answerList != null && answerList.size() > 0) {
      answerMap = answerList
          .stream()
          .map(AnswerChangeMapper::answerChangeToDto)
          .collect(Collectors.toMap(AnswerChangeDto::getId, answerDto -> answerDto));
    }
    return answerMap;
  }

  private static void setAllAnswerToQuestion(
      List<Question> questionList,
      Map<Long, AnswerChangeDto> answerMap,
      Map<Long, QuestionChangeDto> questionMap,
      List<Long> questionUsedId,
      List<Long> answerUsedId,
      List<QuestionChangeDto> questionChangeList
  ) {
    if (questionList == null) {
      return;
    }
    for (Question question : questionList) {
      Long questionId = question.getId();
      QuestionChangeDto questionChangeDto = questionMap.get(questionId);
      if (question.getPossibleAnswerIdList() != null) {
        for (Long answerId : question.getPossibleAnswerIdList()) {
          if (answerMap.containsKey(answerId)) {
            answerUsedId.add(answerId);
            questionChangeDto.getAnswerList().add(answerMap.get(answerId));
          }
        }
      }
      questionMap.put(questionId, questionChangeDto);

      if (!questionUsedId.contains(questionId)) {
        questionChangeList.add(questionChangeDto);
      }
    }
  }

  private static void setAllOpenQuestionToAnswer(
      List<Answer> answerList,
      Map<Long, AnswerChangeDto> answerMap,
      Map<Long, QuestionChangeDto> questionMap,
      List<Long> questionUsedId
  ) {
    if (answerList == null) {
      return;
    }
    for (Answer answer : answerList) {
      List<Long> questionIdList = answer.getOpenQuestionList();
      Long answerId = answer.getId();
      AnswerChangeDto answerChangeDto = answerMap.get(answerId);
      if (questionIdList != null) {
        for (Long questionId : questionIdList) {
          if (questionMap.containsKey(questionId)) {
            answerChangeDto.getOpenQuestionList().add(questionMap.get(questionId));
            questionUsedId.add(questionId);
          }
        }
      }
      answerMap.put(answerId, answerChangeDto);
    }
  }

  private static void setSegmentChange(Layer layer, LayerChangeDto layerChangeDto) {

  }

  private static void setEntrypointChange(Layer layer, LayerChangeDto layerChangeDto) {

  }

}
