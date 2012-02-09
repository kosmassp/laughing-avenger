package org.simple.project.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.simple.project.model.RoomType;

public interface RoomTypeManager extends GenericManager<RoomType, Long> {
	List<RoomType> findByName(String name);

}
