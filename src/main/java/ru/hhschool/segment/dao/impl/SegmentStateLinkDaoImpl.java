package ru.hhschool.segment.dao.impl;

import ru.hhschool.segment.dao.abstracts.SegmentStateLinkDao;
import ru.hhschool.segment.model.entity.SegmentStateLink;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class SegmentStateLinkDaoImpl extends ReadWriteDaoImpl<SegmentStateLink, Long> implements SegmentStateLinkDao {
  @Override
  public List<SegmentStateLink> findAll(Long layerId, String searchQuery) {
    if (searchQuery.isBlank()) {
      return findAll();
    } else {
      return em.createQuery("SELECT e FROM SegmentStateLink e WHERE e.layer.id = :layerId " +
              "AND LOWER(e.segment.title) LIKE LOWER(:searchQuery)")
          .setParameter("layerId", layerId)
          .setParameter("searchQuery", "%" + searchQuery + "%")
          .getResultList();
    }
  }

  @Override
  public List<SegmentStateLink> findAll(Long layerId) {
    return em.createQuery("SELECT e FROM SegmentStateLink e WHERE e.layer.id = :layerId")
        .setParameter("layerId", layerId)
        .getResultList();
  }

  @Override
  public Optional<SegmentStateLink> findById(Long layerId, Long segmentId) {
    try {
      return Optional.of(em.createQuery("SELECT e FROM SegmentStateLink e WHERE e.layer.id = :layerId AND e.segment.id = :segmentId", SegmentStateLink.class)
          .setParameter("layerId", layerId)
          .setParameter("segmentId", segmentId)
          .getSingleResult());
    } catch (NoResultException noResultException){
      return Optional.empty();
    }
  }
}
