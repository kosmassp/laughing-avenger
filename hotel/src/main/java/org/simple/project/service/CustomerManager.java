package org.simple.project.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.simple.project.model.Customer;

public interface CustomerManager extends GenericManager<Customer, Long> {
	public List<Customer> findByIdNumberAndType(String idNumber, String idType);

}
