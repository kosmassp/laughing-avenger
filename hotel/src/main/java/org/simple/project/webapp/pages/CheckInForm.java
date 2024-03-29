package org.simple.project.webapp.pages;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.DateField;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.simple.project.model.Customer;
import org.simple.project.model.DefaultConstant;
import org.simple.project.model.Event;
import org.simple.project.model.EventPrice;
import org.simple.project.model.Facility;
import org.simple.project.model.FacilityTransaction;
import org.simple.project.model.Room;
import org.simple.project.model.TransactionCiCo;
import org.simple.project.service.CustomerManager;
import org.simple.project.service.DummyManager;
import org.simple.project.service.RoomManager;
import org.simple.project.webapp.components.CheckboxSelect;
import org.simple.project.webapp.services.ServiceFacade;
import org.slf4j.Logger;

public class CheckInForm extends BasePage{
	@Inject
	private Logger log;

	@Persist
	private TransactionCiCo transactionCiCo;
	public TransactionCiCo getTransactionCiCo() {
		return transactionCiCo;
	}

	
	@Persist("client")
	private Customer customer;
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Allows setting customer object from another class (i.e. CustomerList)
	 *
	 * @param customer an initialized instance
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Inject
	@Service("transactionCiCoManager")
	private GenericManager<TransactionCiCo, Long> transactionCiCoManager;

	@Inject
	@Service("roomManager")
	private RoomManager roomManager;
	
	@Inject
	@Service("customerManager")
	private CustomerManager customerManager;

	@Inject
	@Service("dummyManager")
	private DummyManager dummyManager;

	@Inject
	@Service("facilityManager")
	private GenericManager<Facility,Long> facilityManager;
	
	@Inject
	@Service("eventManager")
	private GenericManager<Event,Long> eventManager;
	
	@Inject
	@Service("eventPriceManager")
	private GenericManager<EventPrice,Long> eventPriceManager;
	
    @Inject
    private ServiceFacade serviceFacade;
    
	@Component(id = "checkInForm")
	private Form form;

	@Component(id = "idNumber", parameters = {"value=customer.idNumber", "validate=regexp=\\d*"})
	private TextField idNumberField;

	@Component(id = "idType", 
			parameters = {	"value=customer.idType",
							"model=idTypeModel",
				            "validate=required",
				            "blankoption=never"  })
	private Select idTypeField;

	@Component(id = "name", parameters = {"value=customer.name", "validate=required"})
	private TextField nameField;

	@Component(id = "address", parameters = {"value=customer.address", "validate=required"})
	private TextField addressField;

	@Component(id = "phoneNumber", parameters = {"value=customer.phoneNumber"})
	private TextField phoneNumberField;

	@Component(id = "age", parameters = {"value=customer.age", "validate=min=15,max=150"})
	private TextField ageField;

	@Component(id = "birthdate", parameters = {"value=customer.birthdate", "format=dd-MMM-yyyy"})
	private DateField birthdateField;

	@Component(id = "zipCode", parameters = {"value=customer.zipCode"})
	private TextField zipCodeField;

	@Component(id = "room", 
			parameters = {	"value=transactionCiCo.room",
							"encoder=roomEncoder",
							"model=availableRooms",
				            "validate=required",
				            "blankoption=always",
				            "zone=roomPriceZone" })
	private Select roomField;

	@Component(id = "facility", 
			parameters = {	"model=facilityModel",
							"encoder=facilityEncoder",
							"selected=selectedFacilities"})
	private CheckboxSelect facilityField;

	@Component(id = "event", 
			parameters = {	"value=transactionCiCo.event",
							"encoder=eventEncoder",
							"model=eventModel",
				            "validate=required",
				            "zone=roomPriceZone"   })
	private Select eventField;

	@Component(id = "roomPrice", 
			parameters = {	"value=roomPrice",
							"readonly=true"})
	private TextField roomPriceField;

	@Component(id = "customRoomPrice", 
			parameters = {	"value=customRoomPrice",
							"validate=required" })
	private TextField customRoomPriceField;

//
//    @Component(id = "idType",
//            parameters = {
//                    "model=idTypeModel",
//                    "encoder=encoder",
//                    "validate=required",
//                    "blankoption=never"})
//    private Select idTypeField;
	
//	@OnEvent(component = "event", value=EventConstants.VALUE_CHANGED)
//	public void onValueChangeFromEvent(Object value){
//		System.out.println("TESTTTTT" + value);
//		System.out.println("XX -> "+getTransactionCiCo().getEvent());
//		
//	}
	
	private BigDecimal roomPrice;
	public BigDecimal getRoomPrice(){
		return roomPrice;
	}
	public void setRoomPrice(BigDecimal roomPrice){
		this.roomPrice = roomPrice ;
	}
	
	private BigDecimal customRoomPrice;
	public BigDecimal getCustomRoomPrice(){
		return customRoomPrice;
	}
	public void setCustomRoomPrice(BigDecimal customRoomPrice){
		this.customRoomPrice = customRoomPrice ;
	}
	
	
	@Component ( id = "roomPriceZone")
	private Zone roomPriceZone;
	public Object onValueChanged(Object re) 
	{
//		try{ testing purpose
//			System.out.println((Object[])dummyManager.findNativeQuery("select r.* from room r join transaction t on (t.id_room = r.id and t.status = ? ) ", TransactionCiCo.Status.CHECKED_IN).get(0)+"");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		if(re instanceof Room){
			System.out.println("On Room Value Changed Triggered");
			transactionCiCo.setRoom((Room)re);
		} else if(re instanceof Event){
			System.out.println("On Event Value Changed Triggered");
			transactionCiCo.setEvent((Event)re);
		}
	
		Event event = transactionCiCo.getEvent();
		Room room = transactionCiCo.getRoom();
		
		
		// just for views, the real value are when on success value.
		if(isEventCustom(event.getName())){
			customRoomPrice = getRoomPrice(eventManager.get(DefaultConstant.DEFAULT_EVENT_ID),room);
			roomPrice = BigDecimal.ZERO;
		} else {
			customRoomPrice = BigDecimal.ZERO;
			roomPrice = getRoomPrice(event,room);
		}
		return roomPriceZone.getBody();
	}

	private boolean isEventCustom(String eventName) {
 		return eventName.equals("Custom");
	}


	private boolean cancel;
	private boolean delete;
	private BigDecimal eventPrice;

	private List<Facility> selectedFacilities;
    public List<Facility> getSelectedFacilities(){
    	return selectedFacilities;
    }
    public void setSelectedFacilities(List<Facility> selectedFacilities){
    	this.selectedFacilities = selectedFacilities;
    }
    
	void beginRender() {
	    if (customer == null) {
	        customer = new Customer();
	    }
	    if (transactionCiCo == null) {
	        transactionCiCo = new TransactionCiCo();
	        transactionCiCo.setEvent(eventManager.get(DefaultConstant.DEFAULT_EVENT_ID));
	    }
        transactionCiCo.setRoom(null);
	}
	
	void onValidateForm() {
		if (!delete && !cancel) {
			// manually validate required fields or annotate the Customer object
			/*if (foo.getProperty() == null || user.getProperty().trim().equals("")) {
	                form.recordError("Property is a required field.");
	            }*/
		}
	}

	void onActivate(Long id) {
//		if (id != null) {
//			customer = customerManager.get(id);
//		}
	}

	Object onSuccess() {
		
		System.out.println(transactionCiCo.getRoom());
		System.out.println(customer);
		System.out.println(selectedFacilities);
		
		if (delete) return onDelete();
		if (cancel) return onCancel();

		log.debug("Check customer availablity...");
		List<Customer> foundedCustomers = customerManager.findByIdNumberAndType(customer.getIdNumber(), customer.getIdType());
		if(!foundedCustomers.isEmpty()){
			customer = foundedCustomers.get(0); // setting it to the first foundedCustomer since the possibility of having idNumber and idType not unique is almost impossible. (99% return 1 customer if any) 
			for (Customer foundedCustomer : foundedCustomers) {
				if(foundedCustomer.getName().equalsIgnoreCase(customer.getName()))
					customer = foundedCustomer;
			}
		}
		
//		boolean isNew = (customer.getId() == null);
//		if(isNew){
//			customerManager.save(customer);
//		}
		
		log.debug("Transaction check in...");
//		t.setCheckInBy(getSession());
		transactionCiCo.setCustomer(customer);
		Room room = transactionCiCo.getRoom();
		room.setStatus(Room.ServiceStatus.IN_SERVICE);
		transactionCiCo.setRoom(room);
		for (Facility facility : selectedFacilities) {
			transactionCiCo.addFacilityTransaction(new FacilityTransaction(facility));
		}
		transactionCiCo.setStatus(TransactionCiCo.Status.CHECKED_IN);
		transactionCiCo.setCheckInBy(getCurrentUser());
		transactionCiCo.setCheckInByName(getCurrentUser().getFullName());
		transactionCiCo.setCheckInTime(Calendar.getInstance().getTime());
		transactionCiCo.setCreatedBy(getCurrentUser());
		transactionCiCo.setCreatedDate(Calendar.getInstance().getTime());
		transactionCiCo.setCustomerData(customer.toString());
		//TODO eventPrice
		transactionCiCo.setRoomPrice(getRoomPrice(transactionCiCo.getEvent(),transactionCiCo.getRoom()));
		transactionCiCo.setLastUpdateTime(Calendar.getInstance().getTime());
		transactionCiCoManager.save(transactionCiCo);

//		if (isNew) {
//			customerList.addInfo(key, true);
//			return customerList;
//		} else {
			addInfo("checkin.success", true);
			return this;
//		}
	}

	void onSelectedFromDelete() {
		log.debug("Deleting customer...");
		delete = true;
	}

	void onSelectedFromCancel() {
		log.debug("Cancelling form...");
		cancel = true;
	}

	private BigDecimal getRoomPrice(Event event, Room room){
		try {
			String eventName = event.getEventName();
			String roomType = room.getRoomType().getName();
			
			if(isEventCustom(eventName)) return customRoomPrice;
			
			BigDecimal roomPrice = serviceFacade.getRoomPrice(roomType, eventName);
			if( roomPrice  != null)
				return roomPrice;
		}catch (NullPointerException e) {
			log.info("getPrice() on CheckInForm.java got NullPointer exception");
			log.info(e.getMessage());
			return BigDecimal.ZERO;
		}
		return BigDecimal.ZERO;
	}
	
//	public boolean getEventPriceReadOnly(){
//		try {
//			String event = getTransactionCiCo().getEvent().getEventName();
//			String roomType = getTransactionCiCo().getRoom().getRoomType().getName();
//
//			if(isEventCustom(event)) return false;
//			if(serviceFacade.getRoomPrice(roomType, event) == null) return false;
//		}catch (NullPointerException e) {
//			log.info("getEventPriceReadOnly() on CheckInForm.java got NullPointer exception");
//			log.info(e.getMessage());
//			return true;
//		}
//		return true;
//	}
	
	Object onDelete() {
//		customerManager.remove(customer.getId());
//		customerList.addInfo("customer.deleted", true);
		return this;
	}

	Object onCancel() {
		return this;
	}
	
    @Cached
	public Object getIdTypeModel() {
		return serviceFacade.getIdTypes();
	}

    @Cached
	public Object getFacilityModel() {
		return serviceFacade.getFacilities();
	}

    @Cached
	public Object getEventModel() {
		return serviceFacade.getEvents();
	}

    public Object getAvailableRooms() {
//    	List<Room> availableRoomList = roomManager.findByStatus(Room.ServiceStatus.AVAILABLE, Room.ServiceStatus.IN_SERVICE);
    	List<Room> availableRoomList = roomManager.findByStatus(Room.ServiceStatus.AVAILABLE);
		return availableRoomList;
	}
    
    public ValueEncoder<Facility> getFacilityEncoder(){
    	return serviceFacade.getGenericEncoder("facilityManager");
//    	return new GenericEncoder<Facility>(facilityManager.getAll());
    }
    
    public ValueEncoder<Room> getRoomEncoder(){
    	return serviceFacade.getGenericEncoder("roomManager");
//    	return new GenericEncoder<Room>(roomManager.getAll());
    }

    public ValueEncoder<Event> getEventEncoder(){
    	return serviceFacade.getGenericEncoder("eventManager");
//    	return new GenericEncoder<Event>(eventManager.getAll());
    }


}