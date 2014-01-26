package com.apress.prospring3.ch16.restful;

import java.util.Date;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.apress.prospring3.ch16.domain.Contact;
import com.apress.prospring3.ch16.domain.Contacts;

public class RestfulClientSample {

	private static final String URL_GET_ALL_CONTACTS = "http://localhost:8080/ch16/restful/contact/listdata";
	private static final String URL_GET_CONTACT_BY_ID = "http://localhost:8080/ch16/restful/contact/{id}";
	private static final String URL_CREATE_CONTACT = "http://localhost:8080/ch16/restful/contact";
	private static final String URL_UPDATE_CONTACT = "http://localhost:8080/ch16/restful/contact/{id}";
	private static final String URL_DELETE_CONTACT = "http://localhost:8080/ch16/restful/contact/{id}";

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:restful-client-app-context.xml");
		ctx.refresh();

		Contacts contacts;
		Contact contact;

		RestTemplate restTemplate = ctx.getBean("restTemplate",
				RestTemplate.class);

		// 전체 연락처 조회 테스트
		System.out.println("Testing retrieve all contacts:");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS,
				Contacts.class);
		listContacts(contacts);
		
		// id로 연락처 조회 테스트
		System.out.println("Testing retrieve a contact by id: ");
		contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
		System.out.println(contact);
		
		// 연락처 업데이트 테스트
		contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
		contact.setFirstName("Kim Fung");
		System.out.println("Testing update contact by id: ");
		restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
		System.out.println("Contact update successfully: " + contact);
		
		// 연락처 삭제 테스트
		restTemplate.delete(URL_DELETE_CONTACT, 1);
		System.out.println("Testing delete contact by id: ");
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
		
		// 연락처 생성 테스트
		System.out.println("Testing create contact: ");
		contact = new Contact();
		contact.setFirstName("James");
		contact.setLastName("Gosling");
		contact.setBirthDate(new Date());
		contact = restTemplate.postForObject(URL_CREATE_CONTACT, contact, Contact.class);
		contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
		listContacts(contacts);
	}

	private static void listContacts(Contacts contacts) {
		for (Contact contact : contacts.getContacts()) {
			System.out.println(contact);
		}
	}

}
