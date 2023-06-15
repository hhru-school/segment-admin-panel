package ru.hhschool.segment.mapper.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerScreenDto;
import ru.hhschool.segment.model.dto.createlayer.validate.ScreenValidateResultDto;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class ScreenValidateResultMapper {
  public static ScreenValidateResultDto toDto(InfoLayerScreenDto screen) {
    ScreenValidateResultDto screenValidateResultDto = new ScreenValidateResultDto(
        screen.getId(),
        screen.getTitle()
    );
    return screenValidateResultDto;
  }

  public static List<ScreenValidateResultDto> toListDto(Collection<InfoLayerScreenDto> screens) {
    return screens.stream()
        .map(ScreenValidateResultMapper::toDto)
        .sorted(Comparator.comparing(ScreenValidateResultDto::getScreenId))
        .toList();
  }
}
