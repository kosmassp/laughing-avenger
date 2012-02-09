package org.simple.project.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;


@Entity
@Table(name = "purchase")
@Searchable
public class Purchase extends BaseObject implements Serializable, ListToMapModel {
	private static final long serialVersionUID = 5780713354012009990L;

	private Long id;
	private String name;
	private BigDecimal defaultPrice;

	
//	private List<PurchaseTransaction> purchaseTransaction; // might be useless. make it transient or something so it wouldnt make to deep hibernate. 
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getId() {
		return id;
	}
    
	@Column(nullable = false, length = 45)
	public String getName() {
		return name;
	}

	@Column(name="default_price", nullable = false, precision=14, scale=2)
	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDefaultPrice(BigDecimal defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", name=" + name + ", defaultPrice="
				+ defaultPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((defaultPrice == null) ? 0 : defaultPrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Purchase other = (Purchase) obj;
		if (defaultPrice == null) {
			if (other.defaultPrice != null)
				return false;
		} else if (!defaultPrice.equals(other.defaultPrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
