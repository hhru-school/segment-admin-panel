package ru.hhschool.segment.mapper.screen;

import java.util.List;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformDto;
import ru.hhschool.segment.model.entity.Screen;

public class ScreenMapper {
  public static ScreenDto screenToDto(Screen screen, List<ScreenPlatformDto> appVersions) {
    ScreenDto screenDto = new ScreenDto(
        screen.getId(),
        screen.getTitle(),
        screen.getDescription(),
        screen.getType(),
        screen.getState(),
        ScreenFieldMapper.fieldListToDtoList(screen.getQuestions()),
        appVersions
    );


    return screenDto;
  }
}
