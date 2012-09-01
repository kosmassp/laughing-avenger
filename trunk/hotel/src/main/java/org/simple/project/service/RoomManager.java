package org.simple.project.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.simple.project.model.Room;

public interface RoomManager extends GenericManager<Room, Long> {
	List<Room> findByStatus(Integer... status);

}
