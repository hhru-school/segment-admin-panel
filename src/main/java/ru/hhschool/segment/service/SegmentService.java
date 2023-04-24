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
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isPresent()){
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      layersInSpace.add(layer.get());
      List<Segment> segmentList = segmentDao.findSegmentsInSpace(layersInSpace);
      return SegmentViewMapper.toDtoListForAllSegmentsPage(segmentList);
    }
    return Collections.EMPTY_LIST;
  }

  @Transactional
  public Optional<SegmentSelectedDto> getSegmentSelectedDto(Long layerId, Long segmentId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    Optional<Segment> segment = segmentDao.findById(segmentId);
    if (layer.isPresent() && segment.isPresent()){
      List<Layer> layersInSpace = layerDao.getAllParents(layerId);
      Collections.reverse(layersInSpace);
      layersInSpace.add(layer.get());
      List<QuestionActivatorLink> questionActivatorLinksInSpace = questionActivatorLinkDao.findQALInSpace(layersInSpace, segmentId);
      Map<String, QuestionActivatorLink> latestQuestionActivatorLinkMap = getLatestQALInSpace(questionActivatorLinksInSpace, layersInSpace);
      List<Question> questions = getUniqueQuestionsInSpace(latestQuestionActivatorLinkMap);
      List<SegmentViewQuestionDto> segmentViewQuestionDtoList = getSegmentViewQuestionDtoList(questions, latestQuestionActivatorLinkMap, layer.get());
      SegmentSelectedDto segmentSelectedDto = SegmentSelectedMapper.toDtoForSelectedSegmentViewPage(segment.get(), segmentViewQuestionDtoList);
      return Optional.of(segmentSelectedDto);
    }
    return Optional.empty();
  }

  /**
   * Находим все крайние линки для пространства выбранного слоя.
   * Важно, чтобы линки были отсортированы по порядку слоев в пространстве
   */
  private Map<String, QuestionActivatorLink> getLatestQALInSpace(List<QuestionActivatorLink> links, List<Layer> layersInSpace) {
    Collections.sort(links,
        Comparator.comparing(link -> layersInSpace.indexOf(link.getLayerId())));
    Map<String, QuestionActivatorLink> questionActivatorLinkMap = new HashMap<>();
    for(QuestionActivatorLink link : links){
      String key = link.getQuestion().getTitle() + link.getEntrypoint().getTitle();
      if (questionActivatorLinkMap.containsKey(key)){
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
                                                                     Map<String, QuestionActivatorLink> questionActivatorLinkMap,
                                                                     Layer layer) {
    List<SegmentViewQuestionDto> segmentViewQuestionDtoList = new ArrayList<>();
    for (Question question : questions){
      List<SegmentViewEntryPointDto> segmentViewEntryPointDtoList = new ArrayList<>();
      //Boolean requiredChanged = false;
      for (QuestionActivatorLink link : questionActivatorLinkMap.values()){
        if (link.getQuestion().equals(question)){
          Boolean visibilityChanged = hasVisibilityChanged(link, layer);
          //requiredChanged = hasRequiredChanged(link, layer);
          segmentViewEntryPointDtoList.add(SegmentViewEntryPointMapper.toDtoForSelectedSegmentViewPage(link, visibilityChanged));
        }
      }
      SegmentViewQuestionDto segmentViewQuestionDto = SegmentViewQuestionMapper.toDtoForSelectedSegmentViewPage(question, segmentViewEntryPointDtoList);
      segmentViewQuestionDtoList.add(segmentViewQuestionDto);
    }
    return segmentViewQuestionDtoList;
  }

  private Boolean hasVisibilityChanged(QuestionActivatorLink currentLink, Layer layer){
    Layer parentLayer = layer.getParent();
    if (parentLayer == null){
      return false;
    }
    Optional<QuestionActivatorLink> parentLink = questionActivatorLinkDao.findExactly(parentLayer.getId(),
        currentLink.getSegment().getId(),
        currentLink.getQuestion().getId(),
        currentLink.getEntrypoint().getId());
    if (parentLink.isEmpty()){
      return false;
    }
    return !currentLink.getQuestionVisibility().equals(parentLink.get().getQuestionVisibility());
  }

//  private Boolean hasRequiredChanged(QuestionActivatorLink currentLink, Layer layer){
//    Layer parentLayer = layer.getParent();
//    if (parentLayer == null){
//      return false;
//    }
//    QuestionActivatorLink parentLink = questionActivatorLinkDao.findExactly(parentLayer.getId(),
//        currentLink.getSegment().getId(),
//        currentLink.getQuestion().getId(),
//        currentLink.getEntrypoint().getId());
//    return !currentLink.isQuestionRequired() == parentLink.isQuestionRequired();
//  }
}
