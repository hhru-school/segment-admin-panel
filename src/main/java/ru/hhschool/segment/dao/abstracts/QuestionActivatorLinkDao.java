package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.QuestionActivatorLink;

public interface QuestionActivatorLinkDao extends ReadWriteDao<QuestionActivatorLink, Long> {
  List<QuestionActivatorLink> findAllByLayerIdAndEntrypointId(Long layerId, Long entrypointId);
}
