package org.simple.project.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

@Entity
@Table(name = "transaction_purchase")
@Searchable
public class PurchaseTransaction extends BaseObject implements Serializable{
	private static final long serialVersionUID = 5209590532243652191L;

	private Long id;
	private TransactionCiCo transactionCiCo;
	private Purchase purchase;
	private Integer quantity;
	private BigDecimal price;
	private BigDecimal totalPrice;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getId() {
		return id;
	}
	
    @ManyToOne(targetEntity = TransactionCiCo.class)
    @JoinColumn(name="id_transaction")
	public TransactionCiCo getTransactionCiCo() {
		return transactionCiCo;
	}

    @ManyToOne
    @JoinColumn(name="id_purchase")
    public Purchase getPurchase() {
		return purchase;
	}
    
    
	@Column(nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	@Column(nullable = false, precision=14, scale=2)
	public BigDecimal getPrice() {
		return price;
	}
	
	@Column(name = "total_price", precision=14, scale=2)
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTransactionCiCo(TransactionCiCo transactionCiCo) {
		this.transactionCiCo = transactionCiCo;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "PurchaseTransaction [id=" + id + ", transaction=" + transactionCiCo
				+ ", purchase=" + purchase + ", quantity=" + quantity
				+ ", price=" + price + ", totalPrice=" + totalPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((purchase == null) ? 0 : purchase.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((totalPrice == null) ? 0 : totalPrice.hashCode());
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
		PurchaseTransaction other = (PurchaseTransaction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (purchase == null) {
			if (other.purchase != null)
				return false;
		} else if (!purchase.equals(other.purchase))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalPrice == null) {
			if (other.totalPrice != null)
				return false;
		} else if (!totalPrice.equals(other.totalPrice))
			return false;
		return true;
	}

	
	
	
	

}
