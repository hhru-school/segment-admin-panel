package ru.hhschool.segment.dao.abstracts;

import java.util.List;
import java.util.Optional;

public interface ReadWriteDao<T, K> {

  void persist(final T entity);

  void update(final T entity);

  void delete(final T entity);

  void deleteByIdCascadeEnable(K id);

  void deleteByIdCascadeIgnore(K id);

  boolean existsById(K id);

  Optional<T> findById(K id);

  List<T> findAll();
}
