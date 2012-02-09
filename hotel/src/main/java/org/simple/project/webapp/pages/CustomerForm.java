package org.simple.project.webapp.pages;

import org.apache.tapestry5.annotations.Cached;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Service;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.appfuse.service.GenericManager;
import org.simple.project.model.Customer;
import org.simple.project.webapp.services.ServiceFacade;
import org.slf4j.Logger;

public class CustomerForm extends BasePage{
	@Inject
	private Logger log;

	@Inject
	@Service("customerManager")
	private GenericManager<Customer, Long> customerManager;

	@Persist
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

    @Inject
    private ServiceFacade serviceFacade;
    
	/**
	 * Allows setting customer object from another class (i.e. CustomerList)
	 *
	 * @param customer an initialized instance
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@InjectPage
	private CustomerList customerList;

	@Component(id = "customerForm")
	private Form form;
//
//    @Component(id = "idType",
//            parameters = {
//                    "model=idTypeModel",
//                    "encoder=encoder",
//                    "validate=required",
//                    "blankoption=never"})
//    private Select idTypeField;
	
	private boolean cancel;
	private boolean delete;

	void onValidateForm() {
		if (!delete && !cancel) {
			// manually validate required fields or annotate the Customer object
			/*if (foo.getProperty() == null || user.getProperty().trim().equals("")) {
	                form.recordError("Property is a required field.");
	            }*/
		}
	}

	void onActivate(Long id) {
		if (id != null) {
			customer = customerManager.get(id);
		}
	}

	Object onSuccess() {
		if (delete) return onDelete();
		if (cancel) return onCancel();

		log.debug("Saving customer...");

		boolean isNew = (getCustomer().getId() == null);

		customerManager.save(customer);

		String key = (isNew) ? "customer.added" : "customer.updated";

		if (isNew) {
			customerList.addInfo(key, true);
			return customerList;
		} else {
			addInfo(key, true);
			return this;
		}
	}

	void onSelectedFromDelete() {
		log.debug("Deleting customer...");
		delete = true;
	}

	void onSelectedFromCancel() {
		log.debug("Cancelling form...");
		cancel = true;
	}

	Object onDelete() {
		customerManager.remove(customer.getId());
		customerList.addInfo("customer.deleted", true);
		return customerList;
	}

	Object onCancel() {
		return customerList;
	}
	
    @Cached
	Object getIdTypeModel() {
		return serviceFacade.getIdTypes();
	}

}