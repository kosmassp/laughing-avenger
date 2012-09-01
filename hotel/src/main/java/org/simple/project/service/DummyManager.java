package org.simple.project.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.simple.project.model.Dummy;
import org.simple.project.model.Room;

public interface DummyManager extends GenericManager<Dummy, Long> {
	public List<Object[]> findNativeQuery(String sql, Object... param);
}
