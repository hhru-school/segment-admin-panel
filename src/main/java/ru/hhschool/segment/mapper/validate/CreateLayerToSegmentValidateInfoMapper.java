package ru.hhschool.segment.mapper.validate;

import java.util.List;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerRequirementDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerScreenDto;
import ru.hhschool.segment.model.dto.createlayer.validate.SegmentValidateInfoDto;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateEntrypointDto;
import ru.hhschool.segment.model.dto.layer.create.LayerCreateScreenDto;
import ru.hhschool.segment.model.dto.layer.create.LinkCreateQuestionDto;
import ru.hhschool.segment.model.dto.layer.create.LinkCreateScreenQuestionDto;

public class CreateLayerToSegmentValidateInfoMapper {
  public static SegmentValidateInfoDto toDto(
      Long segmentId,
      List<LinkCreateQuestionDto> fields,
      List<LayerCreateEntrypointDto> entryPoints
  ) {
    return new SegmentValidateInfoDto(
        segmentId,
        toInfoLayerRequirementDto(fields),
        toInfoLayerEntryPointDto(entryPoints)
    );
  }

  private static List<InfoLayerEntryPointDto> toInfoLayerEntryPointDto(List<LayerCreateEntrypointDto> entryPoints) {
    if (entryPoints == null) {
      return List.of();
    }
    return entryPoints.stream().map((entryPoint) ->
        new InfoLayerEntryPointDto(
            entryPoint.getId(),
            entryPoint.getTitle(),
            entryPoint.getDescription(),
            toInfoLayerScreenDto(entryPoint.getScreens())
        )
    ).toList();
  }

  private static List<InfoLayerScreenDto> toInfoLayerScreenDto(List<LayerCreateScreenDto> screens) {
    if (screens == null) {
      return List.of();
    }
    return screens.stream().map((screen) ->
        new InfoLayerScreenDto(
            screen.getId(),
            screen.getSegmentScreenEntrypointLinkId(),
            screen.getTitle(),
            screen.getDescription(),
            screen.getType(),
            screen.getState(),
            screen.getPosition(),
            screen.getAppVersions(),
            toInfoLayerQuestionDto(screen.getFields())
        )
    ).toList();
  }

  private static List<InfoLayerQuestionDto> toInfoLayerQuestionDto(List<LinkCreateScreenQuestionDto> fields) {
    if (fields == null) {
      return List.of();
    }
    return fields.stream().map((field) ->
        new InfoLayerQuestionDto(
            field.getId(),
            field.getScreenQuestionLinkId(),
            field.getTitle(),
            field.getVisibility(),
            field.getPosition()
        )
    ).toList();
  }

  private static List<InfoLayerRequirementDto> toInfoLayerRequirementDto(List<LinkCreateQuestionDto> fields) {
    if (fields == null) {
      return List.of();
    }
    return fields.stream().map((field) ->
        new InfoLayerRequirementDto(
            field.getId(),
            field.getQuestionRequiredLinkId(),
            field.getTitle(),
            field.isRequired()
        )
    ).toList();
  }


}
