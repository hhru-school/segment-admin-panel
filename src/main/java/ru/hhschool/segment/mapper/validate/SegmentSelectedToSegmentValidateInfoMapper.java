package ru.hhschool.segment.mapper.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerQuestionDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerRequirementDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerScreenDto;
import ru.hhschool.segment.model.dto.createlayer.validate.SegmentValidateInfoDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.*;

import java.util.List;

public class SegmentSelectedToSegmentValidateInfoMapper {
  public static SegmentValidateInfoDto toDto(SegmentSelectedDto segmentSelectedDto) {
    return new SegmentValidateInfoDto(
        segmentSelectedDto.getSegmentId(),
        toInfoLayerRequirementDtoList(segmentSelectedDto.getFields()),
        toInfoLayerEntryPointDtoList(segmentSelectedDto.getEntryPoints())
    );
  }

  private static List<InfoLayerRequirementDto> toInfoLayerRequirementDtoList(List<SegmentViewRequirementDto> fields){
    return fields.stream().map(SegmentSelectedToSegmentValidateInfoMapper::toInfoLayerRequirementDto).toList();
  }

  private static InfoLayerRequirementDto toInfoLayerRequirementDto(SegmentViewRequirementDto field){
    return new InfoLayerRequirementDto(
        field.getId(),
        null,
        field.getTitle(),
        field.getRequired()
    );
  }

  private static List<InfoLayerEntryPointDto> toInfoLayerEntryPointDtoList(List<SegmentViewEntryPointDto> entryPoints){
    return entryPoints.stream().map(SegmentSelectedToSegmentValidateInfoMapper::toInfoLayerEntryPointDto).toList();
  }

  private static InfoLayerEntryPointDto toInfoLayerEntryPointDto(SegmentViewEntryPointDto entryPoint){
    return new InfoLayerEntryPointDto(
        entryPoint.getId(),
        entryPoint.getTitle(),
        entryPoint.getDescription(),
        toInfoLayerScreenDtoList(entryPoint.getScreens())
    );
  }

  private static List<InfoLayerScreenDto> toInfoLayerScreenDtoList(List<SegmentViewScreenDto> screens){
    return screens.stream().map(screen -> toInfoLayerScreenDto(screen, screens.indexOf(screen))).toList();
  }

  private static InfoLayerScreenDto toInfoLayerScreenDto(SegmentViewScreenDto screen, Integer position){
    return new InfoLayerScreenDto(
        screen.getId(),
        null,
        screen.getTitle(),
        screen.getDescription(),
        screen.getType(),
        screen.getState(),
        position,
        null,
        toInfoLayerQuestionDtoList(screen.getFields())
    );
  }

  private static List<InfoLayerQuestionDto> toInfoLayerQuestionDtoList(List<SegmentViewQuestionDto> questions){
    return questions.stream().map(question -> toInfoLayerQuestionDto(question, questions.indexOf(question))).toList();
  }

  private static InfoLayerQuestionDto toInfoLayerQuestionDto(SegmentViewQuestionDto question, Integer position){
    return new InfoLayerQuestionDto(
        question.getId(),
        null,
        question.getTitle(),
        question.getVisibility(),
        position
    );
  }
}
