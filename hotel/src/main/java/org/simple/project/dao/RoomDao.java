package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.simple.project.model.Room;

public interface RoomDao extends GenericDao<Room, Long> {
	public List<Room> findByStatus(Integer... status);
}
