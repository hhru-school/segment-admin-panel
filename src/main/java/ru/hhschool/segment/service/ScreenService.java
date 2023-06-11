package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.screen.ScreenMapper;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.screen.ScreenCreateDto;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.enums.PlatformType;
import ru.hhschool.segment.model.enums.ScreenType;

public class ScreenService {
  private final ScreenDao screenDao;
  private final PlatformDao platformDao;
  private final QuestionDao questionDao;

  public ScreenService(ScreenDao screenDao, PlatformDao platformDao, QuestionDao questionDao) {
    this.screenDao = screenDao;
    this.platformDao = platformDao;
    this.questionDao = questionDao;
  }

  @Transactional
  public List<ScreenDto> getAll(ScreenType screenType, Long androidId, Long iosId, boolean webSelect) {
    List<ScreenType> screenTypeList = List.of();
    if (screenType == null) {
      screenTypeList = Arrays.stream(screenType.values()).toList();
    } else {
      screenTypeList = List.of(screenType);
    }
    Optional<String> androidVersion = getPlatformVersionFromId(androidId, PlatformType.ANDROID);
    Optional<String> iosVersion = getPlatformVersionFromId(iosId, PlatformType.IOS);

    List<Screen> screens = screenDao.findAll(screenTypeList, androidVersion, iosVersion, webSelect);
    List<ScreenDto> screenDtoList = new ArrayList<>();

    for (Screen screen : screens) {
      List<PlatformDto> platformVersions = PlatformMapper.toDtoList(platformDao.findAll(screen.getPlatforms()));
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

    List<PlatformDto> platformVersions = PlatformMapper.toDtoList(platformDao.findAll(screen.getPlatforms()));

    return Optional.of(ScreenMapper.screenToDto(screen, platformVersions));
  }

  @Transactional
  public Optional<ScreenDto> add(ScreenCreateDto screenCreateDto) {
    if (screenCreateDto.getTitle() == null || screenCreateDto.getTitle().isBlank()) {
      throw new HttpBadRequestException("Название(Title) неверно указанное значение или пустой.");
    }
    if (screenCreateDto.getQuestionsId() == null || screenCreateDto.getPlatformsId().isEmpty()) {
      throw new HttpBadRequestException("Отсутствуют необходимые данные.");
    }

    List<Question> questionList = new ArrayList<>();
    for (Long questionId : screenCreateDto.getQuestionsId()) {
      questionList.add(questionDao.findById(questionId).orElseThrow(
          () -> new HttpBadRequestException("Не обнаружен ID поля или вопроса.")
      ));
    }

    Screen screen = ScreenMapper.dtoToScreen(screenCreateDto, questionList);

    try {
      screenDao.persist(screen);
    } catch (Exception err) {
      String lastMessage = err.getMessage();
      Throwable cause = err.getCause();
      while (cause != null) {
        lastMessage = cause.getMessage();
        cause = cause.getCause();
      }
      throw new HttpBadRequestException(lastMessage);
    }

    List<PlatformDto> platformVersions = PlatformMapper.toDtoList(platformDao.findAll(screen.getPlatforms()));

    return Optional.of(ScreenMapper.screenToDto(screen, platformVersions));
  }

  private Optional<String> getPlatformVersionFromId(Long platformId, PlatformType platformType) {
    Optional<String> result = Optional.empty();
    if (platformId != null) {
      Optional<Platform> platform = platformDao.findById(platformId);
      if (platform.isEmpty() || platform.get().getPlatform() != platformType) {
        throw new HttpBadRequestException("Error: bad " + platformType + " version.");
      }
      result = Optional.of(platform.get().getApplicationVersion());
    }
    return result;
  }

}
