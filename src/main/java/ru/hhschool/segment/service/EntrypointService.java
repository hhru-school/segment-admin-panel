package ru.hhschool.segment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.QuestionActivatorLinkDao;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.mapper.entrypointinfo.EntrypointWitchQuestionStatusMapper;
import ru.hhschool.segment.mapper.entrypointinfo.QuestionStatusMapper;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.dto.entrypointinfo.EntrypointWitchQuestionStatusDto;
import ru.hhschool.segment.model.dto.entrypointinfo.QuestionStatusDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public class EntrypointService {
  private final EntrypointDao entrypointDao;
  private final LayerDao layerDao;
  private final QuestionActivatorLinkDao questionActivatorLinkDao;

  @Inject
  public EntrypointService(EntrypointDao entrypointDao, LayerDao layerDao, QuestionActivatorLinkDao questionActivatorLinkDao) {
    this.entrypointDao = entrypointDao;
    this.layerDao = layerDao;
    this.questionActivatorLinkDao = questionActivatorLinkDao;
  }

  public List<EntrypointDto> getAllEntrypoint() {
    return EntrypointMapper.entrypointListToDtoList(entrypointDao.findAll());
  }

  @Transactional
  public Set<EntrypointDto> getAllEntrypointByLayerId(Long layerId) {
    Optional<Layer> layer = Optional.ofNullable(layerDao.findById(layerId));
    if (layer.isEmpty()) {
      return Set.of();
    }
    List<Layer> layerParentList = layerDao.getAllParents(layerId);

    Set<EntrypointDto> entrypointDtoSet = new HashSet<>();
    for (int i = layerParentList.size() - 1; i >= 0; i--) {
      saveEntrypointFromLayerToSet(entrypointDtoSet, layerParentList.get(i).getId());
    }
    saveEntrypointFromLayerToSet(entrypointDtoSet, layerId);

    return entrypointDtoSet;
  }

  @Transactional
  public Optional<EntrypointWitchQuestionStatusDto> getEntrypointByIdWithQuestionStatus(Long entrypointId, Long layerId) {
    Optional<Layer> layer = Optional.ofNullable(layerDao.findById(layerId));
    if (layer.isEmpty()) {
      return Optional.empty();
    }

    Optional<Entrypoint> entrypoint = Optional.ofNullable(entrypointDao.findById(entrypointId));
    if (entrypoint.isEmpty()) {
      return Optional.empty();
    }

    List<Layer> layerParentList = layerDao.getAllParents(layerId);

    Set<QuestionStatusDto> questionStatusDtoSet = new HashSet<>();
    for (int i = layerParentList.size() - 1; i >= 0; i--) {
      saveEntrypointQuestionStatusFromLayerToSet(questionStatusDtoSet, layerParentList.get(i).getId(), entrypointId);
    }
    saveEntrypointQuestionStatusFromLayerToSet(questionStatusDtoSet, layer.get().getId(), entrypointId);

    return Optional.of(EntrypointWitchQuestionStatusMapper.questionToQuestionStatusDto(entrypoint.get(), questionStatusDtoSet));
  }

  /**
   * Собираем элементы по слоям Каждый слой layerId накладывается на другой, затирая старое значение или создавая новое.
   * Важно! У questionStatusDto обязательно определен Equals и HashCode на Title.
   */
  private void saveEntrypointQuestionStatusFromLayerToSet(Set<QuestionStatusDto> questionStatusDtoSet, Long layerId, Long entrypointId) {
    List<QuestionActivatorLink> questionActivatorLinksList = questionActivatorLinkDao.findAllByLayerIdAndEntrypointId(layerId, entrypointId);
    for (QuestionActivatorLink questionActivatorLink : questionActivatorLinksList) {
      Question question = questionActivatorLink.getQuestion();
      if (question != null) {
        QuestionStatusDto questionStatusDto = QuestionStatusMapper.questionToQuestionStatusDto(question);

        if (questionStatusDtoSet.contains(questionStatusDto)) {
          questionStatusDtoSet.remove(questionStatusDto);
        }
        questionStatusDto.getQuestionStatus().add(question.getQuestionVisibilityType());
        questionStatusDtoSet.add(questionStatusDto);
      }
    }
  }


  /**
   * Собираем элементы по слоям Каждый слой layerId накладывается на другой, затирая старое значение или создавая новое.
   * Важно! У entrypointDto обязательно определен Equals и HashCode на Title + Description.
   *
   * @param entrypointDtoSet
   * @param layerId
   */
  private void saveEntrypointFromLayerToSet(Set<EntrypointDto> entrypointDtoSet, Long layerId) {
    List<Entrypoint> entrypointList = entrypointDao.findAllByLayerId(layerId);
    List<EntrypointDto> entrypointDtoList = EntrypointMapper.entrypointListToDtoList(entrypointList);
    for (EntrypointDto entrypointDto : entrypointDtoList) {
      if (entrypointDtoSet.contains(entrypointDto)) {
        entrypointDtoSet.remove(entrypointDto);
      }
      entrypointDtoSet.add(entrypointDto);
    }
  }

}
