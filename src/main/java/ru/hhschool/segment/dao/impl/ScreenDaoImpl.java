package ru.hhschool.segment.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.Query;
import ru.hhschool.segment.dao.abstracts.ScreenDao;
import ru.hhschool.segment.model.entity.Screen;

public class ScreenDaoImpl extends ReadWriteDaoImpl<Screen, Long> implements ScreenDao {
  @Override
  public List<Screen> findAll(Optional<String> androidVersion, Optional<String> iosVersion, boolean webSelect) {
    if (androidVersion.isEmpty() && iosVersion.isEmpty() && !webSelect) {
      return findAll();
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

    StringBuilder sql = new StringBuilder();

    sql.append("SELECT * FROM Screens s WHERE \n")
        .append(androidSql)
        .append("\n AND ")
        .append(iosSql)
        .append("\n AND ")
        .append(webSql);

    Query nativeQuery = em.createNativeQuery(sql.toString(), Screen.class);

    if (androidVersion.isPresent()) {
      nativeQuery.setParameter("androidVersion", androidVersion.get());
    }

    if (iosVersion.isPresent()) {
      nativeQuery.setParameter("iosVersion", iosVersion.get());
    }

    return nativeQuery.getResultList();

  }
}
