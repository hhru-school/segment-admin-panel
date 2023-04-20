package ru.hhschool.segment.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ru.hhschool.segment.dao.abstracts.ReadWriteDao;

public class ReadWriteDaoImpl<T, K> implements ReadWriteDao<T, K> {
  private final Class<T> clazz;

  @PersistenceContext
  protected EntityManager em;

  @SuppressWarnings("unchecked")
  public ReadWriteDaoImpl() {
    this.clazz = (Class<T>) ((ParameterizedType) getClass()
        .getGenericSuperclass())
        .getActualTypeArguments()[0];
  }

  @Override
  public void persist(T entity) {
    em.persist(entity);
  }

  @Override
  public void update(T entity) {
    em.merge(entity);
  }

  @Override
  public void delete(T entity) {
    em.remove(em.contains(entity) ? entity : em.merge(entity));
  }

  @Override
  public void deleteByIdCascadeEnable(K id) {
    em.remove(em.find(clazz, id));
  }

  @Override
  public void deleteByIdCascadeIgnore(K id) {
    em.createQuery("DELETE FROM " + clazz.getName() + " u WHERE u.id = :id")
        .setParameter("id", id)
        .executeUpdate();
  }

  @Override
  public boolean existsById(K id) {
    return em.find(clazz, id) != null;
  }

  @Override
  public Optional<T> findById(K id) {
    return Optional.ofNullable(em.find(clazz, id));
  }

  @Override
  public List<T> findAll() {
    return em.createQuery("select u from " + clazz.getName() + " u", clazz)
        .getResultList();
  }
}
