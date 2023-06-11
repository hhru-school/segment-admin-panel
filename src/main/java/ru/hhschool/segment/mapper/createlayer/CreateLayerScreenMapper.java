package ru.hhschool.segment.mapper.createlayer;

import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.CreateLayerScreenDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;
import java.util.Objects;

public class CreateLayerScreenMapper {

  public static CreateLayerScreenDto toDtoForLayerCreation(SegmentScreenEntrypointLink link,
                                                           List<PlatformDto> platforms,
                                                           List<CreateLayerQuestionDto> fields){
    Screen screen = link.getScreen();
    CreateLayerScreenDto createLayerScreenDto = new CreateLayerScreenDto(
        screen.getId(),
        link.getId(),
        screen.getTitle(),
        screen.getDescription(),
        screen.getType(),
        link.getScreenPosition(),
        platforms,
        fields
    );
    return createLayerScreenDto;
  }
}
