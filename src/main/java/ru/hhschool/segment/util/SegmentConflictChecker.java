package ru.hhschool.segment.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import ru.hhschool.segment.mapper.change.SegmentChangeMapper;
import ru.hhschool.segment.model.dto.change.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.enums.ConflictStatus;

/**
 * сравнивать
 * Segment ParentId + Title
 */
public class SegmentConflictChecker {

  public static List<SegmentChangeDto> getSegmentConflictList(List<Segment> listA, List<Segment> listB) {
    List<SegmentChangeDto> conflictList = new ArrayList<>();

    if (listA != null && listB != null) {
      List<SegmentChangeDto> listLayerA = SegmentChangeMapper.segmentChangeListToDtoList(listA);
      Set<SegmentChangeDto> listLayerB = new HashSet<>(SegmentChangeMapper.segmentChangeListToDtoList(listB));

      boolean conflict = listLayerA.stream()
          .anyMatch(element -> listLayerB.contains(element));
      if (conflict) {
        Set<SegmentChangeDto> entityB = new HashSet<>(listLayerB);

        for (SegmentChangeDto segmentChangeDto : listLayerA) {
          if (entityB.contains(segmentChangeDto.getTitle())) {
            segmentChangeDto.setConflict(ConflictStatus.CONFLICT.isConflict());
            conflictList.add(segmentChangeDto);
          }
        }
      }
    }
    return conflictList;
  }

}
