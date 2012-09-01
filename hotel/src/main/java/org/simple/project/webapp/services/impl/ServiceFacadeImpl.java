package org.simple.project.webapp.services.impl;

import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.Context;
import org.appfuse.model.Role;
import org.appfuse.service.GenericManager;
import org.appfuse.service.MailEngine;
import org.appfuse.service.RoleManager;
import org.appfuse.service.UserManager;
import org.simple.project.model.Customer;
import org.simple.project.model.Event;
import org.simple.project.model.EventPrice;
import org.simple.project.model.Facility;
import org.simple.project.model.Purchase;
import org.simple.project.model.Room;
import org.simple.project.model.RoomType;
import org.simple.project.webapp.services.GenericEncoder;
import org.simple.project.webapp.services.ServiceFacade;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of the ServiceFacade Interface
 *
 * @author Serge Eby
 * @version $Id: ServiceFacadeImpl.java 5 2008-08-30 09:59:21Z serge.eby $
 */
public class ServiceFacadeImpl implements ServiceFacade {

    private Logger logger;
    private MailEngine mailEngine;
    private UserManager userManager;
    private RoleManager roleManager;
    private SimpleMailMessage mailMessage;
    private ThreadLocale threadLocale;

    private GenericManager<Facility, Long> 	facilityManager;
    private GenericManager<Purchase,Long>  	purchaseManager;
    private GenericManager<RoomType,Long>   roomTypeManager;
    private GenericManager<Room,Long>   	roomManager;
    private GenericManager<Event, Long> 	eventManager;
    private GenericManager<EventPrice,Long> eventPriceManager;
    
    private Map<String,GenericEncoder> genericEncoderMap;
    private Map<String,BigDecimal> eventPriceMap;

    public ServiceFacadeImpl(Logger logger, Context context, MailEngine mailEngine,
                             UserManager userMgr, RoleManager roleMgr,
                             GenericManager<Facility,Long> facilityMgr, GenericManager<Event,Long> eventMgr,
                             GenericManager<Purchase,Long> purchaseMgr, GenericManager<EventPrice,Long> eventPriceMgr,
                             GenericManager<RoomType,Long> roomTypeMgr, GenericManager<Room,Long> roomMgr,
                             SimpleMailMessage mailMessage, ThreadLocale threadLocale) {
        this.logger = logger;
        this.mailEngine = mailEngine;
        this.userManager = userMgr;
        this.roleManager = roleMgr;
        this.facilityManager = facilityMgr;
        this.purchaseManager = purchaseMgr;
        this.roomTypeManager = roomTypeMgr;
        this.roomManager = roomMgr;
        this.eventManager = eventMgr;
        this.eventPriceManager = eventPriceMgr;
        this.mailMessage = mailMessage;
        this.threadLocale = threadLocale;
        this.genericEncoderMap = new HashMap();
        this.eventPriceMap = new HashMap();
    }

    public MailEngine getMailEngine() {
        return mailEngine;
    }

    public SimpleMailMessage getMailMessage() {
        return mailMessage;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAvailableRoles() {
        List<Role> roles = roleManager.getAll();
        List<String> availableRoles = new ArrayList<String>();
        for (Role role : roles) {
            availableRoles.add(role.getName());
        }
        return availableRoles;
    }

    public Map<String, String> getAvailableCountries() {
        Map<String, String> countries = new HashMap<String, String>();
        Locale locale = threadLocale.getLocale();
        final String EMPTY = "";
        Locale[] availableLocales = Locale.getAvailableLocales();

        for (Locale l : availableLocales) {
            String name = l.getDisplayCountry(locale);
            String iso = l.getCountry();
            if (!EMPTY.equals(name) && !EMPTY.equals(iso)) {
                countries.put(iso, name);
            }
        }
        logger.debug("Number of countries added: " + countries.size());

        // Sort by value
        Map<String, String> sortedCountries = new TreeMap<String, String>(new CountryComparator(countries, locale));
        sortedCountries.putAll(countries);
        return sortedCountries;

    }

    /**
     * Class to compare LabelValues using their labels with locale-sensitive
     * behaviour.
     */
    private class CountryComparator implements Comparator<String> {
        private Collator c;
        private Map<String, String> unsortedMap;

        /**
         * Creates a new CountryComparator object.
         *
         * @param map of country and locale
         * @param locale The Locale used for localized String comparison.
         */
        public CountryComparator(Map<String, String> map, Locale locale) {
            unsortedMap = map;
            c = Collator.getInstance(locale);
        }

        /**
         * Compares the localized labels of two LabelValues.
         *
         * @param rhs The first String to compare.
         * @param lhs The second String to compare.
         * @return The value returned by comparing the localized labels.
         */

        public final int compare(String lhs, String rhs) {
            String lvalue = unsortedMap.get(lhs);
            String rvalue = unsortedMap.get(rhs);
            return c.compare(lvalue, rvalue);
        }
    }

    public List<String> getIdTypes() {
    	
    	List idTypeList = new ArrayList<String>();
    	idTypeList.add(Customer.TYPE_ID.KTP);
    	idTypeList.add(Customer.TYPE_ID.SIM);
    	idTypeList.add(Customer.TYPE_ID.PASSPORT);
    	idTypeList.add(Customer.TYPE_ID.LAIN_LAIN);
    	
        return idTypeList;
    }
    
    public List<Facility> getFacilities(){
    	return facilityManager.getAll();
    }

    public List<Event> getEvents(){
    	return eventManager.getAll();
    }

    public GenericEncoder getGenericEncoder(String genericEncoder){
    	//TODO must add reseter when there is a change in this getter (ex: new facility, new purchase)
    	if(genericEncoderMap.get(genericEncoder) == null){ // another way to reset genericEncoderMap getNull
    		genericEncoderMap.put("roomManager",new GenericEncoder<Room>(roomManager.getAll()));
    		genericEncoderMap.put("eventManager",new GenericEncoder<Event>(eventManager.getAll()));
    		genericEncoderMap.put("facilityManager",new GenericEncoder<Facility>(facilityManager.getAll()));
    		genericEncoderMap.put("puchaseManager",new GenericEncoder<Purchase>(purchaseManager.getAll()));
    		genericEncoderMap.put("roomTypeManager",new GenericEncoder<RoomType>(roomTypeManager.getAll()));
    	}
    		
    	return genericEncoderMap.get(genericEncoder);
    }
    
    public BigDecimal getRoomPrice(String roomType, String event){
    	if(eventPriceMap.get(roomType + event) == null){
    		List<EventPrice> eventPriceList = eventPriceManager.getAll();
    		for (EventPrice eventPrice : eventPriceList) {
    			String eventName = eventPrice.getEvent().getEventName();
    			String roomTypeName = eventPrice.getRoomType().getName();
    			BigDecimal roomPrice = eventPrice.getPrice();
    			eventPriceMap.put(roomTypeName + eventName , roomPrice);
    		} 
    	}    	
    	return eventPriceMap.get(roomType + event);
    }

}
