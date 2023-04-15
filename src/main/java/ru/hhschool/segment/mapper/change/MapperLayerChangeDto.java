package ru.hhschool.segment.mapper.change;

import ru.hhschool.segment.model.dto.change.*;
import ru.hhschool.segment.model.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapperLayerChangeDto {
    public static LayerChangeDto layerChangeToDto(Layer layer) {
        LayerChangeDto layerChangeDto = new LayerChangeDto(
                layer.getId(),
                layer.getParent().getId(),
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
                    .map(v -> MapperQuestionActivatorLinkChangeDto.questionActivatorLinkToDto(v))
                    .collect(
                            Collectors.groupingBy(v -> v.getSegmentTitle())
                    );

            layerChangeDto.setQuestionActivatorLinkMap(segmentGroupMap);
        }
    }

    private static void checkQuestionAndAnswerChange(Layer layer, LayerChangeDto layerChangeDto) {
        List<Answer> answerList = layer.getAnswerList();
        Map<Long, AnswerChangeDto> answerMap = Map.of();
        if (answerList != null && answerList.size() > 0) {
            answerMap = answerList
                    .stream()
                    .map(MapperAnswerChangeDto::answerChangeToDto)
                    .collect(Collectors.toMap(AnswerChangeDto::getId, answerDto -> answerDto));
        }

        List<Question> questionList = layer.getQuestionList();
        Map<Long, QuestionChangeDto> questionMap = Map.of();
        if (questionList != null && questionList.size() > 0) {
            questionMap = questionList
                    .stream()
                    .map(MapperQuestionChangeDto::questionChangeToDto)
                    .collect(Collectors.toMap(QuestionChangeDto::getId, questionDto -> questionDto));
        }

        List<Long> questionUsedId = new ArrayList<>();
        List<QuestionChangeDto> questionChangeList = new ArrayList<>();

        setAllOpenQuestionToAnswer(answerList, answerMap, questionMap, questionUsedId);
        setAllAnswerToQuestion(questionList, answerMap, questionMap, questionUsedId, questionChangeList);

        if (questionChangeList.size() != 0) {
            Map<String, List<QuestionChangeDto>> questionChangeMap = new HashMap<>();
            questionChangeMap.put("CREATE", questionChangeList);
            layerChangeDto.setQuestionMap(questionChangeMap);
        } else if (answerList.size() != 0) {
            Map<String, List<AnswerChangeDto>> answerChangeMap = new HashMap<>();
            List<AnswerChangeDto> anwerChangeList = (List<AnswerChangeDto>) answerMap.values();
            answerChangeMap.put("CREATE", anwerChangeList);
            layerChangeDto.setAnswerMap(answerChangeMap);
        }
    }

    private static void setAllAnswerToQuestion(List<Question> questionList, Map<Long, AnswerChangeDto> answerMap, Map<Long, QuestionChangeDto> questionMap, List<Long> questionUsedId, List<QuestionChangeDto> questionChangeList) {
        for (Question question : questionList) {
            Long questionId = question.getId();
            QuestionChangeDto questionChangeDto = questionMap.get(questionId);
            for (Long answerId : question.getPossibleAnswerIdList()) {
                if (answerMap.containsKey(answerId)) {
                    questionChangeDto.getAnswerList().add(answerMap.get(answerId));
                }
            }
            questionMap.put(questionId, questionChangeDto);

            if (!questionUsedId.contains(questionId)) {
                questionChangeList.add(questionChangeDto);
            }
        }
    }

    private static void setAllOpenQuestionToAnswer(List<Answer> answerList, Map<Long, AnswerChangeDto> answerMap, Map<Long, QuestionChangeDto> questionMap, List<Long> questionUsedId) {
        for (Answer answer : answerList) {
            List<Long> questionIdList = answer.getOpenQuestionList();
            Long answerId = answer.getId();
            AnswerChangeDto answerChangeDto = answerMap.get(answerId);
            for (Long questionId : questionIdList) {
                if (questionMap.containsKey(questionId)) {
                    answerChangeDto.getOpenQuestionList().add(questionMap.get(questionId));
                    questionUsedId.add(questionId);
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
            segmentMap.put("CREATE", MapperSegmentChangeDto.segmentChangeListToDtoList(segmentCreatedList));

            List<Segment> segmentDisableList = segmentList
                    .stream()
                    .filter(Segment::isArchived)
                    .toList();
            segmentMap.put("ARCHIVE", MapperSegmentChangeDto.segmentChangeListToDtoList(segmentDisableList));

            layerChangeDto.setSegmentMap(segmentMap);
        }
    }

    private static void checkEntrypointChange(Layer layer, LayerChangeDto layerChangeDto) {
        List<Entrypoint> entrypointList = layer.getEntrypointList();
        if (entrypointList != null && entrypointList.size() > 0) {
            Map<String, List<EntrypointChangeDto>> entrypointMap = new HashMap<>();
            entrypointMap.put("CREATE", MapperEntrypointChangeDto.entrypointChangeListToDtoList(entrypointList));
            layerChangeDto.setEntrypointMap(entrypointMap);
        }
    }


}
