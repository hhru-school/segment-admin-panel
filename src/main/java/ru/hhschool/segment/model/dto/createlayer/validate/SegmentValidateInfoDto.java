package ru.hhschool.segment.model.dto.createlayer.validate;

import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerEntryPointDto;
import ru.hhschool.segment.model.dto.createlayer.info.InfoLayerRequirementDto;

import java.util.List;

public class SegmentValidateInfoDto {
  private Long segmentId;
  private List<InfoLayerRequirementDto> fields;
  private List<InfoLayerEntryPointDto> entryPoints;

  public SegmentValidateInfoDto() {}

  public SegmentValidateInfoDto(Long segmentId, List<InfoLayerRequirementDto> fields, List<InfoLayerEntryPointDto> entryPoints) {
    this.segmentId = segmentId;
    this.fields = fields;
    this.entryPoints = entryPoints;
  }

  public Long getSegmentId() {
    return segmentId;
  }

  public void setSegmentId(Long segmentId) {
    this.segmentId = segmentId;
  }

  public List<InfoLayerRequirementDto> getFields() {
    return fields;
  }

  public void setFields(List<InfoLayerRequirementDto> fields) {
    this.fields = fields;
  }

  public List<InfoLayerEntryPointDto> getEntryPoints() {
    return entryPoints;
  }

  public void setEntryPoints(List<InfoLayerEntryPointDto> entryPoints) {
    this.entryPoints = entryPoints;
  }
}
