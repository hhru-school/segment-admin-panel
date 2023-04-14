package ru.hhschool.segment.service;

import org.springframework.stereotype.Service;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.model.dto.LayerDto;

import javax.inject.Inject;
import java.util.List;

@Service
public class LayerService {

  private final LayerDao layerDao;

  @Inject
  public LayerService(LayerDao layerDao) {
    this.layerDao = layerDao;
  }

  public List<LayerDto> getLayerDtoListForMainPage() {
    return LayerMapper.toDtoListForMainPage(layerDao.findAll());
  }
}
