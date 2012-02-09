package org.simple.project.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.simple.project.dao.CustomerDao;
import org.simple.project.model.Customer;
import org.springframework.stereotype.Repository;

@Repository("customerDao")
public class CustomerDaoHibernate extends GenericDaoHibernate<Customer, Long> implements
	CustomerDao {

	public CustomerDaoHibernate() {
		super(Customer.class);
	}

	public List<Customer> findByIdNumberAndType(String idNumber, String idType) {
		return getHibernateTemplate().find("from Customer where idNumber = ? and idType = ?", idNumber, idType);
	}

}
