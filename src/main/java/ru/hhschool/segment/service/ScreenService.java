package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.mapper.screen.ScreenMapper;
import ru.hhschool.segment.mapper.screen.ScreenPlatformMapper;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformDto;
import ru.hhschool.segment.model.dto.screen.ScreenPlatformVersionDto;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.enums.PlatformType;

public class ScreenService {
  private final ScreenDao screenDao;
  private final PlatformDao platformDao;

  public ScreenService(ScreenDao screenDao, PlatformDao platformDao) {
    this.screenDao = screenDao;
    this.platformDao = platformDao;
  }

  @Transactional
  public List<ScreenDto> getAll(Long androidId, Long iosId, boolean webSelect) {
    String androidVersion = getPlatformVersionFromId(androidId, PlatformType.ANDROID);
    String iosVersion = getPlatformVersionFromId(iosId, PlatformType.IOS);

    List<Screen> screens = screenDao.findAll(androidVersion, iosVersion, webSelect);
    List<ScreenDto> screenDtoList = new ArrayList<>();

    for (Screen screen : screens) {
      List<ScreenPlatformDto> platformVersions = ScreenPlatformMapper.platformListToDtoList(platformDao.findAll(screen.getPlatforms()));
      screenDtoList.add(ScreenMapper.screenToDto(screen, platformVersions));
    }
    return screenDtoList;
  }

  @Transactional
  public Optional<ScreenDto> getScreenById(long screenId) {
    Optional<Screen> screenOptional = screenDao.findById(screenId);
    if (screenOptional.isEmpty()) {
      return Optional.empty();
    }

    Screen screen = screenOptional.get();

    List<ScreenPlatformDto> platformVersions = ScreenPlatformMapper.platformListToDtoList(platformDao.findAll(screen.getPlatforms()));

    return Optional.of(ScreenMapper.screenToDto(screen, platformVersions));
  }

  public List<ScreenPlatformVersionDto> getAllPlatforms() {
    List<ScreenPlatformDto> screenPlatformList = ScreenPlatformMapper.platformListToDtoList(screenDao.findAllPlatforms());

    Map<String, List<ScreenPlatformDto>> platformVersionMap = screenPlatformList
        .stream()
        .sorted(Comparator.comparing(ScreenPlatformDto::getVersion))
        .collect(Collectors.groupingBy((p) -> p.getPlatform()));

    List<ScreenPlatformVersionDto> platformVersionDtoList = platformVersionMap
        .entrySet()
        .stream()
        .map(e -> new ScreenPlatformVersionDto(e.getKey(), e.getValue()))
        .sorted(Comparator.comparing(ScreenPlatformVersionDto::getPlatform))
        .toList();

    return platformVersionDtoList;
  }


  private String getPlatformVersionFromId(Long platformId, PlatformType platformType) {
    String result = null;
    if (platformId != null) {
      Optional<Platform> platform = platformDao.findById(platformId);
      if (platform.isEmpty()
          || (platform.isPresent() && platform.get().getPlatform() != platformType)) {
        throw new HttpBadRequestException("Error: bad " + platformType + " version.");
      }
      result = platform.get().getApplicationVersion();
    }
    return result;
  }

}
