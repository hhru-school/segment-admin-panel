package ru.hhschool.segment.service;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.mapper.screen.ScreenMapper;
import ru.hhschool.segment.mapper.screen.ScreenPlatformMapper;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformDto;
import ru.hhschool.segment.model.entity.Screen;

public class ScreenService {
  private final ScreenDao screenDao;
  private final PlatformDao platformDao;

  public ScreenService(ScreenDao screenDao, PlatformDao platformDao) {
    this.screenDao = screenDao;
    this.platformDao = platformDao;
  }

  public List<ScreenDto> getAll() {
    return ScreenMapper.screenListToDto(screenDao.findAll());
  }

  public Optional<ScreenDto> getScreenById(long screenId) {
    Optional<Screen> screenOptional = screenDao.findById(screenId);
    if (screenOptional.isEmpty()) {
      return Optional.empty();
    }

    Screen screen = screenOptional.get();

    List<>

    List<ScreenPlatformDto> platformVersions =
        ScreenPlatformMapper.platformListToDtoList(platformDao.findAll(screen.getApplication().getPlatformList()));

    return Optional.of(ScreenMapper.screenToDto(screen, , platformVersions));
  }

}
