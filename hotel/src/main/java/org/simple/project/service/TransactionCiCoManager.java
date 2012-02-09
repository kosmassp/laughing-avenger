package org.simple.project.service;

import java.util.List;

import org.appfuse.service.GenericManager;
import org.simple.project.model.TransactionCiCo;

public interface TransactionCiCoManager extends GenericManager<TransactionCiCo, Long> {
	List<TransactionCiCo> findByCustomerName(String customerName);

}
