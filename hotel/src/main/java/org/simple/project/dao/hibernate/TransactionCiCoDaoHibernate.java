package org.simple.project.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.simple.project.dao.TransactionCiCoDao;
import org.simple.project.model.TransactionCiCo;
import org.springframework.stereotype.Repository;

@Repository("transactionCiCoDao")
public class TransactionCiCoDaoHibernate extends GenericDaoHibernate<TransactionCiCo, Long> implements
	TransactionCiCoDao {

	public TransactionCiCoDaoHibernate() {
		super(TransactionCiCo.class);
	}

	public List<TransactionCiCo> findByCustomerName(String customerName) {
		return getHibernateTemplate().find("from TransactionCiCo where customer.name = ?", customerName);
	}

	public List<TransactionCiCo> findByRoomIdAndStatus(Long roomId, Integer trxStatus) {
		return getHibernateTemplate().find("from TransactionCiCo where room.id = ? and status = ? ", roomId, trxStatus);
	}


}
