package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import ru.hhschool.segment.model.entity.Role;

public interface RoleDao extends ReadWriteDao<Role, Long> {
  List<Role> findAllOrderByName();

  List<Role> findAll(List<Long> roleIdList);

}
