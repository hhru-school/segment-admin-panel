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
  public static Optional<LayerChangeDto> getConflict(Layer layer, Layer layerStableChild) {
    LayerChangeDto layerChangeDto = LayerChangeMapper.layerChangeToDto(layer, ConflictStatus.NONE);

    boolean conflict = ConflictStatus.NONE.isConflict();

    List<EntrypointChangeDto> entrypointConflictList
        = new EntityConflictChecker<EntrypointChangeDto>().getConflictList(
        EntrypointChangeMapper.entrypointChangeListToDtoList(layer.getEntrypointList()),
        EntrypointChangeMapper.entrypointChangeListToDtoList(layerStableChild.getEntrypointList())
    );
    if (entrypointConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setEntrypointMap(Map.of(EntityStatus.CONFLICT.name(), entrypointConflictList));
    }

    List<QuestionChangeDto> questionConflictList
        = new EntityConflictChecker<QuestionChangeDto>().getConflictList(
        QuestionChangeMapper.questionChangeListToDtoList(layer.getQuestionList()),
        QuestionChangeMapper.questionChangeListToDtoList(layerStableChild.getQuestionList())
    );
    if (questionConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionMap(Map.of(EntityStatus.CONFLICT.name(), questionConflictList));
    }

    List<SegmentChangeDto> segmentChangeList
        = new EntityConflictChecker<SegmentChangeDto>().getConflictList(
        SegmentChangeMapper.segmentChangeListToDtoList(layer.getSegmentList()),
        SegmentChangeMapper.segmentChangeListToDtoList(layerStableChild.getSegmentList())
    );
    if (segmentChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setSegmentMap(Map.of(EntityStatus.CONFLICT.name(), segmentChangeList));
    }

    List<QuestionActivatorLinkChangeDto> linksChangeList
        = new EntityConflictChecker<QuestionActivatorLinkChangeDto>().getConflictList(
        QuestionActivatorLinkChangeMapper.questionActivatorLinkChangeListToDtoList(layer.getQuestionActivatorLinksList()),
        QuestionActivatorLinkChangeMapper.questionActivatorLinkChangeListToDtoList(layerStableChild.getQuestionActivatorLinksList())
    );
    if (linksChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionActivatorLinkMap(Map.of(EntityStatus.CONFLICT.name(), linksChangeList));
    }

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
  }


}
