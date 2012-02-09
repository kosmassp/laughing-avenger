package org.simple.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

/**
 * This class represents the basic "user" object in AppFuse that allows for authentication
 * and user management.  It implements Acegi Security's UserDetails interface.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Updated by Dan Kibler (dan@getrolling.com)
 *         Extended to implement Acegi UserDetails interface
 *         by David Carter david@carter.net
 */
@Entity
@Table(name = "customer")
@Searchable
public class Customer extends BaseObject implements Serializable {

    private static final long serialVersionUID = 7172158569527643417L;
    public static final class TYPE_ID {
    	public static final String KTP 		= "KTP";
    	public static final String SIM 		= "SIM";
    	public static final String PASSPORT = "PASSPORT";
    	public static final String LAIN_LAIN= "LAIN-LAIN";
    };
	
	private Long id;
    private String idNumber;                    // required
    private String idType;						// required
    private String name; 						// required
    private String address;
    private String phoneNumber;
    private Integer age;
    private Date birthdate;
    private String zipCode;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Customer() {
    }
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId
	public Long getId() {
		return id;
	}

    @Column(name = "id_number", nullable = false, length = 50, unique = true)
    @SearchableProperty
	public String getIdNumber() {
		return idNumber;
	}

    @Column(name = "id_type", nullable = false, length = 45)
	public String getIdType() {
		return idType;
	}

    @Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return name;
	}

    @Column(name = "address", nullable = false, length = 50)
	public String getAddress() {
		return address;
	}

    @Column(name = "phone", nullable = true, length = 50)
	public String getPhoneNumber() {
		return phoneNumber;
	}

    @Column(name = "age", nullable = true)
	public Integer getAge() {
		return age;
	}

    @Column(name = "zip_code", nullable = true, length = 50)
	public String getZipCode() {
		return zipCode;
	}

	
    @Column(name = "birthdate", nullable = true)
    public Date getBirthdate() {
		return birthdate;
	}

//----------
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", idNumber=" + idNumber + ", idType="
				+ idType + ", name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", age=" + age
				+ ", birthdate=" + birthdate + ", zipCode=" + zipCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idNumber == null) ? 0 : idNumber.hashCode());
		result = prime * result + ((idType == null) ? 0 : idType.hashCode());
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
		Customer other = (Customer) obj;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		if (idType == null) {
			if (other.idType != null)
				return false;
		} else if (!idType.equals(other.idType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
    
}
