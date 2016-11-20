package com.pnt.Java8Project;

public class Person {
	protected String firstName;
	protected String lastName;
	
	public Person() {
		super();
	}

	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return "I'm Person, firstName=" + firstName + ",lastName=" + lastName;
	}
}
