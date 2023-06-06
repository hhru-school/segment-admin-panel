package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.PlatformDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;
import java.util.Objects;

public class SegmentViewScreenMapper {

  public static SegmentViewScreenDto toDtoForSelectedSegmentViewPage(SegmentScreenEntrypointLink link,
                                                                     Long viewLayerId,
                                                                     Boolean isNew,
                                                                     List<PlatformDto> platforms,
                                                                     List<SegmentViewQuestionDto> questions){
    Screen screen = link.getScreen();
    SegmentViewScreenDto segmentViewScreenDto = new SegmentViewScreenDto();
    segmentViewScreenDto.setId(screen.getId());
    segmentViewScreenDto.setTitle(screen.getTitle());
    segmentViewScreenDto.setDescription(screen.getDescription());
    segmentViewScreenDto.setType(screen.getType());
    segmentViewScreenDto.setState(link.getScreenState());
    segmentViewScreenDto.setIsNew(isNew);
    if (link.getLayer().getId().equals(viewLayerId) && link.getOldSegmentScreenEntrypointLink() != null){
      boolean positionChanged = !Objects.equals(link.getScreenPosition(), link.getOldSegmentScreenEntrypointLink().getScreenPosition());
      boolean stateChanged = !Objects.equals(link.getScreenState(), link.getOldSegmentScreenEntrypointLink().getScreenState());
      if (positionChanged) {
        segmentViewScreenDto.setOldPosition(link.getOldSegmentScreenEntrypointLink().getScreenPosition());
      }
      if (stateChanged) {
        segmentViewScreenDto.setOldState(link.getOldSegmentScreenEntrypointLink().getScreenState());
      }
    }
    segmentViewScreenDto.setAppVersions(platforms);
    segmentViewScreenDto.setFields(questions);
    return segmentViewScreenDto;
  }
}
