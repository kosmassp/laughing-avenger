package org.simple.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

@Entity
@Table(name = "event")
@Searchable
public class Event extends BaseObject implements Serializable, ListToMapModel {
	
	private static final long serialVersionUID = 4456539331132355209L;

//	public static final String NORMAL 		= "NORMAL";
//	public static final String NATAL 		= "NATAL";
//	public static final String PASKAH		= "PASKAH";
//	public static final String LIBURAN_SEKOLAH		= "LIBURAN SEKOLAH";
//	public static final String LEBARAN 		= "LEBARAN";
//	public static final String SALES 		= "SALES";
//	public static final String CUSTOM 		= "CUSTOM";
	
	private Long id;
	private String eventName; // normal, lebaran, natal, sales, custom

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
    public Long getId() {
		return id;
	}

	@Column(nullable = false, length = 45)
	public String getEventName() {
		return eventName;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
//		return "Event [id=" + id + ", eventName=" + eventName + "]";
		return eventName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Event other = (Event) obj;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public String getName() {
		return eventName;
	}

	
	
	
	
}
