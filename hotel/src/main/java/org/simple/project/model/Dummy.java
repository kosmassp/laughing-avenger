package org.simple.project.model;

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
@Table(name = "dummy")
@Searchable
public class Dummy extends BaseObject {
	
	private static final long serialVersionUID = 5739767491179359246L;
	private Long idDummy;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getIdDummy() {
		return idDummy;
	}
	
	public void setIdDummy(Long id) {
		this.idDummy = id;
	}

	@Override
	public String toString() {
		return "Dummy [id=" + idDummy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDummy == null) ? 0 : idDummy.hashCode());
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
		Dummy other = (Dummy) obj;
		if (idDummy == null) {
			if (other.idDummy != null)
				return false;
		} else if (!idDummy.equals(other.idDummy))
			return false;
		return true;
	}


	

}
