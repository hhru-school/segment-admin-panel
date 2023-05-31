package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.model.entity.Platform;
import ru.hhschool.segment.model.entity.Screen;

public interface ScreenDao extends ReadWriteDao<Screen, Long> {
  List<Platform> findAllPlatforms();

  List<Screen> findAll(Optional<String> androidVersion, Optional<String> iosVersion, boolean webSelect);
}
