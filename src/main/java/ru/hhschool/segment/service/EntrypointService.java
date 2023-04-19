package ru.hhschool.segment.service;

import java.util.List;
import javax.inject.Inject;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.model.dto.EntrypointDto;

public class EntrypointService {
  private final EntrypointDao entrypointDao;

  @Inject
  public EntrypointService(EntrypointDao entrypointDao) {
    this.entrypointDao = entrypointDao;
  }

  public List<EntrypointDto> getAllEntrypoint() {
    return EntrypointMapper.entrypointListToDtoList(entrypointDao.findAll());
  }
}
