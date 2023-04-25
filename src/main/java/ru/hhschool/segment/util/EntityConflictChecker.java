package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.List;
import ru.hhschool.segment.model.dto.change.ConflictSetter;
import ru.hhschool.segment.model.enums.ConflictStatus;

/**
 * Сравнить две сущности и заносить их в Конфликт Список
 * !важно, у сущности должно быть определенно Equals, HashCode, и унаследована от Conflicteda
 */
public class EntityConflictChecker<T extends ConflictSetter> {

  public List<T> getConflictList(List<T> listLayerA, List<T> listLayerB) {
    List<T> conflictList = new ArrayList<>();

    if (listLayerA != null && listLayerB != null) {
      for (T changeDto : listLayerA) {
        if (listLayerB.contains(changeDto)) {
          changeDto.setConflict(ConflictStatus.CONFLICT.isConflict());
          conflictList.add(changeDto);
        }
      }
    }
    return conflictList;
  }

}
