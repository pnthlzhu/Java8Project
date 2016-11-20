package com.pnt.Java8Project;

import org.junit.Test;

public class AnnotationTest {

	@Test
	public void test1() throws Exception {
		Hint hint = Person.class.getAnnotation(Hint.class);
		System.out.println(hint);//null
		
		Hint[] hints = Student.class.getAnnotationsByType(Hint.class);
		System.out.println(hints.length);//2
	}
}
