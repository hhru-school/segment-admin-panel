package ru.hhschool.segment.util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.hhschool.segment.mapper.change.EntrypointChangeMapper;
import ru.hhschool.segment.mapper.change.LayerChangeMapper;
import ru.hhschool.segment.mapper.change.QuestionActivatorLinkChangeMapper;
import ru.hhschool.segment.mapper.change.QuestionChangeMapper;
import ru.hhschool.segment.mapper.change.SegmentChangeMapper;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionActivatorLinkChangeDto;
import ru.hhschool.segment.model.dto.change.QuestionChangeDto;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.ConflictStatus;
import ru.hhschool.segment.model.enums.EntityStatus;

public class LayerConflictChecker {
  public static Optional<LayerChangeDto> getConflict(Layer layer, Layer layerForCompare) {
    LayerChangeDto layerChangeDto = LayerChangeMapper.layerChangeToDto(layer, ConflictStatus.NONE);

    layerChangeDto.setLastCompareLayerId(layerForCompare.getId());

    boolean conflict = ConflictStatus.NONE.isConflict();

    conflict |= checkEntrypointConflict(layer, layerForCompare, layerChangeDto);
    conflict |= checkQuestionConflict(layer, layerForCompare, layerChangeDto);
    conflict |= checkSegmentConflict(layer, layerForCompare, layerChangeDto);
    conflict |= checkQuestionActivatorLinkConflict(layer, layerForCompare, layerChangeDto);

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
  }

  private static boolean checkQuestionActivatorLinkConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    List<QuestionActivatorLinkChangeDto> linksChangeList
        = new EntityConflictChecker<QuestionActivatorLinkChangeDto>().getConflictList(
        QuestionActivatorLinkChangeMapper.questionActivatorLinkChangeListToDtoList(layer.getQuestionActivatorLinksList()),
        QuestionActivatorLinkChangeMapper.questionActivatorLinkChangeListToDtoList(layerForCompare.getQuestionActivatorLinksList())
    );
    if (linksChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionActivatorLinkMap(Map.of(EntityStatus.CONFLICT.name(), linksChangeList));
    } else {
      layerChangeDto.setQuestionActivatorLinkMap(Map.of());
    }
    return conflict;
  }

  private static boolean checkSegmentConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    List<SegmentChangeDto> segmentChangeList
        = new EntityConflictChecker<SegmentChangeDto>().getConflictList(
        SegmentChangeMapper.segmentChangeListToDtoList(layer.getSegmentList()),
        SegmentChangeMapper.segmentChangeListToDtoList(layerForCompare.getSegmentList())
    );
    if (segmentChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setSegmentMap(Map.of(EntityStatus.CONFLICT.name(), segmentChangeList));
    } else {
      layerChangeDto.setSegmentMap(Map.of());
    }
    return conflict;
  }

  private static boolean checkQuestionConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    List<QuestionChangeDto> questionConflictList
        = new EntityConflictChecker<QuestionChangeDto>().getConflictList(
        QuestionChangeMapper.questionChangeListToDtoList(layer.getQuestionList()),
        QuestionChangeMapper.questionChangeListToDtoList(layerForCompare.getQuestionList())
    );
    if (questionConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionMap(Map.of(EntityStatus.CONFLICT.name(), questionConflictList));
    } else {
      layerChangeDto.setQuestionMap(Map.of());
    }
    return conflict;
  }

  private static boolean checkEntrypointConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    List<EntrypointChangeDto> entrypointConflictList
        = new EntityConflictChecker<EntrypointChangeDto>().getConflictList(
        EntrypointChangeMapper.entrypointChangeListToDtoList(layer.getEntrypointList()),
        EntrypointChangeMapper.entrypointChangeListToDtoList(layerForCompare.getEntrypointList())
    );
    if (entrypointConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setEntrypointMap(Map.of(EntityStatus.CONFLICT.name(), entrypointConflictList));
    } else {
      layerChangeDto.setEntrypointMap(Map.of());
    }
    return conflict;
  }


}
