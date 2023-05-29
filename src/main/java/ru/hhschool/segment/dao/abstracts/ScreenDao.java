package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Screen;

public interface ScreenDao extends ReadWriteDao<Screen, Long> {
  List<List<Long>> findAllPlatform();
}
