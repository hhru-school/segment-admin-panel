package ru.hhschool.segment.mapper.layer;

import java.util.ArrayList;
import java.util.List;
import ru.hhschool.segment.exception.HttpBadRequestException;
import ru.hhschool.segment.model.enums.LayerStateType;

public class LayerStatusMapper {

  public static List<LayerStateType> toStatusList(List<String> layerStringStatus) {
    if (layerStringStatus == null) {
      return List.of();
    }
    List<LayerStateType> layerStatusList = new ArrayList<>();
    for (String layerStatus : layerStringStatus) {
      try {
        layerStatusList.add(LayerStateType.valueOf(layerStatus));
      } catch (IllegalArgumentException err) {
        throw new HttpBadRequestException("Неверно заданный параметр.");
      }
    }
    return layerStatusList;
  }
}
