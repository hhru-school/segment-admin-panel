package ru.hhschool.segment.mapper;

import ru.hhschool.segment.model.dto.SegmentChangeDto;
import ru.hhschool.segment.model.entity.Segment;

import java.util.List;

public class MapperSegmentChangeDto {
    public static SegmentChangeDto segmentChangeToDto(Segment segment) {
        SegmentChangeDto segmentChangeDto = new SegmentChangeDto(
                segment.getId(),
                segment.getTitle(),
                segment.getDescription(),
                segment.isArchived()
        );

        return segmentChangeDto;
    }

    public static List<SegmentChangeDto> segmentChangeListToDtoList(List<Segment> segmentList) {
        return segmentList
                .stream()
                .map(MapperSegmentChangeDto::segmentChangeToDto)
                .toList();
    }
}
