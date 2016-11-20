package com.pnt.Java8Project;

@FunctionalInterface
public interface PersonFactory<P extends Person> {

	P create(String firstName, String lastName);
}
