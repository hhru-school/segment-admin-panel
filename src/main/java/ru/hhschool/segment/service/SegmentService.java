package ru.hhschool.segment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.transaction.Transactional;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.dao.abstracts.SegmentDao;
import ru.hhschool.segment.mapper.RoleMapper;
import ru.hhschool.segment.mapper.SegmentMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.dto.segment.SegmentCreateDto;
import ru.hhschool.segment.model.dto.segment.SegmentDto;
import ru.hhschool.segment.model.entity.Segment;

public class SegmentService {
  private final SegmentDao segmentDao;
  private final RoleDao roleDao;

  @Inject
  public SegmentService(SegmentDao segmentDao, RoleDao roleDao) {
    this.segmentDao = segmentDao;
    this.roleDao = roleDao;
  }

  @Transactional
  public List<SegmentDto> getAll(String searchQuery) {
    List<Segment> segmentList = segmentDao.findAll(searchQuery);

    List<SegmentDto> segmentDtoList = new ArrayList<>();
    for (Segment segment : segmentList) {
      List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.getRoleList()));
      segmentDtoList.add(SegmentMapper.segmentToDto(segment, roleList));
    }

    return segmentDtoList;
  }

  @Transactional
  public Optional<SegmentDto> add(SegmentCreateDto segmentCreateDto) {
    if (segmentCreateDto.getRoles() == null || segmentCreateDto.getRoles().isEmpty()) {
      return Optional.empty();
    }
    Optional<Segment> parentSegment = Optional.empty();
    if (segmentCreateDto.getParentSegment() != null && segmentCreateDto.getParentSegment().getId() != null) {
      parentSegment = segmentDao.findById(segmentCreateDto.getParentSegment().getId());
    }

    Segment segment = SegmentMapper.dtoToSegment(segmentCreateDto, parentSegment);
    segmentDao.persist(segment);
    List<RoleDto> roleList = RoleMapper.roleListToDto(roleDao.findAll(segment.getRoleList()));

    return Optional.of(SegmentMapper.segmentToDto(segment, roleList));
  }
}
