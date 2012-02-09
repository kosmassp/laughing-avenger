package org.simple.project.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.appfuse.model.BaseObject;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

@Entity
@Table(name = "room_type")
@Searchable
public class RoomType extends BaseObject implements Serializable {
	
	private static final long serialVersionUID = 2366584273243236764L;
	
	private Long id;
	private String name;
	private String description;
	private Set<Room> rooms;
	
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
    
    @Column(nullable = false, length = 150)
    public String getDescription() {
		return description;
	}
	
	@OneToMany(mappedBy="roomType")
	public Set<Room> getRooms() {
		return rooms;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}


	@Override
	public String toString() {
		return "RoomType [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		RoomType other = (RoomType) obj;
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
