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
@Table(name = "room")
@Searchable
public class Room extends BaseObject implements ListToMapModel,Comparable<Room>{
	
	private static final long serialVersionUID = 7189584255448736970L;

	public static final class Status {
		public static final Integer BOOKED = 2;
		public static final Integer CHECKED = 1;
		public static final Integer AVAILABLE = 0;
		public static final Integer OUT_OF_SERVICE = -1;
	}
	private Long id;
	private Integer number;
	private Integer floor;
	private Integer status; //booked:2, checked:1, available:0, under maintenance:-1
	private String name; //example: 1A1, 1A2, 1A3, 2A1, 2A2, 1B1, 1B2, 2B1,
	private String description; //facility availabilty, or whatever else
	private RoomType roomType; //vip, ac, standar, etc
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getId() {
		return id;
	}
	
	@Column(name="room_number", nullable = false)
	public Integer getNumber() {
		return number;
	}

	@Column(name="room_floor",nullable = false)
	public Integer getFloor() {
		return floor;
	}
	
	@Column(nullable = false)
	public Integer getStatus() {
		return status;
	}
	
	@Column(nullable = false, length=45)
	public String getName() {
		return name;
	}
	
	@Column(nullable = false, length=150)
	public String getDescription() {
		return description;
	}
	
	
    @ManyToOne
    @JoinColumn(name="id_room_type")
	public RoomType getRoomType() {
		return roomType;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setFloor(Integer floor) {
		this.floor = floor;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		return "Kamar No:" + number + "/ Lt:" + floor;
//		return "Room [id=" + id + ", number=" + number + ", floor=" + floor
//				+ ", status=" + status + ", name=" + name + ", description="
//				+ description + ", roomType=" + roomType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((floor == null) ? 0 : floor.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
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
		Room other = (Room) obj;
		if (floor == null) {
			if (other.floor != null)
				return false;
		} else if (!floor.equals(other.floor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (roomType == null) {
			if (other.roomType != roomType)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}

	public int compareTo(Room room) {
		if(this.floor != room.floor )
			return this.floor.compareTo(room.floor);
		if(this.number != room.number )
			return this.number.compareTo(room.number);
		
		return this.id.compareTo(room.getId()); 
	}
	
	

}
