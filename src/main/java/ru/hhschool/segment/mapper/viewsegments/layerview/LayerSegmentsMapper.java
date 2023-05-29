package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.LayerSegmentsDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.entity.Layer;

import java.util.List;

public class LayerSegmentsMapper {
    public static LayerSegmentsDto toDtoForSegmentsInLayerPage(Layer layer, List<SegmentLayerViewDto> segments){
        LayerSegmentsDto layerSegmentsDto = new LayerSegmentsDto();
        layerSegmentsDto.setId(layer.getId());
        layerSegmentsDto.setTitle(layer.getTitle());
        layerSegmentsDto.setSegments(segments);
        return layerSegmentsDto;
    }
}
