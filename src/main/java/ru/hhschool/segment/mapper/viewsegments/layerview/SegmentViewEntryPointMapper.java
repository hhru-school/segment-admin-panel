package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Entrypoint;

import java.util.List;

public class SegmentViewEntryPointMapper {

  public static SegmentViewEntryPointDto toDtoForSelectedSegmentViewPage(Entrypoint entrypoint, List<SegmentViewScreenDto> screens){
    SegmentViewEntryPointDto segmentViewEntryPointDto = new SegmentViewEntryPointDto(
        entrypoint.getId(),
        entrypoint.getTitle(),
        entrypoint.getDescription(),
        screens
    );
    return segmentViewEntryPointDto;
  }
}
