package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.model.entity.Screen;

public class ScreenDaoImpl extends ReadWriteDaoImpl<Screen, Long> implements ScreenDao {

  @Override
  public List<List<Long>> findAllPlatform() {
    List<List<Long>> platformList = em.createQuery("SELECT DISTINCT s.platforms FROM Screen s")
        .getResultList();

    return platformList;
  }
}
