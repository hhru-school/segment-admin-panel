package ru.hhschool.segment.mapper.viewsegments.layerview;

import java.util.List;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.SegmentStateLink;
import ru.hhschool.segment.model.enums.StateType;

public class SegmentLayerViewMapper {
  public static SegmentLayerViewDto toDtoForSegmentsInLayerPage(
      Segment segment,
      SegmentStateLink link,
      List<Role> roles,
      SegmentViewChangeState changeState
  ) {
    SegmentLayerViewDto segmentViewDto = new SegmentLayerViewDto();
    segmentViewDto.setId(segment.getId());
    segmentViewDto.setTitle(segment.getTitle());
    segmentViewDto.setSegmentStateLinkId(link.getId());
    segmentViewDto.setRoles(roles);
    segmentViewDto.setTags(segment.getTags());
    segmentViewDto.setChangeState(changeState);
    segmentViewDto.setActiveState(link.getState());
    return segmentViewDto;
  }
}
