package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.simple.project.model.TransactionCiCo;

public interface TransactionCiCoDao extends GenericDao<TransactionCiCo, Long> {
	public List<TransactionCiCo> findByCustomerName(String customerName);
}
