package org.simple.project.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.simple.project.dao.CustomerDao;
import org.simple.project.model.Customer;
import org.simple.project.service.CustomerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerManager")
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager {
	CustomerDao customerDao;
	
	@Autowired
	public CustomerManagerImpl(CustomerDao customerDao) {
		super(customerDao);
		this.customerDao = customerDao;
	}

	public List<Customer> findByIdNumberAndType(String idNumber, String idType) {
		return customerDao.findByIdNumberAndType(idNumber, idType);
	}


}
