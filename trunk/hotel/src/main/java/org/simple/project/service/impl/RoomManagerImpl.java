package org.simple.project.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.simple.project.dao.RoomDao;
import org.simple.project.model.Room;
import org.simple.project.service.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomManager")
public class RoomManagerImpl extends GenericManagerImpl<Room, Long> implements RoomManager {
	RoomDao roomDao;
	
	@Autowired
	public RoomManagerImpl(RoomDao roomDao) {
		super(roomDao);
		this.roomDao = roomDao;
	}

	public List<Room> findByStatus(Integer status) {
		return roomDao.findByStatus(status);
	}

}
