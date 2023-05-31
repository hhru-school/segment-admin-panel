package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Platform;

public interface PlatformDao extends ReadWriteDao<Platform, Long> {
  List<Platform> findAll(List<Long> platformIdList);
}
