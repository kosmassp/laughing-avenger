package org.simple.project.webapp.pages;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFormTest extends BasePageTestCase {

    @Test
    public void testCancel() throws Exception {
        doc = tester.renderPage("customerList");
        Element table = doc.getElementById("customerList");
        List<Node> rows = table.find("tbody").getChildren();
        String id = ((Element) rows.get(0)).find("td/a").getChildMarkup().trim();

        Element editLink = table.getElementById("customer-" + id);
        doc = tester.clickLink(editLink);

        Element cancelButton = doc.getElementById("cancel");
        doc = tester.clickSubmit(cancelButton, fieldValues);

        ResourceBundle rb = ResourceBundle.getBundle(MESSAGES);

        assertTrue(doc.toString().contains("<title>" +
                rb.getString("customerList.title")));
    }

    @Test
    public void testSave() throws Exception {
        doc = tester.renderPage("customerForm");

        Element form = doc.getElementById("customerForm");
        assertNotNull(form);
            
        // enter all required fields
        fieldValues.put("idNumber", "999999999");
        fieldValues.put("idType", "2");
        fieldValues.put("name", "Web Test Name");
        fieldValues.put("address", "Address");
        fieldValues.put("phoneNumber", "12345678");
        fieldValues.put("age", "22");
        fieldValues.put("birthdate", "1988-01-01");
        fieldValues.put("zipCode", "11111");

        doc = tester.submitForm(form, fieldValues);

        Element errors = doc.getElementById("errorMessages");

        if (errors != null) {
            System.out.println(errors);
        }

        assertNull(doc.getElementById("errorMessages"));

        Element successMessages = doc.getElementById("successMessages");
        assertNotNull(successMessages);
        assertTrue(successMessages.toString().contains("added successfully"));
        Element table = doc.getElementById("customerList");
        assertNotNull(table);
    }

    @Test
    public void testRemove() throws Exception {
        doc = tester.renderPage("customerList");
        Element table = doc.getElementById("customerList");
        List<Node> rows = table.find("tbody").getChildren();
        String id = ((Element) rows.get(1)).find("td/a").getChildMarkup().trim();

        Element editLink = table.getElementById("customer-" + id);
        doc = tester.clickLink(editLink);

        Element deleteButton = doc.getElementById("delete");
        doc = tester.clickSubmit(deleteButton, fieldValues);
        assertTrue(doc.toString().contains("deleted successfully"));
    }
}