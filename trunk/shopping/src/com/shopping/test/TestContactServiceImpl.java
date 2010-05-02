package com.shopping.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shopping.service.ContactService;
import com.shopping.service.impl.ContactServiceImpl;
import com.shopping.vo.ContactVo;

public class TestContactServiceImpl {

	private ContactService service = new ContactServiceImpl();
	@Test
	public void testAddContact() {
		ContactVo contact = new ContactVo();
		contact.setOrderId(1);
		contact.setName("abc");
		contact.setPostcode("123456");
		contact.setAddress("abcabd");
		contact.setTelphone("123456");
		contact.setFreetime(1);
		
		assertTrue(service.addContact(contact));
	}

	@Test
	public void testFindAllContact() {
		List<ContactVo> list = new ArrayList<ContactVo>();
		int start = 1;
		int limit = 2;
		list = service.findAllContact(start, limit);
		assertNotNull(list);
	}

	@Test
	public void testFindContactById() {
		ContactVo contact = new ContactVo();
		contact = service.findContactById(1);
		assertNotNull(contact);
	}

	@Test
	public void testFindContactByOrderId() {
		ContactVo contact = new ContactVo();
		contact = service.findContactByOrderId(1);
		assertNotNull(contact);
	}

	@Test
	public void testModifyContact() {

		ContactVo contact = new ContactVo();
		contact.setConId(1);
		contact.setOrderId(1);
		contact.setName("abc");
		contact.setPostcode("123456");
		contact.setAddress("abcabd");
		contact.setTelphone("123456");
		contact.setFreetime(1);
		
		assertTrue(service.modifyContact(contact));
	}

	@Test
	public void testRemoveContactById() {
		assertFalse(service.removeContactById(2));
	}

}
