package ru.hhschool.segment.util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import ru.hhschool.segment.mapper.change.LayerChangeMapper;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
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
        = EntrypointConflictChecker.getEntrypointConflictList(layer.getEntrypointList(), layerStableChild.getEntrypointList());
    if (entrypointConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setEntrypointMap(Map.of(EntityStatus.CONFLICT.name(), entrypointConflictList));
    }

    List<QuestionChangeDto> questionConflictList
        = QuestionConflictChecker.getQuestionConflictList(layer.getQuestionList(), layerStableChild.getQuestionList());
    if (questionConflictList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setQuestionMap(Map.of(EntityStatus.CONFLICT.name(), questionConflictList));
    }

    List<SegmentChangeDto> segmentChangeList
        = SegmentConflictChecker.getSegmentConflictList(layer.getSegmentList(), layerStableChild.getSegmentList());
    if (segmentChangeList.size() != 0) {
      conflict = ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setSegmentMap(Map.of(EntityStatus.CONFLICT.name(), segmentChangeList));
    }

    //TODO поиск конфликтов в Links

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
  }


}
