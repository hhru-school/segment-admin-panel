package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import ru.hhschool.segment.model.dto.change.GetterElemet;
import ru.hhschool.segment.model.enums.ConflictStatus;

public class EntityConflictChecker<E extends GetterElemet> {

  public List<E> getEntityConflict(List<E> listLayerA, List<E> listLayerB) {
    List<E> conflictList = new ArrayList<>();

    if (listLayerA != null && listLayerB != null) {
      boolean conflict = listLayerA.stream()
          .map(E::getTitle)
          .anyMatch(element -> listLayerB.contains(element));
      if (conflict) {
        Set<String> titleB = listLayerB
            .stream()
            .map(E::getTitle)
            .collect(Collectors.toSet());

        for (E element : listLayerA) {
          if (titleB.contains(element.getTitle())) {
            element.setConflict(ConflictStatus.CONFLICT.isConflict());
            conflictList.add(element);
          }
        }
      }
    }
    return conflictList;
  }
}
