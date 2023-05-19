package ru.hhschool.segment.service;

import java.util.List;
import java.util.Optional;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.mapper.RoleMapper;
import ru.hhschool.segment.model.dto.RoleDto;
import ru.hhschool.segment.model.entity.Role;

public class RoleService {
  private final RoleDao roleDao;

  public RoleService(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  public List<RoleDto> getAll() {
    return RoleMapper.roleListToDto(roleDao.findAllOrderByName());
  }

  public Optional<RoleDto> getRoleById(long roleId) {
    Optional<Role> role = roleDao.findById(roleId);
    if (role.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(RoleMapper.roleToDto(role.get()));
  }

}
