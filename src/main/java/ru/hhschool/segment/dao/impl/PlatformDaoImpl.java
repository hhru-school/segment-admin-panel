package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.dao.abstracts.PlatfromDao;
import ru.hhschool.segment.model.entity.Layer;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlatformDaoImpl extends ReadWriteDaoImpl<Platform, Long> implements PlatfromDao {
  @Override
  public List<Platform> findAll(List<Long> platformIdList) {
    return em.createQuery("SELECT r FROM Platform r WHERE r.id IN :platformIdList")
        .setParameter("platformIdList", platformIdList)
        .getResultList();
  }
}
