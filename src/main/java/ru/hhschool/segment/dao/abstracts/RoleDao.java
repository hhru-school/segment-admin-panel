package ru.hhschool.segment.dao.abstracts;

import ru.hhschool.segment.model.entity.Role;

import java.util.List;

public interface RoleDao extends ReadWriteDao<Role, Long> {
  List<Role> findAllOrderByName();

  List<Role> findAll(List<Long> roleIdList);

}
