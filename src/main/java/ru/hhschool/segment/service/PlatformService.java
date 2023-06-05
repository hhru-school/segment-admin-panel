package ru.hhschool.segment.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.hhschool.segment.dao.abstracts.PlatformDao;
import ru.hhschool.segment.mapper.PlatformMapper;
import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.platform.PlatformVersionListDto;
import ru.hhschool.segment.model.enums.PlatformType;

public class PlatformService {
  private final PlatformDao platformDao;

  public PlatformService(PlatformDao platformDao) {
    this.platformDao = platformDao;
  }

  public List<PlatformVersionListDto> getAll() {
    List<PlatformDto> platformList = PlatformMapper.toDtoList(platformDao.findAll());

    Map<String, List<PlatformDto>> platformVersionMap = platformList
        .stream()
        .sorted(Comparator.comparing(PlatformDto::getVersion))
        .collect(Collectors.groupingBy(platformDto -> platformDto.getPlatform().toString()));

    List<PlatformVersionListDto> platformVersionListDtoList = platformVersionMap
        .entrySet()
        .stream()
        .map(e -> new PlatformVersionListDto(PlatformType.valueOf(e.getKey()), e.getValue()))
        .sorted(Comparator.comparing(PlatformVersionListDto::getPlatform))
        .toList();

    return platformVersionListDtoList;
  }

}
