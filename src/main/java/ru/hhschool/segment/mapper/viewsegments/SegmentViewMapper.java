package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

public class SegmentViewMapper {

  public static SegmentViewDto toDtoForSegmentsInLayerPage(Segment segment, List<Role> roles, SegmentViewChangeState changeState, StateType activeState){
    SegmentViewDto segmentViewDto = new SegmentViewDto();
    segmentViewDto.setId(segment.getId());
    segmentViewDto.setTitle(segment.getTitle());
    segmentViewDto.setRoles(roles);
    segmentViewDto.setTags(segment.getTagList());
    segmentViewDto.setChangeState(changeState);
    segmentViewDto.setActiveState(activeState);
    return segmentViewDto;
  }

}
