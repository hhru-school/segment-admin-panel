package ru.hhschool.segment.dao.impl;

import org.springframework.stereotype.Repository;
import ru.hhschool.segment.dao.abstracts.LayerDao;
import ru.hhschool.segment.model.entity.Layer;

@Repository
public class LayerDaoImpl extends ReadWriteDaoImpl<Layer, Long> implements LayerDao {
}
