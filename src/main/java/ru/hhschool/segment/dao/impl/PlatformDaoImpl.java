package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.model.entity.Platform;

public class PlatformDaoImpl extends ReadWriteDaoImpl<Platform, Long> implements PlatformDao {
  public List<Platform> findAll(List<Long> platformIdList) {
    List<Platform> platformList = em.createQuery("SELECT p FROM Platform p WHERE p.id IN :platformIdList")
        .setParameter("platformIdList", platformIdList)
        .getResultList();
    return platformList;
  }
}
