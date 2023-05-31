package ru.hhschool.segment.mapper.viewsegments.layerview;

import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewPlatformDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewQuestionDto;
import ru.hhschool.segment.model.dto.viewsegments.layerview.SegmentViewScreenDto;
import ru.hhschool.segment.model.entity.Screen;
import ru.hhschool.segment.model.entity.SegmentScreenEntrypointLink;

import java.util.List;

public class SegmentViewScreenMapper {

  public static SegmentViewScreenDto toDtoForSelectedSegmentViewPage(SegmentScreenEntrypointLink link,
                                                                     Boolean isNew,
                                                                     List<SegmentViewPlatformDto> platforms,
                                                                     List<SegmentViewQuestionDto> questions){
    Screen screen = link.getScreen();
    SegmentViewScreenDto segmentViewScreenDto = new SegmentViewScreenDto();
    segmentViewScreenDto.setId(screen.getId());
    segmentViewScreenDto.setTitle(screen.getTitle());
    segmentViewScreenDto.setDescription(screen.getDescription());
    segmentViewScreenDto.setType(screen.getType());
    segmentViewScreenDto.setState(link.getScreenState());
    segmentViewScreenDto.setNew(isNew);
    if (link.getOldSegmentScreenEntrypointLink() != null){
      segmentViewScreenDto.setOldPosition(link.getOldSegmentScreenEntrypointLink().getScreenPosition());
    }
    segmentViewScreenDto.setPlatforms(platforms);
    segmentViewScreenDto.setQuestions(questions);
    return segmentViewScreenDto;
  }
}
