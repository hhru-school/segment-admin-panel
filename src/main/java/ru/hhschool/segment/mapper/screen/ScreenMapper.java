package ru.hhschool.segment.mapper.screen;

import java.util.List;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.screen.ScreenCreateDto;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.enums.StateType;

public class ScreenMapper {
  public static ScreenDto screenToDto(Screen screen, List<PlatformDto> appVersions) {
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

  public static Screen dtoToScreen(ScreenCreateDto screenCreateDto, List<Question> questionList) {
    Screen screen = new Screen(
        screenCreateDto.getTitle(),
        screenCreateDto.getDescription(),
        screenCreateDto.getType(),
        StateType.ACTIVE,
        screenCreateDto.getPlatformsId(),
        questionList
    );
    return screen;
  }
}
