package ru.hhschool.segment.mapper.viewsegments;

import ru.hhschool.segment.model.dto.viewsegments.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.entity.Layer;

import java.util.List;

public class LayerSegmentsMapper {
    public static LayerSegmentsDto toDtoForSegmentsInLayerPage(Layer layer, List<SegmentViewDto> segments){
        LayerSegmentsDto layerSegmentsDto = new LayerSegmentsDto();
        layerSegmentsDto.setId(layer.getId());
        layerSegmentsDto.setTitle(layer.getTitle());
        layerSegmentsDto.setSegments(segments);
        return layerSegmentsDto;
    }
}
