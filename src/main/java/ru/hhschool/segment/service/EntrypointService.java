package ru.hhschool.segment.service;

import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.EntrypointDao;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.exception.HttpNotFoundException;
import ru.hhschool.segment.mapper.EntrypointMapper;
import ru.hhschool.segment.model.dto.EntrypointCreateDto;
import ru.hhschool.segment.model.dto.EntrypointDto;
import ru.hhschool.segment.model.entity.Entrypoint;
import ru.hhschool.segment.util.ExceptionMessageExtract;

public class EntrypointService {
  private final EntrypointDao entrypointDao;

  @Inject
  public EntrypointService(EntrypointDao entrypointDao) {
    this.entrypointDao = entrypointDao;
  }

  public List<EntrypointDto> getAllEntrypoint() {
    return EntrypointMapper.entrypointListToDtoList(entrypointDao.findAll());
  }

  public Optional<EntrypointDto> getEntrypointById(Long entrypointId) {
    Optional<Entrypoint> entrypoint = entrypointDao.findById(entrypointId);
    return Optional.of(
        entrypoint.map(EntrypointMapper::entrypointToDto)
            .orElseThrow(() -> new HttpNotFoundException("entrypointID not found."))
    );
  }

  @Transactional
  public Optional<EntrypointDto> add(EntrypointCreateDto entrypointCreateDto) {
    if (entrypointCreateDto.getTitle() == null || entrypointCreateDto.getTitle().isBlank()) {
      throw new HttpBadRequestException("Название(Title) неверно указанное значение или пустой.");
    }
    Entrypoint entrypoint = EntrypointMapper.dtoToEntrypoint(entrypointCreateDto);
    try {
      entrypointDao.persist(entrypoint);
    } catch (Exception err) {
      ExceptionMessageExtract.extractStackErrors(err);
    }

    return Optional.of(EntrypointMapper.entrypointToDto(entrypoint));
  }

}
