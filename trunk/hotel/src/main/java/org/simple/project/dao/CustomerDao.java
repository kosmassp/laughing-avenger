package org.simple.project.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import org.simple.project.model.Customer;

public interface CustomerDao extends GenericDao<Customer, Long> {
	public List<Customer> findByIdNumberAndType(String idNumber, String idType);
}
