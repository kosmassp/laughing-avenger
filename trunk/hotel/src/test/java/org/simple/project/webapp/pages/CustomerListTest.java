package org.simple.project.webapp.pages;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.dom.Node;
import org.junit.Test;

public class CustomerListTest extends BasePageTestCase {
	
	@Test
	public void testList(){
		doc = tester.renderPage("customerList");
		assertNotNull(doc.getElementById("customerList"));
		assertTrue(doc.getElementById("customerList").find("tbody").getChildren().size()>=0);
	}

	@Test
	public void testEdit() {
	    doc = tester.renderPage("customerList");

	    Element table = doc.getElementById("customerList");
	    List<Node> rows = table.find("tbody").getChildren();
	    String id = ((Element) rows.get(0)).find("td/a").getChildMarkup().trim();
	    Element editLink = table.getElementById("customer-" + id);
	    doc = tester.clickLink(editLink);

	    ResourceBundle rb = ResourceBundle.getBundle(MESSAGES);

	    assertTrue(doc.toString().contains("<title>" +
	            rb.getString("customerDetail.title")));
	}
}
