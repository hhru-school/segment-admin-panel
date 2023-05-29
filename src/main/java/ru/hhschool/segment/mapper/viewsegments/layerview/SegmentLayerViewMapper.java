package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.enums.SegmentViewChangeState;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.enums.StateType;

import java.util.List;

public class SegmentLayerViewMapper {
    public static SegmentLayerViewDto toDtoForSegmentsInLayerPage(Segment segment, List<Role> roles, SegmentViewChangeState changeState, StateType activeState){
        SegmentLayerViewDto segmentViewDto = new SegmentLayerViewDto();
        segmentViewDto.setId(segment.getId());
        segmentViewDto.setTitle(segment.getTitle());
        segmentViewDto.setRoles(roles);
        segmentViewDto.setTags(segment.getTags());
        segmentViewDto.setChangeState(changeState);
        segmentViewDto.setActiveState(activeState);
        return segmentViewDto;
    }
}
