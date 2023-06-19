package ru.hhschool.segment.mapper.merge;

import ru.hhschool.segment.model.dto.merge.MergeResponseDto;
import ru.hhschool.segment.model.dto.merge.enums.MergeErrorType;
import ru.hhschool.segment.model.entity.Layer;

public class MergeResponseMapper {
  public static MergeResponseDto toDtoResponse(Layer entity) {
    MergeResponseDto mergeResponseDto = new MergeResponseDto();
    mergeResponseDto.setId(entity.getId());
    mergeResponseDto.setState(entity.getState());
    return mergeResponseDto;
  }

  public static MergeResponseDto toDtoResponse(Layer entity, MergeErrorType errorType, String errorMessage) {
    MergeResponseDto mergeResponseDto = new MergeResponseDto();
    mergeResponseDto.setId(entity.getId());
    mergeResponseDto.setState(entity.getState());
    mergeResponseDto.setErrorType(errorType);
    mergeResponseDto.setErrorMessage(errorMessage);
    return mergeResponseDto;
  }
}
