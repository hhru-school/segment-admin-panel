package ru.hhschool.segment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.LayerMapper;
import ru.hhschool.segment.mapper.basicInfo.LayerBasicInfoMapper;
import ru.hhschool.segment.mapper.change.MapperLayerChange;
import ru.hhschool.segment.model.dto.LayerDto;
import ru.hhschool.segment.model.dto.basicInfo.LayerBasicInfoDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class LayerService {
    private final LayerDao layerDao;
    private final Logger LOGGER = LoggerFactory.getLogger(LayerService.class);

    @Inject
    public LayerService(LayerDao layerDao) {
        this.layerDao = layerDao;
    }

    public List<LayerDto> getLayerDtoListForMainPage() {
        return LayerMapper.toDtoListForMainPage(layerDao.findAll());
    }

    public Optional<LayerChangeDto> getLayerChanges(Long layerId) {
        Optional<Layer> layer = layerDao.findByIdFetchEager(layerId);
        if (layer.isEmpty()) {
            return Optional.empty();
        }

        LayerChangeDto layerChangeDto = MapperLayerChange.layerChangeToDto(layer.get());
        return Optional.of(layerChangeDto);
    }

    @Transactional
    public Optional<LayerBasicInfoDto> getLayerDtoForBasicInfoPage(Long id) {
        Optional<Layer> layer = Optional.ofNullable(layerDao.findById(id));
        if (layer.isEmpty()) {
            return Optional.empty();
        }
        LayerBasicInfoDto layerBasicInfoDto = LayerBasicInfoMapper.toDtoForBasicInfoPage(layer.get(), layerDao.getAllParents(id));
        return Optional.of(layerBasicInfoDto);
    }
}
