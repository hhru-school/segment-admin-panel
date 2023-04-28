package ru.hhschool.segment.service;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.mapper.viewsegments.SegmentSelectedMapper;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewEntryPointMapper;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewMapper;
import ru.hhschool.segment.mapper.viewsegments.SegmentViewQuestionMapper;
import ru.hhschool.segment.model.dto.viewsegments.SegmentSelectedDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewEntryPointDto;
import ru.hhschool.segment.model.dto.viewsegments.SegmentViewQuestionDto;
import ru.hhschool.segment.model.entity.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

public class SegmentService {
  private final SegmentDao segmentDao;
  private final LayerDao layerDao;
  private final QuestionActivatorLinkDao questionActivatorLinkDao;

  @Inject
  public SegmentService(SegmentDao segmentDao, LayerDao layerDao, QuestionActivatorLinkDao questionActivatorLinkDao) {
    this.segmentDao = segmentDao;
    this.layerDao = layerDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
  }

  @Transactional
  public List<SegmentViewDto> getSegmentViewDtoListForAllSegmentsPage(Long layerId) {
    return SegmentViewMapper.toDtoListForAllSegmentsPage(getSegmentsForSpace(layerId));
  }

  public List<Segment> getSegmentsForSpace(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()){
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      layersInSpace.add(layer.get());
      return findSegmentsInSpace(layersInSpace);
    }
    return Collections.EMPTY_LIST;
  }

  private List<Segment> findSegmentsInSpace(Collection<Layer> space) {
    List<Segment> segmentList = new ArrayList<>();
    for (Layer layer : space) {
      segmentList.addAll(segmentDao.findAll(layer.getId()));
    }
    return segmentList;
  }

  @Transactional
  public Optional<SegmentSelectedDto> getSegmentSelectedDto(Long layerId, Long segmentId) {
    Optional<Segment> segment = segmentDao.findById(segmentId);
    if (segment.isPresent() && getSegmentsForSpace(layerId).contains(segment.get())){
      List<Layer> layersInSpace = getLayersInSpace(layerId);
      List<QuestionActivatorLink> questionActivatorLinksInSpace = findQALInSpace(layersInSpace, segmentId);
      Map<String, QuestionActivatorLink> latestQuestionActivatorLinkMap = getLatestQALInSpace(questionActivatorLinksInSpace, layersInSpace);
      List<Question> questions = getUniqueQuestionsInSpace(latestQuestionActivatorLinkMap);
      List<SegmentViewQuestionDto> segmentViewQuestionDtoList = getSegmentViewQuestionDtoList(questions, latestQuestionActivatorLinkMap);
      SegmentSelectedDto segmentSelectedDto = SegmentSelectedMapper.toDtoForSelectedSegmentViewPage(segment.get(), segmentViewQuestionDtoList);
      return Optional.of(segmentSelectedDto);
    }
    return Optional.empty();
  }
  private List<QuestionActivatorLink> findQALInSpace(List<Layer> layerSpace, Long segmentId) {
    List<QuestionActivatorLink> questionActivatorLinkList = new ArrayList<>();
    for(Layer layer : layerSpace){
      questionActivatorLinkList.addAll(questionActivatorLinkDao.findAll(layer.getId(), segmentId));
    }
    return questionActivatorLinkList;
  }
  private List<Layer> getLayersInSpace(Long layerId){
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
   * Находим все крайние линки для пространства выбранного слоя.
   * Важно, чтобы линки были отсортированы по порядку слоев в пространстве
   */
  private Map<String, QuestionActivatorLink> getLatestQALInSpace(List<QuestionActivatorLink> links, List<Layer> layersInSpace) {
    Map<String, QuestionActivatorLink> questionActivatorLinkMap = new HashMap<>();
    for(QuestionActivatorLink link : links){
      String key = link.getQuestion().getTitle() + link.getEntrypoint().getTitle();
      if (questionActivatorLinkMap.get(key) != null){
        questionActivatorLinkMap.replace(key, link);
      }
      questionActivatorLinkMap.put(key, link);
    }
    return questionActivatorLinkMap;
  }

  /**
   * Находим все уникальные вопросы для сегмента, сортированные по названию
   */
  private List<Question> getUniqueQuestionsInSpace(Map<String, QuestionActivatorLink> latestLinksInSpace) {
    Set<Question> questions = new HashSet<>();
    for (String key : latestLinksInSpace.keySet()){
      questions.add(latestLinksInSpace.get(key).getQuestion());
    }
    return questions.stream().sorted(Comparator.comparing(Question::getTitle)).toList();
  }

  /**
   * Получаем DTO для вопросов на странице просмотра сегмента,
   * при этом мобираем точки входа для каждог вопроса
   */
  private List<SegmentViewQuestionDto> getSegmentViewQuestionDtoList(Collection<Question> questions,
                                                                     Map<String, QuestionActivatorLink> questionActivatorLinkMap) {
    List<SegmentViewQuestionDto> segmentViewQuestionDtoList = new ArrayList<>();
    for (Question question : questions){
      List<SegmentViewEntryPointDto> segmentViewEntryPointDtoList = new ArrayList<>();
      for (QuestionActivatorLink link : questionActivatorLinkMap.values()){
        if (link.getQuestion().equals(question)){
          segmentViewEntryPointDtoList.add(SegmentViewEntryPointMapper.toDtoForSelectedSegmentViewPage(link, hasVisibilityChanged(link)));
        }
      }
      Optional<String> key = questionActivatorLinkMap.keySet().stream().filter(k -> k.contains(question.getTitle())).findFirst();
      QuestionActivatorLink someLinkForQuestion = questionActivatorLinkMap.get(key.get());
      SegmentViewQuestionDto segmentViewQuestionDto = SegmentViewQuestionMapper.toDtoForSelectedSegmentViewPage(question,
          someLinkForQuestion.isQuestionRequired(),
          hasRequiredChanged(someLinkForQuestion),
          hasQuestionChanged(someLinkForQuestion),
          segmentViewEntryPointDtoList);
      segmentViewQuestionDtoList.add(segmentViewQuestionDto);
    }
    return segmentViewQuestionDtoList;
  }

  private Boolean hasVisibilityChanged(QuestionActivatorLink currentLink){
    Optional<QuestionActivatorLink> parentLink = getParentLink(currentLink);
    if (parentLink.isEmpty()){
      return false;
    }
    return !currentLink.getQuestionVisibility().equals(parentLink.get().getQuestionVisibility());
  }

  private Boolean hasRequiredChanged(QuestionActivatorLink currentLink){
    Optional<QuestionActivatorLink> parentLink = getParentLink(currentLink);
    if (parentLink.isEmpty()){
      return false;
    }
    return currentLink.isQuestionRequired() != parentLink.get().isQuestionRequired();
  }

  private Boolean hasQuestionChanged(QuestionActivatorLink currentLink){
    Optional<QuestionActivatorLink> parentLink = getParentLink(currentLink);
    if (parentLink.isEmpty()){
      return false;
    }
    return !currentLink.getQuestion().equals(parentLink.get().getQuestion());
  }

  private Optional<QuestionActivatorLink> getParentLink(QuestionActivatorLink currentLink){
    Optional<Layer> layer = layerDao.findById(currentLink.getLayerId());
    Layer parentLayer = layer.get().getParent();
    if (parentLayer == null){
      return Optional.empty();
    }
    return questionActivatorLinkDao.findExactly(parentLayer.getId(),
        currentLink.getSegment().getId(),
        currentLink.getQuestion().getTitle(),
        currentLink.getEntrypoint().getId());
  }
}
