package com.pnt.Java8Project;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FunctionalInterfaceTest {

	@Test
	public void test1() throws Exception {
		Converter<String, Integer> converter = (f) -> Integer.valueOf(f);
		Integer to = converter.convert("123");
		assertTrue(to.intValue() == 123);
	}
}
