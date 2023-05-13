package ru.hhschool.segment.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.mapper.entrypointinfo.EntrypointWitchQuestionStatusMapper;
import ru.hhschool.segment.mapper.entrypointinfo.QuestionStatusMapper;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.dto.entrypointinfo.EntrypointWitchQuestionStatusDto;
import ru.hhschool.segment.model.dto.entrypointinfo.QuestionStatusDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Question;
import ru.hhschool.segment.model.enums.QuestionVisibilityType;
import ru.hhschool.segment.model.enums.ResumeField;

public class EntrypointService {
  private final EntrypointDao entrypointDao;
  private final LayerDao layerDao;

  @Inject
  public EntrypointService(EntrypointDao entrypointDao, LayerDao layerDao) {
    this.entrypointDao = entrypointDao;
    this.layerDao = layerDao;
  }

  public List<EntrypointDto> getAllEntrypoint() {
    return EntrypointMapper.entrypointListToDtoList(entrypointDao.findAll());
  }

  @Transactional
  public Collection<EntrypointDto> getAllEntrypointByLayerId(Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      return Set.of();
    }
    List<Layer> layerParentList = layerDao.getAllParents(layerId);

    Map<String, EntrypointDto> entrypointDtoMap = new HashMap<>();
    for (int i = layerParentList.size() - 1; i >= 0; i--) {
      saveEntrypointFromLayerToMap(entrypointDtoMap, layerParentList.get(i).getId());
    }
    saveEntrypointFromLayerToMap(entrypointDtoMap, layerId);

    return entrypointDtoMap.values();
  }

  @Transactional
  public Optional<EntrypointWitchQuestionStatusDto> getEntrypointByIdWithQuestionStatus(Long entrypointId, Long layerId) {
    Optional<Layer> layer = layerDao.findById(layerId);
    if (layer.isEmpty()) {
      return Optional.empty();
    }

    Optional<Entrypoint> entrypoint = entrypointDao.findById(entrypointId);
    if (entrypoint.isEmpty()) {
      return Optional.empty();
    }

    List<Layer> layerParentList = layerDao.getAllParents(layerId);

    Map<String, QuestionStatusDto> questionStatusDtoMap = new HashMap<>();
    for (int i = layerParentList.size() - 1; i >= 0; i--) {
      saveEntrypointQuestionStatusFromLayerToSet(questionStatusDtoMap, layerParentList.get(i).getId(), entrypointId);
    }
    saveEntrypointQuestionStatusFromLayerToSet(questionStatusDtoMap, layer.get().getId(), entrypointId);

    Set<QuestionStatusDto> questionStatusDtoSet = new HashSet<>(questionStatusDtoMap.values());

    return Optional.of(EntrypointWitchQuestionStatusMapper.questionToQuestionStatusDto(entrypoint.get(), questionStatusDtoSet));
  }

  /**
   * Собираем элементы по слоям. Каждый слой layerId накладывается на другой,
   * собирая статусы из каждого слоя.
   */
  private void saveEntrypointQuestionStatusFromLayerToSet(Map<String, QuestionStatusDto> questionStatusDtoMap, Long layerId, Long entrypointId) {
  }

  /**
   * Собираем элементы по слоям. Каждый слой layerId накладывается на другой, затирая старое значение или создавая новое.
   */
  private void saveEntrypointFromLayerToMap(Map<String, EntrypointDto> entrypointDtoMap, Long layerId) {
  }

}
