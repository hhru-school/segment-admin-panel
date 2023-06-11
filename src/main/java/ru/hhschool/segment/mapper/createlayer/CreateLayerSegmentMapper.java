package ru.hhschool.segment.mapper.createlayer;

import ru.hhschool.segment.model.dto.createlayer.CreateLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerRequirementDto;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerSegmentDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.SegmentStateLink;

import java.util.List;

public class CreateLayerSegmentMapper {
  public static CreateLayerSegmentDto toDtoForLayerCreation(SegmentStateLink link,
                                                            List<Role> roles,
                                                            List<CreateLayerRequirementDto> fields,
                                                            List<CreateLayerEntryPointDto> entryPoints){
    Segment segment = link.getSegment();
    CreateLayerSegmentDto createLayerSegmentDto = new CreateLayerSegmentDto(
        segment.getId(),
        link.getId(),
        link.getState(),
        segment.getTitle(),
        segment.getDescription(),
        roles,
        segment.getTags(),
        fields,
        entryPoints
    );
    return createLayerSegmentDto;
  }
}
