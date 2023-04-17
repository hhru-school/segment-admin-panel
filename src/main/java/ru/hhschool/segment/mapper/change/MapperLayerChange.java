package ru.hhschool.segment.mapper.change;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import ru.hhschool.segment.model.dto.change.AnswerChangeDto;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Answer;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;
import ru.hhschool.segment.model.entity.Segment;

/**
 * в этих методах идет формирование и группировка изменений в сущностях,
 * на их основании заполняем layerChangeDto
 * checkEntrypointChange(layer, layerChangeDto);
 * checkSegmentChange(layer, layerChangeDto);
 * checkQuestionAndAnswerChange(layer, layerChangeDto);
 * checkQuestionActivatorLinkChange(layer, layerChangeDto);
 */
public class MapperLayerChange {
  private enum Status {
    CREATED,
    ARCHIVED,
    NOT_LINKED
  }

  public static LayerChangeDto layerChangeToDto(Layer layer) {
    Long parentId = layer.getParent() == null ? null : layer.getParent().getId();
    LayerChangeDto layerChangeDto = new LayerChangeDto(
        layer.getId(),
        parentId,
        layer.getTitle(),
        layer.getDescription(),
        layer.isStable(),
        layer.isArchive(),
        layer.isDeleted(),
        layer.getCreateTime()
    );

    checkEntrypointChange(layer, layerChangeDto);
    checkSegmentChange(layer, layerChangeDto);
    checkQuestionAndAnswerChange(layer, layerChangeDto);
    checkQuestionActivatorLinkChange(layer, layerChangeDto);

    return layerChangeDto;
  }

  private static void checkQuestionActivatorLinkChange(Layer layer, LayerChangeDto layerChangeDto) {
    List<QuestionActivatorLink> activatorLinkList = layer.getQuestionActivatorLinksList();
    if (activatorLinkList != null && activatorLinkList.size() > 0) {
      Map<String, List<QuestionActivatorLinkChangeDto>> segmentGroupMap = activatorLinkList
          .stream()
          .map(MapperQuestionActivatorLinkChange::questionActivatorLinkToDto)
          .collect(
              Collectors.groupingBy(QuestionActivatorLinkChangeDto::getSegmentTitle)
          );

      layerChangeDto.setQuestionActivatorLinkMap(segmentGroupMap);
    }
  }

  private static void checkQuestionAndAnswerChange(Layer layer, LayerChangeDto layerChangeDto) {
    List<Answer> answerList = layer.getAnswerList();
    Map<Long, AnswerChangeDto> answerMap = answerListToMap(answerList);

    List<Question> questionList = layer.getQuestionList();
    Map<Long, QuestionChangeDto> questionMap = questionListToMap(questionList);

    List<Long> questionUsedId = new ArrayList<>();
    List<Long> answerUsedId = new ArrayList<>();
    List<QuestionChangeDto> questionChangeList = new ArrayList<>();

    setAllOpenQuestionToAnswer(answerList, answerMap, questionMap, questionUsedId);
    setAllAnswerToQuestion(questionList, answerMap, questionMap, questionUsedId, answerUsedId, questionChangeList);

    if (questionChangeList.size() != 0) {
      Map<String, List<QuestionChangeDto>> questionChangeMap = new HashMap<>();
      questionChangeMap.put(Status.CREATED.name(), questionChangeList);
      layerChangeDto.setQuestionMap(questionChangeMap);
    }
    if (answerList.size() != 0) {
      Map<String, List<AnswerChangeDto>> answerChangeMap = new HashMap<>();
      List<AnswerChangeDto> anwerChangeList = answerMap.values()
          .stream()
          .filter(answer -> !answerUsedId.contains(answer.getId()))
          .toList();
      answerChangeMap.put(Status.NOT_LINKED.name(), anwerChangeList);
      layerChangeDto.setAnswerMap(answerChangeMap);
    }
  }

  private static Map<Long, QuestionChangeDto> questionListToMap(List<Question> questionList) {
    Map<Long, QuestionChangeDto> questionMap = Map.of();
    if (questionList != null && questionList.size() > 0) {
      questionMap = questionList
          .stream()
          .map(MapperQuestionChange::questionChangeToDto)
          .collect(Collectors.toMap(QuestionChangeDto::getId, questionDto -> questionDto));
    }
    return questionMap;
  }

  private static Map<Long, AnswerChangeDto> answerListToMap(List<Answer> answerList) {
    Map<Long, AnswerChangeDto> answerMap = Map.of();
    if (answerList != null && answerList.size() > 0) {
      answerMap = answerList
          .stream()
          .map(MapperAnswerChange::answerChangeToDto)
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

  private static void checkSegmentChange(Layer layer, LayerChangeDto layerChangeDto) {
    List<Segment> segmentList = layer.getSegmentList();
    if (segmentList != null && segmentList.size() > 0) {
      Map<String, List<SegmentChangeDto>> segmentMap = new HashMap<>();

      List<Segment> segmentCreatedList = segmentList
          .stream()
          .filter(segment -> !segment.isArchived())
          .toList();
      segmentMap.put(Status.CREATED.name(), MapperSegmentChange.segmentChangeListToDtoList(segmentCreatedList));

      List<Segment> segmentDisableList = segmentList
          .stream()
          .filter(Segment::isArchived)
          .toList();
      segmentMap.put(Status.ARCHIVED.name(), MapperSegmentChange.segmentChangeListToDtoList(segmentDisableList));

      layerChangeDto.setSegmentMap(segmentMap);
    }
  }

  private static void checkEntrypointChange(Layer layer, LayerChangeDto layerChangeDto) {
    List<Entrypoint> entrypointList = layer.getEntrypointList();
    Hibernate.initialize(layer.getEntrypointList());
    if (entrypointList != null && entrypointList.size() > 0) {
      Map<String, List<EntrypointChangeDto>> entrypointMap = new HashMap<>();
      entrypointMap.put(Status.CREATED.name(), MapperEntrypointChange.entrypointChangeListToDtoList(entrypointList));
      layerChangeDto.setEntrypointMap(entrypointMap);
    }
  }


}
