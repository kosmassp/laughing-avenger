package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.simple.project.model.RoomType;

public interface RoomTypeDao extends GenericDao<RoomType, Long> {
	public List<RoomType> findByName(String name);
}
