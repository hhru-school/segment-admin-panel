package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.layer.LayerStatusMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.dto.layer.LayerCreateDto;
import ru.hhschool.segment.model.dto.layer.LayerDtoForList;
import ru.hhschool.segment.model.dto.layer.QuestionRequiredLinkCreateDto;
import ru.hhschool.segment.model.dto.layer.ScreenQuestionLinkCreateDto;
import ru.hhschool.segment.model.dto.layer.SegmentScreenEntrypointLinkCreateDto;
import ru.hhschool.segment.model.dto.layer.SegmentStateLinkCreateDto;
import ru.hhschool.segment.model.dto.screen.ScreenDto;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.enums.LayerStateType;

public class LayerService {
  private final LayerDao layerDao;
  private final PlatformDao platformDao;
  private final SegmentStateLinkDao segmentStateLinkDao;

  @Inject
  public LayerService(LayerDao layerDao, PlatformDao platformDao, SegmentStateLinkDao segmentStateLinkDao) {
    this.layerDao = layerDao;
    this.platformDao = platformDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
  }

  public List<LayerDto> getLayerGroupList() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }

  @Transactional
  public Optional<LayerBasicInfoDto> getLayerDtoForBasicInfoPage(Long id) {
    Optional<Layer> layer = layerDao.findById(id);
    if (layer.isEmpty()) {
      return Optional.empty();
    }
    LayerBasicInfoDto layerBasicInfoDto = LayerBasicInfoMapper.toDtoForBasicInfoPage(
        layer.get(),
        layerDao.getAllParents(id),
        PlatformMapper.toDtoList(platformDao.findAll(layer.get().getPlatforms()))
    );
    return Optional.of(layerBasicInfoDto);
  }

  @Transactional
  public Optional<LayerChangeDto> mergeLayerWithParent(Long layerId) throws NotFoundException, IllegalStateException {
    return Optional.empty();
  }

  public List<LayerDtoForList> getAll(List<String> layerStringStateTypes) {
    List<LayerStateType> layerStateTypes = LayerStatusMapper.toStatusList(layerStringStateTypes);
    List<Layer> layerList = layerDao.findAll(layerStateTypes);

    return LayerMapper.toLayerForListDto(layerList);
  }

  @Transactional
  public void setLayerStateToArchive(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      throw new HttpNotFoundException("Слой не найден.");
    }
    if (layer.get().getState() == LayerStateType.STABLE) {
      throw new HttpBadRequestException("Не возможно STABLE слой сделать архивным.");
    }
    layer.get().setState(LayerStateType.ARCHIVE);
    try {
      layerDao.update(layer.get());
    } catch (Exception err) {
      String lastMessage = err.getMessage();
      Throwable cause = err.getCause();
      while (cause != null) {
        lastMessage = cause.getMessage();
        cause = cause.getCause();
      }
      throw new HttpBadRequestException(lastMessage);
    }
  }

  @Transactional
  public Optional<ScreenDto> add(LayerCreateDto layerCreateDto) {
    if (layerCreateDto.getParentLayerId() == null) {
      throw new HttpBadRequestException("Не указан родительский слой.");
    }

    Optional<Layer> parentLayer = layerDao.findById(layerCreateDto.getParentLayerId());
    if (parentLayer.isEmpty()) {
      throw new HttpBadRequestException("Родительский слой не найден.");
    }

    List<SegmentStateLinkCreateDto> segmentStateLinks = layerCreateDto.getSegmentStateLinks();
    List<QuestionRequiredLinkCreateDto> questionRequiredLinks = layerCreateDto.getQuestionRequiredLinks();
    List<ScreenQuestionLinkCreateDto> screenQuestionLinks = layerCreateDto.getScreenQuestionLinks();
    List<SegmentScreenEntrypointLinkCreateDto> segmentScreenEntrypointLinks = layerCreateDto.getSegmentScreenEntrypointLinks();

    List<Long> layerPlatforms = getLayerPlatforms(layerCreateDto);

    Layer layer = LayerMapper.dtoToLayer(layerCreateDto, parentLayer.get(), layerPlatforms);
    return null;
  }

  /**
   * Для всех платформ выбираем со старшими версиями. Разбитые по платформам.
   */
  private List<Long> getLayerPlatforms(LayerCreateDto layerCreateDto) {
    List<Platform> platformList = platformDao.findAll(layerCreateDto.getPlatformsId());
    Optional<Platform> androidPlatform = Optional.empty();
    Optional<Platform> iosPlatform = Optional.empty();
    Optional<Platform> webPlatform = Optional.empty();

    for (Platform platform : platformList) {
      switch (platform.getPlatform()) {
        case WEB:
          webPlatform = Optional.of(platform);
          break;
        case ANDROID:
          androidPlatform = getMaxVersion(androidPlatform, Optional.of(platform));
          break;
        case IOS:
          iosPlatform = getMaxVersion(iosPlatform, Optional.of(platform));
          break;
      }
    }

    List<Long> layerPlatforms = new ArrayList<>();

    if (androidPlatform.isPresent()) {
      layerPlatforms.add(androidPlatform.get().getId());
    }
    if (iosPlatform.isPresent()) {
      layerPlatforms.add(iosPlatform.get().getId());
    }
    if (webPlatform.isPresent()) {
      layerPlatforms.add(webPlatform.get().getId());
    }

    if (layerPlatforms.isEmpty()) {
      throw new HttpBadRequestException("Версии платформ не обнаружены.");
    }
    return layerPlatforms;
  }

  /**
   * Проверяем версии платформ возвращаем ту которая самая старшая.
   * 0. если одна из платформ null возвращаем другую.
   * 1. Разбиваем через точку на разряды, минор мажор
   * 2. Перебираем разряды и сравниваем.
   * 3. Если одна из цифр больше, то эту платформу и вернем.
   * 4. Если они одинаковы возвращаем первую.
   * 5. Если количество цифр в первой больше ее и возвращаем иначе вернем вторую.
   */
  private Optional<Platform> getMaxVersion(Optional<Platform> platform1, Optional<Platform> platform2) {
    if (platform1.isEmpty()) {
      return platform2;
    }
    if (platform2.isEmpty()) {
      return platform1;
    }
    String[] version1 = platform1.get().getApplicationVersion().split("\\.");
    String[] version2 = platform2.get().getApplicationVersion().split("\\.");

    int minSize = Math.min(version1.length, version2.length);

    for (int i = 0; i < minSize; i++) {
      int number1 = Integer.parseInt(version1[i]);
      int number2 = Integer.parseInt(version2[i]);

      if (number1 > number2) {
        return platform1;
      } else if (number2 > number1) {
        return platform2;
      }
    }

    if (version1.length == version2.length) {
      return platform1;
    } else if (version1.length > version2.length) {
      return platform1;
    }

    return platform2;
  }
}
