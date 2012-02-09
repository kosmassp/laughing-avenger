package org.simple.project.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.simple.project.dao.RoomDao;
import org.simple.project.model.Room;
import org.springframework.stereotype.Repository;

@Repository("roomDao")
public class RoomDaoHibernate extends GenericDaoHibernate<Room, Long> implements RoomDao {

	public RoomDaoHibernate() {
		super(Room.class);
	}

	public List<Room> findByStatus(Integer status) {
		return getHibernateTemplate().find("from Room where status = ?", status);
	}

}
