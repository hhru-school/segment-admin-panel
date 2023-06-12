package ru.hhschool.segment.mapper.createlayer.info;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerRequirementDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerSegmentDto;
import ru.hhschool.segment.model.entity.Role;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.SegmentStateLink;

import java.util.List;

public class InfoLayerSegmentMapper {
  public static InfoLayerSegmentDto toDtoForLayerCreation(SegmentStateLink link,
                                                          List<Role> roles,
                                                          List<InfoLayerRequirementDto> fields,
                                                          List<InfoLayerEntryPointDto> entryPoints){
    Segment segment = link.getSegment();
    InfoLayerSegmentDto infoLayerSegmentDto = new InfoLayerSegmentDto(
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
    return infoLayerSegmentDto;
  }
}
