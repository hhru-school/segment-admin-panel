package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Screen;

public class ScreenDaoImpl extends ReadWriteDaoImpl<Screen, Long> implements ScreenDao {

  @Override
  public List<Platform> findAllPlatforms() {

    List<Platform> platformList = em.createNativeQuery(
            """
                SELECT p.platform_id, p.platform, p.application_version FROM platforms p
                  WHERE p.platform_id IN (
                    SELECT DISTINCT unnest(ps.platforms) 
                      FROM (SELECT DISTINCT s.platforms FROM Screens s) AS ps
                  );
                """, Platform.class)
        .getResultList();

    return platformList;
  }
}
