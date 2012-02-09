package org.simple.project.dao.hibernate;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.simple.project.dao.RoomTypeDao;
import org.simple.project.model.RoomType;
import org.springframework.stereotype.Repository;

@Repository("roomTypeDao")
public class RoomTypeDaoHibernate extends GenericDaoHibernate<RoomType, Long> implements
		RoomTypeDao {

	public RoomTypeDaoHibernate() {
		super(RoomType.class);
	}

	public List<RoomType> findByName(String name) {
		return getHibernateTemplate().find("from RoomType where name = ?", name);
	}

}
