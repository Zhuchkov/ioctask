package ua.rd.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SecondTestObject {

	private String firstName;
	private String lastName;
	private int id;
	private int secondId;

	SecondTestObject() {
		System.out.println("SecondTestObject def const");
	}

	public SecondTestObject(String firstName, String lastName) {
		System.out.println("SecondTestObject param const");
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getSecondId() {
		return secondId;
	}

	public void setSecondId(int secondId) {
		this.secondId = secondId;
	}

	@Override
	public String toString() {
		return "SecondTestObject [firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", secondId="
				+ secondId + "]";
	}

}
