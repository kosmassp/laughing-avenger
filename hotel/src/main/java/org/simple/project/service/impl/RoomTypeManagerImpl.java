package org.simple.project.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.simple.project.dao.RoomTypeDao;
import org.simple.project.model.RoomType;
import org.simple.project.service.RoomTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomTypeManager")
public class RoomTypeManagerImpl extends GenericManagerImpl<RoomType, Long> implements RoomTypeManager {
	RoomTypeDao roomTypeDao;
	
	@Autowired
	public RoomTypeManagerImpl(RoomTypeDao roomTypeDao) {
		super(roomTypeDao);
		this.roomTypeDao = roomTypeDao;
	}

	public List<RoomType> findByName(String name) {
		return roomTypeDao.findByName(name);
	}

}
