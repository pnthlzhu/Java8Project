package com.pnt.Java8Project;

/**
 * 使用可重复注解
 * @author Administrator
 * @date 2016年11月20日
 */
@Hint("hint1")
@Hint("hint2")
public class Student extends Person {
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public String toString() {
		return "I'm Student, firstName=" + firstName + ",lastName=" + lastName;
	}
}
