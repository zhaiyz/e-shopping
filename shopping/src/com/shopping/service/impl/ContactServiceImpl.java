package com.shopping.service.impl;

import java.util.List;

import com.shopping.dao.ContactDao;
import com.shopping.dao.impl.ContactDaoImpl;
import com.shopping.service.ContactService;
import com.shopping.vo.ContactVo;

public class ContactServiceImpl implements ContactService {

	private ContactDao dao = new ContactDaoImpl();

	public boolean addContactDao(ContactVo contact) {
		return dao.addContactDao(contact);
	}

	public List<ContactVo> findAllContact(int start, int limit) {
		return dao.findAllContact(start, limit);
	}

	public ContactVo findContactById(int id) {
		return dao.findContactById(id);
	}

	public ContactVo findContactByOrderId(int id) {
		return dao.findContactByOrderId(id);
	}

	public boolean modifyContact(ContactVo contact) {
		return dao.modifyContact(contact);
	}

	public boolean removeContactById(int id) {
		return dao.removeContactById(id);
	}

}
