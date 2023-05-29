package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

public class SegmentSelectedMapper {
  public static SegmentSelectedDto toDtoForSelectedSegmentViewPage(Segment segment,
                                                                   StateType activeState,
                                                                   List<Role> roles,
                                                                   List<SegmentViewRequirementDto> requirements,
                                                                   List<SegmentViewEntryPointDto> entryPoints){
    SegmentSelectedDto segmentSelectedDto = new SegmentSelectedDto();
    segmentSelectedDto.setId(segment.getId());
    segmentSelectedDto.setActiveState(activeState);
    if (segment.getParentSegment() != null){
      segmentSelectedDto.setParentId(segment.getParentSegment().getId());
    }
    segmentSelectedDto.setTitle(segment.getTitle());
    segmentSelectedDto.setDescription(segment.getDescription());
    segmentSelectedDto.setRoles(roles);
    segmentSelectedDto.setTags(segment.getTags());
    segmentSelectedDto.setRequirements(requirements);
    segmentSelectedDto.setEntryPoints(entryPoints);
    return segmentSelectedDto;
  }
}
