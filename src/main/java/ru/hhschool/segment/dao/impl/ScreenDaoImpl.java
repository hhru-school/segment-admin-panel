package ru.hhschool.segment.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.Query;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.enums.ScreenType;

public class ScreenDaoImpl extends ReadWriteDaoImpl<Screen, Long> implements ScreenDao {
  @Override
  public List<Screen> findAll(List<ScreenType> screenTypeList, Optional<String> androidVersion, Optional<String> iosVersion, boolean webSelect) {
    if (androidVersion.isEmpty() && iosVersion.isEmpty() && !webSelect) {
      return findAll(screenTypeList);
    }

    String androidSql = """
                      s.platforms && ARRAY(
                        SELECT p1.platform_id FROM Platforms p1 WHERE p1.platform = 'ANDROID'
                         AND
                          string_to_array(p1.application_version, '.')::::int[] <= string_to_array(:androidVersion, '.')::::int[]
                      ) 
        """;
    String iosSql = """
                      s.platforms && ARRAY(
                        SELECT p1.platform_id FROM Platforms p1 WHERE p1.platform = 'IOS'
                         AND
                          string_to_array(p1.application_version, '.')::::int[] <= string_to_array(:iosVersion, '.')::::int[]
                      ) 
        """;
    String webSql = " not s.platforms && ARRAY (SELECT p1.platform_id FROM Platforms p1 WHERE (p1.platform = 'WEB') ) ";


    if (androidVersion.isEmpty()) {
      androidSql = " not s.platforms && ARRAY (SELECT p1.platform_id FROM Platforms p1 WHERE p1.platform = 'ANDROID' ) ";
    }

    if (iosVersion.isEmpty()) {
      iosSql = " not s.platforms && ARRAY (SELECT p1.platform_id FROM Platforms p1 WHERE p1.platform = 'IOS' ) ";
    }

    if (webSelect) {
      webSql = " s.platforms && ARRAY (SELECT p1.platform_id FROM Platforms p1 WHERE p1.platform = 'WEB')";
    }

    String sql = "SELECT * FROM Screens s WHERE s.type IN (:screenTypeList) AND \n" +
        androidSql +
        "\n AND " +
        iosSql +
        "\n AND " +
        webSql;

    Query nativeQuery = em.createNativeQuery(sql, Screen.class);

    if (androidVersion.isPresent()) {
      nativeQuery.setParameter("androidVersion", androidVersion.get());
    }

    if (iosVersion.isPresent()) {
      nativeQuery.setParameter("iosVersion", iosVersion.get());
    }

    nativeQuery.setParameter("screenTypeList", screenTypeList.stream().map(Enum::toString).toList());

    return nativeQuery.getResultList();

  }

  public List<Screen> findAll(List<ScreenType> screenTypeList) {
    List<Screen> screenList = em.createQuery("SELECT s FROM Screen s WHERE s.type IN (:screenTypeList)")
        .setParameter("screenTypeList", screenTypeList)
        .getResultList();

    return screenList;
  }
}
