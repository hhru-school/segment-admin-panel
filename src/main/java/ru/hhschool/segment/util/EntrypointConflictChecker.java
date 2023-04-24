package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import ru.hhschool.segment.mapper.change.EntrypointChangeMapper;
import ru.hhschool.segment.model.dto.change.EntrypointChangeDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.enums.ConflictStatus;

public class EntrypointConflictChecker {
  public static List<EntrypointChangeDto> getEntrypointConflictList(List<Entrypoint> listA, List<Entrypoint> listB) {
    List<EntrypointChangeDto> conflictList = new ArrayList<>();

    if (listA != null && listB != null) {
      List<EntrypointChangeDto> listLayerA = EntrypointChangeMapper.entrypointChangeListToDtoList(listA);
      List<EntrypointChangeDto> listLayerB = EntrypointChangeMapper.entrypointChangeListToDtoList(listB);

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
