package ru.hhschool.segment.dao.impl;

import java.util.List;
import ru.hhschool.segment.dao.abstracts.RoleDao;
import ru.hhschool.segment.model.entity.Role;

public class RoleDaoImpl extends ReadWriteDaoImpl<Role, Long> implements RoleDao {
  @Override
  public List<Role> findAllOrderByName() {
    List<Role> roleList = em.createQuery("SELECT r FROM Role r ORDER BY r.name").getResultList();
    return roleList;
  }

  public List<Role> findAll(List<Long> roleIdList) {
    List<Role> roleList = em.createQuery("SELECT r FROM Role r WHERE r.id IN :roleIdList")
        .setParameter("roleIdList", roleIdList)
        .getResultList();
    return roleList;
  }
}
