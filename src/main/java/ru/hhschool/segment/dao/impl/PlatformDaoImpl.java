package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.model.entity.Platform;

public class PlatformDaoImpl extends ReadWriteDaoImpl<Platform, Long> implements PlatformDao {
  public List<Platform> findAll(List<Long> platformIdList) {
    if (platformIdList == null || platformIdList.isEmpty()) {
      return List.of();
    }
    List<Platform> platformList = em.createQuery("""
            SELECT p FROM Platform p
             WHERE p.id IN :platformIdList
             ORDER BY p.platform
            """)
        .setParameter("platformIdList", platformIdList)
        .getResultList();
    return platformList;
  }
}
