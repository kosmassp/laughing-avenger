package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.simple.project.model.Customer;
import org.simple.project.model.Dummy;

public interface DummyDao extends GenericDao<Dummy, Long> {
	public List<Object[]> findNativeQuery(String sql, Object... param);
}
