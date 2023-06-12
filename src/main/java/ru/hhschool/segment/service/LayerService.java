package ru.hhschool.segment.service;

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
import ru.hhschool.segment.model.entity.Screen;
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

    List<Layer> parentList = layerDao.getAllParents(layerCreateDto.getParentLayerId());
    parentList.add(0, parentLayer.get());

    List<Screen> platforms = platformDao.getMaxVer

    Layer layer = LayerMapper.dtoToLayer(layerCreateDto, parentLayer);
    return null;
  }
}
