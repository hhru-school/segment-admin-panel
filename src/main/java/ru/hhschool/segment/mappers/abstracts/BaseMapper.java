package ru.hhschool.segment.mappers.abstracts;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<T, S> {

  T toEntity(S dto);

  S toDto(T entity);

  List<S> toDtoList(Collection<T> entityList);

  List<T> toEntityList(Collection<S> dtoList);

}
