package ru.hhschool.segment.mapper.createlayer.info;

import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerScreenDto;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public class InfoLayerScreenMapper {

  public static InfoLayerScreenDto toDtoForLayerCreation(SegmentScreenEntrypointLink link,
                                                         List<PlatformDto> platforms,
                                                         List<InfoLayerQuestionDto> fields){
    Screen screen = link.getScreen();
    InfoLayerScreenDto infoLayerScreenDto = new InfoLayerScreenDto(
        screen.getId(),
        link.getId(),
        screen.getTitle(),
        screen.getDescription(),
        screen.getType(),
        link.getScreenState(),
        link.getScreenPosition(),
        platforms,
        fields
    );
    return infoLayerScreenDto;
  }
}
