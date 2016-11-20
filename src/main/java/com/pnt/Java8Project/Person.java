package com.pnt.Java8Project;

/**
 * 使用注解容器
 * @author Administrator
 * @date 2016年11月20日
 */
@Hints({@Hint("hint1"), @Hint("hint2")})
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
