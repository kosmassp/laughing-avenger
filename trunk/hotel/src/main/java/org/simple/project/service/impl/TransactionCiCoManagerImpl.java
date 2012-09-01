package org.simple.project.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.simple.project.dao.TransactionCiCoDao;
import org.simple.project.model.Room;
import org.simple.project.model.TransactionCiCo;
import org.simple.project.service.RoomManager;
import org.simple.project.service.TransactionCiCoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("transactionCiCoManager")
public class TransactionCiCoManagerImpl extends GenericManagerImpl<TransactionCiCo, Long> implements TransactionCiCoManager {
	TransactionCiCoDao transactionCiCoDao;
	RoomManager roomManager;
//	CustomerManager customerManager;
//	GenericManager<FacilityTransaction, Long> facilityTransactionManager;
//	GenericManager<PurchaseTransaction, Long> purchaseTransactionManager;
	
	@Autowired
	public TransactionCiCoManagerImpl(TransactionCiCoDao transactionCiCoDao, RoomManager roomManager) {
		super(transactionCiCoDao);
		this.transactionCiCoDao = transactionCiCoDao;
		this.roomManager = roomManager;
	}

	public List<TransactionCiCo> findByCustomerName(String customerName) {
		return transactionCiCoDao.findByCustomerName(customerName);
	}

	public List<TransactionCiCo> findByRoomIdAndStatus(Long roomId, Integer trxStatus) {
		return transactionCiCoDao.findByRoomIdAndStatus(roomId, trxStatus);
	}

	@Override
    public TransactionCiCo save(TransactionCiCo transactionCiCo) {
		Room room  = transactionCiCo.getRoom();
		if(TransactionCiCo.Status.CHECKED_IN.equals(transactionCiCo.getStatus()))
			room.setStatus(Room.ServiceStatus.IN_SERVICE);
		else if(TransactionCiCo.Status.CHECKED_OUT.equals(transactionCiCo.getStatus()))
			room.setStatus(Room.ServiceStatus.AVAILABLE);
		System.out.println("TransactionCiCoManager.save() .. CHECK APAKAH ROOM STATUSNYA BENAR ATAU TIDAK ");
		roomManager.save(room);
		
//		customerManager.save(transactionCiCo.getCustomer());
//		
//		Set<FacilityTransaction> fTrxs = transactionCiCo.getFacilityTransaction();
//		if(fTrxs != null)
//		for (FacilityTransaction facilityTransaction : fTrxs) {
//			facilityTransactionManager.save(facilityTransaction);
//		}
//		
//		Set<PurchaseTransaction> pTrxs = transactionCiCo.getPurchaseTransaction();
//		if(pTrxs != null)
//		for (PurchaseTransaction purchaseTransaction : pTrxs) {
//			purchaseTransactionManager.save(purchaseTransaction);
//		}
		
//		TODO
//		facilityTransactionManager.saveAll(transactionCiCo.getFacilityTransaction());
//		purchaseTransactionManager.saveAll(transactionCiCo.getPurchaseTransaction());
        return dao.save(transactionCiCo);
    }

}
