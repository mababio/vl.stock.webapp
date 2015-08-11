package org.mababio.spring.vltool.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Address {

	
	@Id
	private String  id;
	
	private String state;
	
	private String city;

	public String getId() {
		return id;
	}

	
	public Address(String city, String state){
		this.state =  state;
		this.city = city;
		
	}
	public Address setId(String id) {
		this.id = id;
		return this;
	}

	public String getState() {
		return state;
	}

	public Address setState(String state) {
		this.state = state;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity(String city) {
		this.city = city;
		return this;
	}
	
	
	
}
