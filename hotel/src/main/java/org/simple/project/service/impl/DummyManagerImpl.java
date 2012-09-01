package org.simple.project.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.simple.project.dao.DummyDao;
import org.simple.project.model.Dummy;
import org.simple.project.service.DummyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dummyManager")
public class DummyManagerImpl extends GenericManagerImpl<Dummy, Long> implements DummyManager {
	DummyDao dummyDao;
	
	@Autowired
	public DummyManagerImpl(DummyDao dummyDao) {
		super(dummyDao);
		this.dummyDao = dummyDao;
	}

	public List<Object[]> findNativeQuery(String sql, Object... param) {
		return dummyDao.findNativeQuery(sql,param);
	}

}
