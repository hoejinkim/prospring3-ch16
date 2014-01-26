package com.apress.prospring3.ch16.domain;

import java.io.Serializable;
import java.util.List;

public class Contacts implements Serializable {

	private static final long serialVersionUID = -1054183532248634633L;

	private List<Contact> contacts;

	public Contacts() {
	}

	public Contacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
