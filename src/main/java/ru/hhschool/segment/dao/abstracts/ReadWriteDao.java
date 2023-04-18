package ru.hhschool.segment.dao.abstracts;

import java.util.List;

public interface ReadWriteDao<T, K> {

  void persist(final T entity);

  void update(final T entity);

  void delete(final T entity);

  void deleteByIdCascadeEnable(K id);

  void deleteByIdCascadeIgnore(K id);

  boolean existsById(K id);

  T findById(K id);

  List<T> findAll();
}
