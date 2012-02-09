package org.simple.project.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;
import org.appfuse.model.User;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Proxy;
/**
 * 
 * @author Kosmas
 *	Main transaction model.
 *	Used TransactionC(heck)i(n)C(heck)o(ut) instead of Transaction to prevent it from conflict with transactional manager used in compass. 
 */
@Entity
@Table(name = "transaction")
//@Proxy(lazy=false)
@Searchable
public class TransactionCiCo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1899563968705462948L;
	public static final class Status {
		public static final Integer CHECKED_OUT = 2;
		public static final Integer CHECKED_IN = 1;
		public static final Integer BOOKED = 0;
		public static final Integer DELETED = -1;
		public static final Integer CANCELLED = -2;
	}
	
	private Long id;
	private Customer customer;
	private Integer status;
	private Date checkInTime;
	private User checkInBy;
	private Date checkOutTime;
	private User checkOutBy;
	private String idCardStatus; //titip tidak titip
	private Room room;
	private Event event; // normal, high season, sales, custom.
	private BigDecimal roomPrice; // this one is the price which is used for the transaction
	
	private Set<FacilityTransaction> facilityTransaction;
	private Set<PurchaseTransaction> purchaseTransaction;
		
	/*this should be hidden from user*/
	private BigDecimal totalFee;	// total fee = final fee, basically its just the same as current fee
	private BigDecimal currentFee;	// current fee= fee that show currently billed
	private BigDecimal paidFee;		// paidFee = monet that have been received
	private BigDecimal unpaidFee;	// unpaidFee = currentFee - paidFee
	
	private Date lastUpdateTime;
	private Date createdDate;
	private User createdBy;
	private Date modifiedDate;
	private User modifiedBy;

	private String customerData;
	private String checkInByName;
	private String checkOutByName;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getId() {
		return id;
	}
    
    @ManyToOne
    @JoinColumn(name="id_customer")
	@Cascade({CascadeType.ALL})
	public Customer getCustomer() {
		return customer;
	}
    
	@Column(nullable = false)
	public Integer getStatus() {
		return status;
	}
	
	@Column(name="check_in_time", nullable = false)
	public Date getCheckInTime() {
		return checkInTime;
	}

    @ManyToOne
    @JoinColumn(name="check_in_by")
	public User getCheckInBy() {
		return checkInBy;
	}
	
	@Column(name="check_out_time", nullable = true)
	public Date getCheckOutTime() {
		return checkOutTime;
	}

    @ManyToOne
    @JoinColumn(name="check_out_by", nullable = true)
    public User getCheckOutBy() {
		return checkOutBy;
	}

	@Column(name="status_id_card", nullable = true)
	public String getIdCardStatus() {
		return idCardStatus;
	}

	@ManyToOne
    @JoinColumn(name="id_room")
	public Room getRoom() {
		return room;
	}

	@ManyToOne
    @JoinColumn(name="id_event")
	public Event getEvent() {
		return event;
	}
	
	
	@Column(nullable = false, precision=14, scale=2)
	public BigDecimal getRoomPrice() {
		return roomPrice;
	}
	
	@OneToMany(targetEntity = FacilityTransaction.class,  mappedBy = "transactionCiCo")
	@Cascade({CascadeType.ALL})
	public Set<FacilityTransaction> getFacilityTransaction() {
		return facilityTransaction;
	}
	
	@OneToMany(targetEntity = PurchaseTransaction.class, mappedBy = "transactionCiCo")
	@Cascade({CascadeType.ALL})
	public Set<PurchaseTransaction> getPurchaseTransaction() {
		return purchaseTransaction;
	}
	
	@Column(nullable = true, precision=14, scale=2)
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	@Column(nullable = true, precision=14, scale=2)
	public BigDecimal getCurrentFee() {
		return currentFee;
	}

	@Column(nullable = true, precision=14, scale=2)
	public BigDecimal getPaidFee() {
		return paidFee;
	}

	@Column(nullable = true, precision=14, scale=2)
	public BigDecimal getUnpaidFee() {
		return unpaidFee;
	}


	@Column(nullable = false)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	@Column(nullable = false)
	public Date getCreatedDate() {
		return createdDate;
	}

    @ManyToOne
    @JoinColumn(name="created_by", nullable = false)
	public User getCreatedBy() {
		return createdBy;
	}
    
	@Column(nullable = true)
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
    @ManyToOne
    @JoinColumn(name="modified_by", nullable = true)
	public User getModifiedBy() {
		return modifiedBy;
	}
    
	@Column(name="data_customer", nullable = false, length=250)
	public String getCustomerData() {
		return customerData;
	}

	@Column(nullable = false)
	public String getCheckInByName() {
		return checkInByName;
	}

	@Column(nullable = true)
	public String getCheckOutByName() {
		return checkOutByName;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCheckInTime(Date checkInTime) {
		this.checkInTime = checkInTime;
	}
	public void setCheckInBy(User checkInBy) {
		this.checkInBy = checkInBy;
	}
	public void setCheckOutTime(Date checkOutTime) {
		this.checkOutTime = checkOutTime;
	}
	public void setCheckOutBy(User checkOutBy) {
		this.checkOutBy = checkOutBy;
	}
	public void setIdCardStatus(String idCardStatus) {
		this.idCardStatus = idCardStatus;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public void setRoomPrice(BigDecimal roomPrice) {
		this.roomPrice = roomPrice;
	}
	public void setFacilityTransaction(Set<FacilityTransaction> facilityTransaction) {
		this.facilityTransaction = facilityTransaction;
	}
	public void setPurchaseTransaction(Set<PurchaseTransaction> purchaseTransaction) {
		this.purchaseTransaction = purchaseTransaction;
	}
	public void addFacilityTransaction(FacilityTransaction facilityTransaction) {
		if(this.facilityTransaction == null)
			this.facilityTransaction = new HashSet<FacilityTransaction>();
		this.facilityTransaction.add(facilityTransaction);
		facilityTransaction.setTransactionCiCo(this);
	}
	public void setPurchaseTransaction(PurchaseTransaction purchaseTransaction) {
		if(this.purchaseTransaction == null)
			this.purchaseTransaction = new HashSet<PurchaseTransaction>();
		this.purchaseTransaction.add(purchaseTransaction);
		purchaseTransaction.setTransactionCiCo(this);
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public void setCurrentFee(BigDecimal currentFee) {
		this.currentFee = currentFee;
	}
	public void setPaidFee(BigDecimal paidFee) {
		this.paidFee = paidFee;
	}
	public void setUnpaidFee(BigDecimal unpaidFee) {
		this.unpaidFee = unpaidFee;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public void setCustomerData(String customerData) {
		this.customerData = customerData;
	}
	public void setCheckInByName(String checkInByName) {
		this.checkInByName = checkInByName;
	}
	public void setCheckOutByName(String checkOutByName) {
		this.checkOutByName = checkOutByName;
	}

	
	
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customer=" + customer ;
//		return "Transaction [id=" + id + ", customer=" + customer + ", status="
//				+ status + ", checkInTime=" + checkInTime + ", checkInBy="
//				+ checkInBy + ", checkOutTime=" + checkOutTime
//				+ ", checkOutBy=" + checkOutBy + ", idCardStatus="
//				+ idCardStatus + ", room=" + room + ", event=" + event
//				+ ", roomPrice=" + roomPrice + ", facilityTransaction="
//				+ facilityTransaction + ", purchaseTransaction="
//				+ purchaseTransaction + ", totalFee=" + totalFee
//				+ ", currentFee=" + currentFee + ", paidFee=" + paidFee
//				+ ", unpaidFee=" + unpaidFee + ", lastUpdateTime="
//				+ lastUpdateTime + ", createdDate=" + createdDate
//				+ ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
//				+ ", modifiedBy=" + modifiedBy + ", customerData="
//				+ customerData + ", checkInByName=" + checkInByName
//				+ ", checkOutByName=" + checkOutByName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((checkOutBy == null) ? 0 : checkOutBy.hashCode());
		result = prime * result
				+ ((checkOutTime == null) ? 0 : checkOutTime.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionCiCo other = (TransactionCiCo) obj;
		if (checkOutBy == null) {
			if (other.checkOutBy != null)
				return false;
		} else if (!checkOutBy.equals(other.checkOutBy))
			return false;
		if (checkOutTime == null) {
			if (other.checkOutTime != null)
				return false;
		} else if (!checkOutTime.equals(other.checkOutTime))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}
	
	
	
	
}
