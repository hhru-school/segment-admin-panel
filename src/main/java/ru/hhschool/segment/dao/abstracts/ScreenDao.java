package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.enums.ScreenType;

public interface ScreenDao extends ReadWriteDao<Screen, Long> {
  List<Screen> findAll(List<ScreenType> screenTypeList, Optional<String> androidVersion, Optional<String> iosVersion, boolean webSelect);

  List<Screen> findAll(List<ScreenType> screenTypeList);
}
