package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Role;

import java.util.List;

public interface PlatfromDao extends ReadWriteDao<Platform, Long> {
  List<Platform> findAll(List<Long> roleIdList);
}
