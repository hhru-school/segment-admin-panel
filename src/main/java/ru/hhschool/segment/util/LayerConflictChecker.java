package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import ru.hhschool.segment.mapper.change.MapperEntrypointChange;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.enums.ConflictStatus;
import ru.hhschool.segment.model.enums.EntityStatus;

public class LayerConflictChecker {
  public static Optional<LayerChangeDto> getConflict(Layer layer, Layer layerStableChild) {
    LayerChangeDto layerChangeDto = MapperLayerChange.layerChangeToDto(layer, ConflictStatus.NONE);

    boolean conflict = ConflictStatus.NONE.isConflict();

    List<EntrypointChangeDto> entrypointConflictList =
        new EntityConflictChecker<EntrypointChangeDto>().getEntityConflict(
            MapperEntrypointChange.entrypointChangeListToDtoList(layer.getEntrypointList()),
            MapperEntrypointChange.entrypointChangeListToDtoList(layerStableChild.getEntrypointList())

        );
    if (entrypointConflictList.size() != 0) {
      conflict ^= ConflictStatus.CONFLICT.isConflict();
      layerChangeDto.setEntrypointMap(Map.of(EntityStatus.CONFLICT.name(), entrypointConflictList));
    }

    layerChangeDto.setConflict(conflict);
    return Optional.of(layerChangeDto);
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
