package com.pnt.Java8Project;

public class Student extends Person {
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public String toString() {
		return "I'm Student, firstName=" + firstName + ",lastName=" + lastName;
	}
}
