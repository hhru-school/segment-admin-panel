package ru.hhschool.segment.service;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.mapper.MapperLayerChangeDto;
import ru.hhschool.segment.model.dto.change.LayerChangeDto;
import ru.hhschool.segment.model.entity.Layer;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@Service
public class LayerService {
    private Logger LOGGER = LoggerFactory.getLogger(LayerService.class);
    private final LayerDao layerDao;

    @Inject
    public LayerService(LayerDao layerDao) {
        this.layerDao = layerDao;
    }

    public LayerChangeDto getLayerChange(@NotNull Long layerId) {
        Layer layer = layerDao.findById(layerId);
        if (layer == null) {
            LOGGER.error("LayerId: {} not found.", layerId);
            throw new RuntimeException("LayerId not found.");
        }
        LayerChangeDto layerChangeDto = MapperLayerChangeDto.layerChangeToDto(layer);

        return layerChangeDto;
    }
}
