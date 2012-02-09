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
@Table(name = "event_price")
@Searchable
public class EventPrice extends BaseObject implements Serializable{
	private static final long serialVersionUID = 4940093933497403459L;
	
	private Long id;
	private Event event;
	private RoomType roomType;
	private BigDecimal price;
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SearchableId
	public Long getId() {
		return id;
	}

    @ManyToOne
    @JoinColumn(name="id_event")
	public Event getEvent() {
		return event;
	}


    @ManyToOne
    @JoinColumn(name="id_room_type")
	public RoomType getRoomType() {
		return roomType;
	}


	@Column(nullable = false, precision=14, scale=2)
	public BigDecimal getPrice() {
		return price;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setEvent(Event event) {
		this.event = event;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "EventPrice [id=" + id + ", event=" + event + ", roomType="
				+ roomType + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((roomType == null) ? 0 : roomType.hashCode());
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
		EventPrice other = (EventPrice) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		return true;
	}
	
	
}
