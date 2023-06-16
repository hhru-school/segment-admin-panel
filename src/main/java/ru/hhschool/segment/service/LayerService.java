package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.dao.abstracts.QuestionDao;
import ru.hhschool.segment.dao.abstracts.QuestionRequiredLinkDao;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.dao.abstracts.ScreenQuestionLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.dao.abstracts.SegmentScreenEntrypointLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.mapper.basicinfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.layer.LayerStatusMapper;
import ru.hhschool.segment.mapper.merge.MergeResponseMapper;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.basicinfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.layer.LayerDtoForList;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateDto;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateEntrypointDto;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateScreenDto;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateSegmentDto;
import ru.hhschool.segment.model.dto.layer.create.LinkCreateQuestionDto;
import ru.hhschool.segment.model.dto.merge.MergeResponseDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentLayerViewDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewRequirementDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionRequiredLink;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.entity.ScreenQuestionLink;
import ru.hhschool.segment.model.entity.Segment;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;
import ru.hhschool.segment.model.entity.SegmentStateLink;
import ru.hhschool.segment.model.enums.LayerStateType;
import ru.hhschool.segment.model.enums.ScreenType;
import ru.hhschool.segment.util.SQLErrorExtract;

public class LayerService {
  private final LayerDao layerDao;
  private final PlatformDao platformDao;
  private final SegmentStateLinkDao segmentStateLinkDao;
  private final SegmentService segmentService;
  private final EntrypointDao entrypointDao;
  private final QuestionDao questionDao;
  private final SegmentDao segmentDao;
  private final ScreenDao screenDao;
  private final QuestionRequiredLinkDao questionRequiredLinkDao;
  private final ScreenQuestionLinkDao screenQuestionLinkDao;
  private final SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao;
  @PersistenceContext
  protected EntityManager em;

  @Inject
  public LayerService(
      LayerDao layerDao,
      PlatformDao platformDao,
      SegmentStateLinkDao segmentStateLinkDao,
      SegmentService segmentService,
      EntrypointDao entrypointDao,
      QuestionDao questionDao,
      SegmentDao segmentDao,
      ScreenDao screenDao,
      QuestionRequiredLinkDao questionRequiredLinkDao,
      ScreenQuestionLinkDao screenQuestionLinkDao,
      SegmentScreenEntrypointLinkDao segmentScreenEntrypointLinkDao
  ) {
    this.layerDao = layerDao;
    this.platformDao = platformDao;
    this.segmentStateLinkDao = segmentStateLinkDao;
    this.segmentService = segmentService;
    this.entrypointDao = entrypointDao;
    this.questionDao = questionDao;
    this.segmentDao = segmentDao;
    this.screenDao = screenDao;
    this.questionRequiredLinkDao = questionRequiredLinkDao;
    this.screenQuestionLinkDao = screenQuestionLinkDao;
    this.segmentScreenEntrypointLinkDao = segmentScreenEntrypointLinkDao;
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
  public MergeResponseDto mergeLayerWithParent(Long layerId) {
    Layer mergingLayer = layerDao.findById(layerId)
        .orElseThrow(() -> new HttpNotFoundException("Такого слоя не существует"));
    Layer lastStableLayer = layerDao.findLastStableLayer();
    if (mergingLayer.getState() == LayerStateType.STABLE) {
      throw new HttpBadRequestException("Слой с Id " + mergingLayer.getId() + " уже стабильный");
    }
    if (mergingLayer.getState() == LayerStateType.ARCHIVE) {
      throw new HttpBadRequestException("Слой с Id " + mergingLayer.getId() + "архивный");
    }
    if (Objects.equals(mergingLayer.getParent().getId(), lastStableLayer.getId()) && mergingLayer.getState() != LayerStateType.CONFLICT) {
      mergingLayer.setState(LayerStateType.STABLE);
      layerDao.update(mergingLayer);
      return MergeResponseMapper.toDtoResponse(mergingLayer);
    }

    mergingLayer.setParent(lastStableLayer);
    layerDao.update(mergingLayer);

    List<Layer> parentsOfMergingLayer = layerDao.getAllParents(mergingLayer.getId());
    Collections.reverse(parentsOfMergingLayer);

    changeOldSegmentStateLinks(mergingLayer, parentsOfMergingLayer);
    changeOldQuestionRequiredLinks(mergingLayer, parentsOfMergingLayer);
    changeOldScreenQuestionLinks(mergingLayer, parentsOfMergingLayer);
    changeOldScreenEntrypointLinks(mergingLayer, parentsOfMergingLayer);

    List<SegmentLayerViewDto> segmentLayerViewDtoList = segmentService
        .getSegmentViewDtoListForSegmentsInLayerPage(mergingLayer.getId(), "").get()
        .getSegments();

    List<SegmentSelectedDto> selectedDtoList = new ArrayList<>();
    segmentLayerViewDtoList.forEach(segmentLayerViewDto -> {
      SegmentSelectedDto selectedDto = segmentService.getSegmentSelectedDto(mergingLayer.getId(), segmentLayerViewDto.getId()).get();
      selectedDtoList.add(selectedDto);
    });

    if (!checkStateSegment(selectedDtoList) ||
        !checkQuestionVisibilityAndPosition(selectedDtoList) ||
        !checkScreenPositionAndState(selectedDtoList) ||
        !checkRequiredQuestion(selectedDtoList)) {
      mergingLayer.setState(LayerStateType.CONFLICT);
      layerDao.update(mergingLayer);
      return MergeResponseMapper.toDtoResponse(mergingLayer);
    }
    mergingLayer.setState(LayerStateType.STABLE);
    layerDao.update(mergingLayer);
    return MergeResponseMapper.toDtoResponse(mergingLayer);
  }

  @Transactional
  public void changeOldSegmentStateLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    Map<Long, SegmentStateLink> parentsSegmentStateLinkMap = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(
        parentsOfMergingLayer,
        ""
    ));
    Map<Long, SegmentStateLink> margingSegmentStateLinkMap = segmentService.getLatestSSLInSpace(segmentService.getSSLInSpace(
        List.of(mergingLayer),
        ""
    ));
    margingSegmentStateLinkMap.forEach((id, segmentStateLink) -> {
      if (segmentStateLink.getOldSegmentStateLink().getId() != parentsSegmentStateLinkMap.get(id).getId()) {
        segmentStateLink.setOldSegmentStateLink(parentsSegmentStateLinkMap.get(id));
        segmentStateLinkDao.update(segmentStateLink);
      }
    });
  }

  @Transactional
  public void changeOldQuestionRequiredLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<QuestionRequiredLink> parentsQuestionRequiredLinks = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsQuestionRequiredLinks.addAll(questionRequiredLinkDao.findAll(layer.getId()));
    }
    List<QuestionRequiredLink> latestParentsQuestionRequiredLinks = segmentService.getLatestQRLInSpace(parentsQuestionRequiredLinks);
    List<QuestionRequiredLink> mergingQuestionRequiredLinks = questionRequiredLinkDao.findAll(mergingLayer.getId());
    Map<Long, QuestionRequiredLink> parentsQuestionRequiredLinksMap = latestParentsQuestionRequiredLinks
        .stream()
        .collect(Collectors.toMap(questionRequiredLink -> questionRequiredLink.getQuestion().getId(),
            Function.identity(), (link1, link2) -> link1
        ));
    Map<Long, QuestionRequiredLink> mergingQuestionRequiredLinksMap = mergingQuestionRequiredLinks
        .stream()
        .collect(Collectors.toMap(questionRequiredLink -> questionRequiredLink.getQuestion().getId(),
            Function.identity(), (link1, link2) -> link1
        ));
    mergingQuestionRequiredLinksMap.forEach((id, questionRequiredLink) -> {
      if (questionRequiredLink.getOldQuestionRequiredLink().getId() != parentsQuestionRequiredLinksMap.get(id).getId()) {
        questionRequiredLink.setOldQuestionRequiredLink(parentsQuestionRequiredLinksMap.get(id));
        questionRequiredLinkDao.update(questionRequiredLink);
      }
    });
  }

  @Transactional
  public void changeOldScreenQuestionLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<ScreenQuestionLink> parentsScreenQuestionLink = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsScreenQuestionLink.addAll(screenQuestionLinkDao.findAll(layer.getId()));
    }
    List<ScreenQuestionLink> latestParentsScreenQuestionLinks = segmentService.getLatestSQLInSpace(parentsScreenQuestionLink);
    List<ScreenQuestionLink> mergingScreenQuestionLinks = screenQuestionLinkDao.findAll(mergingLayer.getId());
    Map<String, ScreenQuestionLink> parentsScreenQuestionLinkMap = latestParentsScreenQuestionLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s,%s", link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle(),
                link.getQuestion().getTitle()
            ),
            Function.identity(), (link1, link2) -> link1
        ));
    Map<String, ScreenQuestionLink> mergingScreenQuestionLinkMap = mergingScreenQuestionLinks.stream()
        .collect(Collectors.toMap(link -> String.format("%s,%s,%s,%s", link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle(),
                link.getQuestion().getTitle()
            ),
            Function.identity(), (link1, link2) -> link1
        ));
    mergingScreenQuestionLinkMap.forEach((id, screenQuestionLink) -> {
      if (screenQuestionLink.getOldScreenQuestionLink().getId() != parentsScreenQuestionLinkMap.get(id).getId()) {
        screenQuestionLink.setOldScreenQuestionLink(parentsScreenQuestionLinkMap.get(id));
        screenQuestionLinkDao.update(screenQuestionLink);
      }
    });
  }

  @Transactional
  public void changeOldScreenEntrypointLinks(Layer mergingLayer, List<Layer> parentsOfMergingLayer) {
    List<SegmentScreenEntrypointLink> parentsSegmentScreenEntrypointLink = new ArrayList<>();
    for (Layer layer : parentsOfMergingLayer) {
      parentsSegmentScreenEntrypointLink.addAll(segmentScreenEntrypointLinkDao.findAll(layer.getId()));
    }
    List<SegmentScreenEntrypointLink> latestParentsSegmentScreenEntrypointLinks = segmentService.getLatestSSELInSpace(
        parentsSegmentScreenEntrypointLink);
    List<SegmentScreenEntrypointLink> mergingSegmentScreenEntrypointLinks = segmentScreenEntrypointLinkDao.findAll(mergingLayer.getId());
    Map<String, SegmentScreenEntrypointLink> parentsScreenQuestionLinkMap = latestParentsSegmentScreenEntrypointLinks.stream()
        .collect(Collectors.toMap(link -> String.format(
                "%s,%s,%s",
                link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle()
            ),
            Function.identity(), (link1, link2) -> link1
        ));
    Map<String, SegmentScreenEntrypointLink> mergingScreenQuestionLinkMap = mergingSegmentScreenEntrypointLinks.stream()
        .collect(Collectors.toMap(link -> String.format(
                "%s,%s,%s",
                link.getSegment().getTitle(),
                link.getEntrypoint().getTitle(),
                link.getScreen().getTitle()
            ),
            Function.identity(), (link1, link2) -> link1
        ));
    mergingScreenQuestionLinkMap.forEach((id, segmentScreenEntrypointLink) -> {
      if (segmentScreenEntrypointLink.getOldSegmentScreenEntrypointLink().getId() != parentsScreenQuestionLinkMap.get(id).getId()) {
        segmentScreenEntrypointLink.setOldSegmentScreenEntrypointLink(parentsScreenQuestionLinkMap.get(id));
        segmentScreenEntrypointLinkDao.update(segmentScreenEntrypointLink);
      }
    });
  }

  public boolean checkStateSegment(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      if (segmentSelectedDto.getOldActiveState() != null && segmentSelectedDto.getActiveState() != segmentSelectedDto.getOldActiveState()) {
        return false;
      }
    }
    return true;
  }

  public boolean checkQuestionVisibilityAndPosition(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewEntryPointDto segmentViewEntryPointDto : segmentSelectedDto.getEntryPoints()) {
        for (SegmentViewScreenDto segmentViewScreenDto : segmentViewEntryPointDto.getScreens()) {
          for (SegmentViewQuestionDto segmentViewQuestionDto : segmentViewScreenDto.getFields()) {
            if (segmentViewQuestionDto.getOldPosition() != null || segmentViewQuestionDto.getOldVisibility() != null) {
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  public boolean checkScreenPositionAndState(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewEntryPointDto segmentViewEntryPointDto : segmentSelectedDto.getEntryPoints()) {
        for (SegmentViewScreenDto segmentViewScreenDto : segmentViewEntryPointDto.getScreens()) {
          if (segmentViewScreenDto.getOldPosition() != null) {
            return false;
          }
          if (segmentViewScreenDto.getOldState() != null) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean checkRequiredQuestion(List<SegmentSelectedDto> selectedDtoList) {
    for (SegmentSelectedDto segmentSelectedDto : selectedDtoList) {
      for (SegmentViewRequirementDto segmentViewRequirementDto : segmentSelectedDto.getFields()) {
        if (segmentViewRequirementDto.getIsChanged()) {
          return false;
        }
      }
    }
    return true;
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
      SQLErrorExtract.extractSQLErrors(err);
    }
  }

  @Transactional
  public Optional<LayerDto> add(LayerCreateDto layerCreateDto) {
    if (layerCreateDto.getParentLayer() == null) {
      throw new HttpBadRequestException("Не указан родительский слой.");
    }

    Layer parentLayer = layerDao.findById(layerCreateDto.getParentLayer().getId())
        .orElseThrow(() -> new HttpBadRequestException("Родительский слой не найден."));

    // начало транзакции.
    em.getTransaction().begin();
    try {
      // Первое сохранение без версий, т.к. необходим layerId, для сохранения линков
      // версии пропишутся в конце когда будет возможность пройтись по всем линкам статических экранов

      Layer layer = LayerMapper.dtoToLayer(layerCreateDto, parentLayer, List.of());
      layerDao.persist(layer);

      // по мере прохождения всех вложений собираем Версии приложений.
      List<PlatformDto> platformList = new ArrayList<>();

      for (LayerCreateSegmentDto segmentDto : layerCreateDto.getSegments()) {
        Segment segment = segmentDao.findById(segmentDto.getId())
            .orElseThrow(() -> new HttpBadRequestException("Указан не существующий сегмент."));
        saveSegmentStateLink(layer, segment, segmentDto);

        for (LinkCreateQuestionDto questionDto : segmentDto.getFields()) {
          Question question = questionDao.findById(questionDto.getId())
              .orElseThrow(() -> new HttpBadRequestException("Указан не существующее поле/вопрос."));
          saveQuestionRequiredLink(layer, segment, question, questionDto);
        }
        // идем по точкам входа.
        for (LayerCreateEntrypointDto entryPoint : segmentDto.getEntryPoints()) {

          Entrypoint entrypoint = entrypointDao.findById(entryPoint.getId())
              .orElseThrow(() -> new HttpBadRequestException("Указан не существующий Entrypoint."));
          // Когда идем по скринам
          // 1. собираем версии
          // 2. ищем динамические и делаем их создание.
          for (LayerCreateScreenDto screenDto : entryPoint.getScreens()) {
            // складываем платформы
            platformList.addAll(screenDto.getAppVersions());

            Screen screen = getOrCreateScreen(screenDto);

            saveSegmentScreenEntrypointLink(layer, segment, entrypoint, screen, screenDto);

//            ScreenQuestionLink screenQuestionLink =

          }
        }

      }

      // Находим все максимальные платформы экранов и обновляем версии платформ у Слоя
      layer.setPlatforms(getLayerPlatforms(platformList));
      layerDao.update(layer);


      //      Long layerId = layer.getId();
//      // К нам приходят сегменты и их состояния, надо это прогнать через нашу базу.
//      // 1. получим все линки с состояними для всего дерева.
//      List<Long> layerPlatforms = getLayerPlatforms(layerCreateDto);
//      List<Layer> space = getLayersInSpace(parentLayer.getId());

      // конец транзакции
      em.getTransaction().commit();
    } catch (Exception err) {
      // откат
      em.getTransaction().rollback();
      SQLErrorExtract.extractSQLErrors(err);
    }

    return null;
  }

  /**
   * Прописываем для экранов их позиции и состояния SegmentScreenEntrypointLink.
   * Проверка на состояние в базе не происходит.
   */
  private void saveSegmentScreenEntrypointLink(Layer layer, Segment segment, Entrypoint entrypoint, Screen screen, LayerCreateScreenDto screenDto) {
    SegmentScreenEntrypointLink oldSegmentScreenEntrypointLink = null;
    if (screenDto.getSegmentScreenEntrypointLinkId() != null) {
      oldSegmentScreenEntrypointLink = segmentScreenEntrypointLinkDao.findById(screenDto.getSegmentScreenEntrypointLinkId())
          .orElseThrow(() -> new HttpBadRequestException("Указан не существующий SegmentScreenEntrypointLink"));
    }
    SegmentScreenEntrypointLink segmentScreenEntrypointLink = new SegmentScreenEntrypointLink(
        oldSegmentScreenEntrypointLink,
        layer,
        segment,
        entrypoint,
        screen,
        screenDto.getPosition(),
        screenDto.getState()
    );
    segmentScreenEntrypointLinkDao.persist(segmentScreenEntrypointLink);
  }

  /**
   * Создаем новый динамический экран либо достаем из базы сущность,
   * версии для динамического типа [] и вопросы по умолчанию отсутствуют.
   */
  private Screen getOrCreateScreen(LayerCreateScreenDto screenDto) {
    Screen screen = null;
    if (screenDto.isNew() && ScreenType.DYNAMIC == screenDto.getType()) {
      screen = new Screen(
          screenDto.getTitle(),
          screenDto.getDescription(),
          screenDto.getType(),
          screenDto.getState(),
          List.of(),
          List.of()
      );
      screenDao.persist(screen);
    } else {
      screen = screenDao.findById(screenDto.getId())
          .orElseThrow(() -> new HttpBadRequestException("Указан не существующий экран."));
    }
    return screen;
  }

  /**
   * Прописываем для полей их обязательность QuestionRequiredLink.
   * Проверка на состояние в базе не происходит.
   */
  private void saveQuestionRequiredLink(Layer layer, Segment segment, Question question, LinkCreateQuestionDto questionDto) {
    QuestionRequiredLink oldQuestionRequiredLink = null;
    if (questionDto.getQuestionRequiredLinkId() != null) {
      oldQuestionRequiredLink = questionRequiredLinkDao.findById(questionDto.getQuestionRequiredLinkId())
          .orElseThrow(() -> new HttpBadRequestException("Указан не существующий QuestionRequiredLink."));
    }
    QuestionRequiredLink questionRequiredLink = new QuestionRequiredLink(
        oldQuestionRequiredLink,
        layer,
        segment,
        question,
        questionDto.isRequired()
    );
    questionRequiredLinkDao.persist(questionRequiredLink);
  }

  /**
   * Прописываем для слоя все состояния Сегментов.
   * Проверка на состояние в базе не происходит.
   */
  private void saveSegmentStateLink(Layer layer, Segment segment, LayerCreateSegmentDto segmentDto) {
    SegmentStateLink oldSegmentStateLink = null;
    if (segmentDto.getSegmentStateLinkId() != null) {
      oldSegmentStateLink = segmentStateLinkDao.findById(segmentDto.getSegmentStateLinkId())
          .orElseThrow(() -> new HttpBadRequestException("Указан не существующий SegmentStateLink"));
    }
    SegmentStateLink segmentStateLink = new SegmentStateLink(
        oldSegmentStateLink,
        layer,
        segment,
        segmentDto.getActiveState()
    );
    segmentStateLinkDao.persist(segmentStateLink);
  }

  /**
   * Для всех платформ выбираем со старшими версиями. Разбитые по платформам.
   */
  private List<Long> getLayerPlatforms(List<PlatformDto> platformDtoList) {
    Optional<PlatformDto> androidPlatform = Optional.empty();
    Optional<PlatformDto> iosPlatform = Optional.empty();
    Optional<PlatformDto> webPlatform = Optional.empty();

    for (PlatformDto platformDto : platformDtoList) {
      switch (platformDto.getPlatform()) {
        case WEB -> webPlatform = Optional.of(platformDto);
        case ANDROID -> androidPlatform = getMaxVersion(androidPlatform, Optional.of(platformDto));
        case IOS -> iosPlatform = getMaxVersion(iosPlatform, Optional.of(platformDto));
      }
    }
    List<Long> layerPlatforms = new ArrayList<>();

    androidPlatform.ifPresent(platform -> layerPlatforms.add(platform.getId()));
    iosPlatform.ifPresent(platform -> layerPlatforms.add(platform.getId()));
    webPlatform.ifPresent(platform -> layerPlatforms.add(platform.getId()));

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
  private Optional<PlatformDto> getMaxVersion(Optional<PlatformDto> platform1, Optional<PlatformDto> platform2) {
    if (platform1.isEmpty()) {
      return platform2;
    }
    if (platform2.isEmpty()) {
      return platform1;
    }
    String[] version1 = platform1.get().getVersion().split("\\.");
    String[] version2 = platform2.get().getVersion().split("\\.");

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

  /**
   * Получить дерево родителей Только, что созданного слоя скопирован из SegmentService,
   * скорее всего надо вынести в утилитный класс, чтобы была возможность переиспользовать.
   */
  private List<Layer> getLayersInSpace(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()) {
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      Collections.reverse(layersInSpace);
      layersInSpace.add(layer.get());
      return layersInSpace;
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * Вытаскиваем все линки на Дерево родителей слоя из базы.
   */
  private List<SegmentScreenEntrypointLink> getSSELInSpace(List<Layer> space) {
    List<SegmentScreenEntrypointLink> segmentScreenEntrypointLinks = new ArrayList<>();
    for (Layer layer : space) {
      segmentScreenEntrypointLinks.addAll(segmentScreenEntrypointLinkDao.findAll(layer.getId()));
    }
    return segmentScreenEntrypointLinks;
  }

  /**
   * схлопываем все линки, оставляем только актуальные для дерева родителей слоя.
   * скопирован из с небольшими изменениями (title -> id (т.к. пользуемся справочниками, id всегда принадлежат уникальной сущности))
   * Вернется Map, чтобы была возможность переиспользовать, при поиске oldId
   */
  private Map<String, SegmentScreenEntrypointLink> getLatestSSELInSpace(List<SegmentScreenEntrypointLink> links) {
    Map<String, SegmentScreenEntrypointLink> segmentScreenEntrypointLinkMap = new HashMap<>();
    for (SegmentScreenEntrypointLink link : links) {
      String key = String.format(
          "segment-%s,entrypoint-%s,screen-%s",
          link.getSegment().getId(),
          link.getEntrypoint().getId(),
          link.getScreen().getId()
      );
      segmentScreenEntrypointLinkMap.put(key, link);
    }
    return segmentScreenEntrypointLinkMap;
  }

  /**
   * Получаем все состояния сегментов из дерева родителей скопировано из SegmentService
   * скорее всего надо вынести в утилитный класс, чтобы была возможность переиспользовать.
   */
  private List<SegmentStateLink> getSSLInSpace(List<Layer> layerSpace) {
    List<SegmentStateLink> questionActivatorLinkList = new ArrayList<>();
    for (Layer layer : layerSpace) {
      questionActivatorLinkList.addAll(segmentStateLinkDao.findAll(layer.getId()));
    }
    return questionActivatorLinkList;
  }

  /**
   * Получаем схлопнутые состояния сегментов из дерева родителей
   * скопировано из SegmentService
   * скорее всего надо вынести в утилитный класс, чтобы была возможность переиспользовать.
   */
  private Map<Long, SegmentStateLink> getLatestSSLInSpace(List<SegmentStateLink> links) {
    Map<Long, SegmentStateLink> segmentStateLinkMap = new HashMap<>();
    for (SegmentStateLink link : links) {
      segmentStateLinkMap.put(link.getSegment().getId(), link);
    }
    return segmentStateLinkMap;
  }

}
