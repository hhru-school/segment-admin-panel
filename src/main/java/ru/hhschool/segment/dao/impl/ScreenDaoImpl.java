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

  @Override
  public List<Screen> findAll(String androidVersion, String iosVersion, boolean webSelect) {
    List<Screen> screenList = List.of();

    if (androidVersion == null && iosVersion == null && !webSelect) {
      screenList = findAll();
    } else if (androidVersion != null && iosVersion != null && webSelect) {
      screenList = em.createNativeQuery(
              """
                   SELECT * FROM Screens s
                    WHERE s.platforms @> ARRAY (
                     SELECT p1.platform_id FROM Platforms p1
                       WHERE (p1.platform = 'ANDROID' AND (p1.application_version) <= :androidVersion)
                             OR (p1.platform = 'IOS' AND (p1.application_version) <= :iosVersion)
                             OR p1.platform = 'WEB'
                   )
                  """
              , Screen.class)
          .setParameter("androidVersion", androidVersion)
          .setParameter("iosVersion", iosVersion)
          .getResultList();
    } else if (androidVersion != null && iosVersion != null && !webSelect) {
      screenList = em.createNativeQuery(
              """
                   SELECT * FROM Screens s
                    WHERE s.platforms <@ ARRAY (
                     SELECT p1.platform_id FROM Platforms p1
                       WHERE (p1.platform = 'ANDROID' AND (p1.application_version) <= :androidVersion)
                             OR (p1.platform = 'IOS' AND (p1.application_version) <= :iosVersion)
                   )
                  """
              , Screen.class)
          .setParameter("androidVersion", androidVersion)
          .setParameter("iosVersion", iosVersion)
          .getResultList();
    } else if (androidVersion != null && iosVersion == null) {
      screenList = em.createNativeQuery(
              """
                    SELECT * FROM Screens s
                     WHERE s.platforms <@ ARRAY (
                      SELECT p1.platform_id FROM Platforms p1
                        WHERE (p1.platform = 'ANDROID' AND (p1.application_version) <= :androidVersion)
                              OR (:webSelect AND p1.platform = 'WEB')
                    )
                   """
              , Screen.class)
          .setParameter("androidVersion", androidVersion)
          .setParameter("webSelect", webSelect)
          .getResultList();
    } else if (androidVersion == null && iosVersion != null) {
      screenList = em.createNativeQuery(
              """
                   SELECT * FROM Screens s
                    WHERE s.platforms <@ ARRAY (
                     SELECT p1.platform_id FROM Platforms p1
                       WHERE (p1.platform = 'IOS' AND (p1.application_version) <= :iosVersion)
                         OR (:webSelect AND p1.platform = 'WEB')
                   )
                  """
              , Screen.class)
          .setParameter("iosVersion", iosVersion)
          .setParameter("webSelect", webSelect)
          .getResultList();
    }


     return screenList;
  }
}
