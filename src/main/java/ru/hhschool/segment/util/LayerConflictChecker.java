package ru.hhschool.segment.util;

import java.util.Optional;
import ru.hhschool.segment.mapper.change.LayerChangeMapper;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.ConflictStatus;

public class LayerConflictChecker {
  public static Optional<LayerChangeDto> getConflict(Layer layer, Layer layerForCompare) {
    LayerChangeDto layerChangeDto = LayerChangeMapper.layerChangeToDto(layer, ConflictStatus.NONE);

    layerChangeDto.setLastCompareLayerId(layerForCompare.getId());

    boolean conflict = ConflictStatus.NONE.isConflict();

    conflict |= checkEntrypointConflict(layer, layerForCompare, layerChangeDto);
    conflict |= checkQuestionConflict(layer, layerForCompare, layerChangeDto);
    conflict |= checkQuestionActivatorLinkConflict(layer, layerForCompare, layerChangeDto);

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
  }

  private static boolean checkQuestionActivatorLinkConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    return conflict;
  }

  private static boolean checkQuestionConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    return conflict;
  }

  private static boolean checkEntrypointConflict(Layer layer, Layer layerForCompare, LayerChangeDto layerChangeDto) {
    boolean conflict = ConflictStatus.NONE.isConflict();
    return conflict;
  }


}
