package org.simple.project.webapp.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.EventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.simple.project.model.Customer;
import org.slf4j.Logger;

public class CustomerList extends BasePage {
	
	@Inject
	@Service("customerManager")
	private GenericManager<Customer, Long> customerManager;
	
	@Inject
	private Logger log;

	@InjectPage
	private CustomerForm form;

	
	@Property
	private Customer customer;

	@Component(parameters = {"event=add"})
	private EventLink addTop, addBottom;

	@Component(parameters = {"event=done"})
	private EventLink doneTop, doneBottom;
	
	
	public List<Customer> getCustomers(){
		return customerManager.getAll();
	}
	
	Object onDone(){
		return MainMenu.class;
	}
	
	Object onAdd() {
	    form.setCustomer(new Customer());
	    return form;
	}

	Object onActionFromEdit(Long id) {
	    log.debug("fetching customer with id: {}", id);
	    return form;
	}
	
	
	

}
