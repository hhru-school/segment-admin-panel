package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import ru.hhschool.segment.mapper.change.MapperAnswerChange;
import ru.hhschool.segment.mapper.change.MapperEntrypointChange;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.mapper.change.MapperQuestionChange;
import ru.hhschool.segment.mapper.change.MapperSegmentChange;
import ru.hhschool.segment.model.dto.change.AnswerChangeDto;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.ConflictStatus;
import ru.hhschool.segment.model.enums.EntityStatus;

public class LayerConflictChecker {
  public static Optional<LayerChangeDto> getConflict(Layer layer, Layer layerStableChild) {
    LayerChangeDto layerChangeDto = MapperLayerChange.layerChangeToDto(layer, ConflictStatus.NONE);

    boolean conflict = ConflictStatus.NONE.isConflict();

    List<EntrypointChangeDto> entrypointConflictList = getEntrypointConflictList(layer, layerStableChild);
    if (entrypointConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setEntrypointMap(Map.of(EntityStatus.CONFLICT.name(), entrypointConflictList));
    }

    List<QuestionChangeDto> questionConflictList = getQuestionConflictList(layer, layerStableChild);
    if (questionConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionMap(Map.of(EntityStatus.CONFLICT.name(), questionConflictList));
    }

    List<AnswerChangeDto> answerChangeList = getAnswerConflictList(layer, layerStableChild);
    if (answerChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setAnswerMap(Map.of(EntityStatus.CONFLICT.name(), answerChangeList));
    }

    List<SegmentChangeDto> segmentChangeList = getSegmentConflictList(layer, layerStableChild);
    if (segmentChangeList.size() != 0) {
      //TODO тут только титлы, а у нас еще тут надо еще сравнить и ParentId
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setSegmentMap(Map.of(EntityStatus.CONFLICT.name(), segmentChangeList));
    }

    //TODO поиск конфликтов в Links

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
  }

  private static List<SegmentChangeDto> getSegmentConflictList(Layer layer, Layer layerStableChild) {
    return new EntityConflictChecker<SegmentChangeDto>().getEntityConflict(
        MapperSegmentChange.segmentChangeListToDtoList(layer.getSegmentList()),
        MapperSegmentChange.segmentChangeListToDtoList(layerStableChild.getSegmentList())
    );
  }

  private static List<AnswerChangeDto> getAnswerConflictList(Layer layer, Layer layerStableChild) {
    return new EntityConflictChecker<AnswerChangeDto>().getEntityConflict(
        MapperAnswerChange.answerChangeListToDtoList(layer.getAnswerList()),
        MapperAnswerChange.answerChangeListToDtoList(layerStableChild.getAnswerList())
    );
  }

  private static List<QuestionChangeDto> getQuestionConflictList(Layer layer, Layer layerStableChild) {
    return new EntityConflictChecker<QuestionChangeDto>().getEntityConflict(
        MapperQuestionChange.questionChangeListToDtoList(layer.getQuestionList()),
        MapperQuestionChange.questionChangeListToDtoList(layerStableChild.getQuestionList())
    );
  }

  private static List<EntrypointChangeDto> getEntrypointConflictList(Layer layer, Layer layerStableChild) {
    return new EntityConflictChecker<EntrypointChangeDto>().getEntityConflict(
        MapperEntrypointChange.entrypointChangeListToDtoList(layer.getEntrypointList()),
        MapperEntrypointChange.entrypointChangeListToDtoList(layerStableChild.getEntrypointList())

    );
  }

  private static List<EntrypointChangeDto> isEntrypointConflict(List<Entrypoint> listA, List<Entrypoint> listB) {
    List<EntrypointChangeDto> conflictList = new ArrayList<>();

    if (listA != null && listB != null) {
      List<EntrypointChangeDto> listLayerA = MapperEntrypointChange.entrypointChangeListToDtoList(listA);
      List<EntrypointChangeDto> listLayerB = MapperEntrypointChange.entrypointChangeListToDtoList(listB);

      boolean conflict = listLayerA.stream()
          .map(EntrypointChangeDto::getTitle)
          .anyMatch(element -> listLayerB.contains(element));
      if (conflict) {
        Set<String> titleB = listLayerB
            .stream()
            .map(EntrypointChangeDto::getTitle)
            .collect(Collectors.toSet());

        for (EntrypointChangeDto entrypointChangeDto : listLayerA) {
          if (titleB.contains(entrypointChangeDto.getTitle())) {
            entrypointChangeDto.setConflict(ConflictStatus.CONFLICT.isConflict());
            conflictList.add(entrypointChangeDto);
          }
        }
      }
    }
    return conflictList;
  }
}
