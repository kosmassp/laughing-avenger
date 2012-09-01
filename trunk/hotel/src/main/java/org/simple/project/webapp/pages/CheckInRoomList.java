package org.simple.project.webapp.pages;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.simple.project.model.Customer;
import org.simple.project.model.DefaultConstant;
import org.simple.project.model.Event;
import org.simple.project.model.Room;
import org.simple.project.model.TransactionCiCo;
import org.simple.project.service.DummyManager;
import org.simple.project.webapp.services.ServiceFacade;
import org.slf4j.Logger;

public class CheckInRoomList extends BasePage {
	
    @Inject
    private ServiceFacade serviceFacade;

    @Inject
	@Service("dummyManager")
	private DummyManager dummyManager;

	@Inject
	@Service("eventManager")
	private GenericManager<Event, Long> eventManager;

	@Inject
	private Logger log;
	
	@Component(id = "checkInRoomListForm")
	private Form form;

	
	Object onSuccess() {
		return this;
	}
	
	void beginRender() {
		if(event == null) setEvent(eventManager.get(DefaultConstant.DEFAULT_EVENT_ID));
	}


	@Component(id = "event", 
			parameters = {	"value=event",
							"encoder=eventEncoder",
							"model=eventModel",
				            "validate=required"})
	private Select eventField;
	
	@Persist
	private Event event;
	public Event getEvent(){
		return this.event;
	}
	public void setEvent(Event event){
		this.event = event;
	}

    @Cached
	public Object getEventModel() {
    	List x = serviceFacade.getEvents();
    	x.remove(0);
		return x;
	}

    public ValueEncoder<Event> getEventEncoder(){
    	return serviceFacade.getGenericEncoder("eventManager");
//    	return new GenericEncoder<Event>(eventManager.getAll());
    }

	private static final String SQL_ROOMS = 
		" select room, description, room_floor, name, room_number, id_room_type, price, id_event, book_status, book_time, book_transaction " +
		" from room_for_checkin " +
		" where 1 = 1 ";
	private static final String WHERE_EVENT = " and id_event = ? ";

	private static final String WHERE_STATUS_NOT_BOOKED = " and book_status is null ";
	private static final String WHERE_STATUS_BOOKED = " and book_status = 1 ";
//	private static final String WHERE_EVENT = " and event_type = ? ";
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getRooms(){
		String query = SQL_ROOMS;
		List<Object> param = new ArrayList<Object>();

		if(event != null){
			query = query + WHERE_EVENT;
			param.add(new BigInteger(event.getId().toString()));
		}
		
		List<Object[]> x = (List<Object[]>)dummyManager.findNativeQuery(query, param.toArray());
		List y = new ArrayList<Room>();
		for (Object[] o : x) {
			y.add(new ViewRoom(o));
		}
		
		return y;
	}
	
	public class ViewRoom {
		private BigInteger idRoom;
		private String description;
		private Integer roomFloor;
		private String name;
		private Integer roomNumber;
		private BigInteger idRoomType;
		private BigDecimal price;
		private BigInteger idEvent;
		private Integer bookStatus;
//		private Date bookTime;
		private BigInteger idTransaction;
		
		public BigInteger getRoom() { return idRoom; }
		public String getDescription() { return description; }
		public Integer getRoomFloor() { return roomFloor; }
		public String getName() { return name; }
		public Integer getRoomNumber() { return roomNumber; }
		public BigInteger getRoomType() {  return idRoomType; }
		public BigDecimal getPrice() { return price; }
		public BigInteger getEvent() { return idEvent; }
		public String getStatus() { 
			return bookStatus == null ? "AVAILABLE" : 
				(bookStatus.equals(TransactionCiCo.Status.BOOKED) ? "BOOKED" : "ERROR" ); 
		}
		public BigInteger getTransaction() { return idTransaction; }

		public ViewRoom(Object[] o){
			this.idRoom = (BigInteger) o[0] ;
			this.description = (String) o[1] ;
			this.roomFloor = (Integer) o[2] ;
			this.name = (String) o[3] ;
			this.roomNumber = (Integer) o[4] ;
			this.idRoomType = (BigInteger) o[5] ;
			this.price = (BigDecimal) o[6] ;
			this.idEvent = (BigInteger) o[7] ;
			this.bookStatus = (Integer) o[8] ;
			this.idTransaction = (BigInteger) o[10] ;
		}
		
	}


}
