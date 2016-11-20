package com.pnt.Java8Project;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 方法和构造函数引用
 * Java 8 允许你通过::关键字获取方法或者构造函数的的引用。
 * @author Administrator
 * @date 2016年11月20日
 */
public class MethodReferenceTest {

	/**
	 * 引用一个静态方法
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Converter<String, Integer> converter = Integer::valueOf;
		Integer to = converter.convert("123");
		assertTrue(to.intValue() == 123);
	}
	
	/**
	 * 引用一个对象的方法
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String to = converter.convert("java");
		assertTrue("j".equals(to));
	}
	
	/**
	 * 引用构造函数
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("hlzhu", "pig");
		assertTrue(person instanceof Person);
		System.out.println(person);
		
		personFactory = Student::new;
		person = personFactory.create("ghy", "rabbit");
		assertTrue(person instanceof Student);
		System.out.println(person);
	}
}
