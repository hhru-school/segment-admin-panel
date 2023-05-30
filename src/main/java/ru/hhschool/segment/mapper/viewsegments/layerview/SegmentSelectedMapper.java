package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

public class SegmentSelectedMapper {
  public static SegmentSelectedDto toDtoForSelectedSegmentViewPage(Layer layer,
                                                                   Segment segment,
                                                                   StateType activeState,
                                                                   List<Role> roles,
                                                                   List<SegmentViewRequirementDto> requirements,
                                                                   List<SegmentViewEntryPointDto> entryPoints){
    SegmentSelectedDto segmentSelectedDto = new SegmentSelectedDto();
    segmentSelectedDto.setLayerId(layer.getId());
    segmentSelectedDto.setLayerTitle(layer.getTitle());
    segmentSelectedDto.setSegmentId(segment.getId());
    segmentSelectedDto.setSegmentId(segment.getId());
    segmentSelectedDto.setActiveState(activeState);
    if (segment.getParentSegment() != null){
      segmentSelectedDto.setParentSegmentId(segment.getParentSegment().getId());
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
