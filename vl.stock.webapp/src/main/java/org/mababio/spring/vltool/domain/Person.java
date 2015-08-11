package org.mababio.spring.vltool.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Person {

	
	
	@Id
	private String firstLast;
	
	
	private String firstName;
	private String lastName;
	
	private Address addr;
	public Address getAddr() {
		return addr;
	}
	public Person setAddr(Address addr) {
		this.addr = addr;
		return this;
	}
	
	
	public Person(String fname, String lname ){
		
		this.firstName = fname;
		this.lastName = lname;
		this.firstLast = fname.concat(lname);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public Person setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public String getLastName() {
		return lastName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	public Person setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	
	
	
}
