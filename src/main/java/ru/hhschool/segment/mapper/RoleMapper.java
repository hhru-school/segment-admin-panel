package ru.hhschool.segment.mapper;

import java.util.List;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.entity.Role;

public class RoleMapper {
  public static RoleDto roleToDto(Role role) {
    RoleDto roleDto = new RoleDto(role.getId(), role.getName());

    return roleDto;
  }

  public static List<RoleDto> roleListToDto(List<Role> roleList) {
    return roleList
        .stream()
        .map(RoleMapper::roleToDto)
        .toList();
  }
}
