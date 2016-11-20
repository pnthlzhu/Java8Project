package com.pnt.Java8Project;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Lambda范围
 * @author Administrator
 * @date 2016年11月20日
 */
public class LambdaScopeTest {
	
	//成员变量
	public int outerNum;
	//静态变量
	public static int outerStaticNum = 30;
	
	//默认方法无法在lambda表达式内容部被访问

	/**
	 * 访问局部变量
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		//可以访问lambda表达式外部的final局部变量
		//在lambda表达式内部企图改变num的值是不允许的
		final int num = 1;
		Converter<Integer, String> converter = (f) -> String.valueOf(f + num);
		String to = converter.convert(2);
		assertTrue("3".equals(to));
	}
	
	/**
	 * 访问局部变量
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		//num在编译的时候被隐式地当做final变量来处理
		int num = 1;
		Converter<Integer, String> converter = (f) -> String.valueOf(f + num);
		String to = converter.convert(2);
		//num = 3;代码不合法，因为num在编译的时候被隐式地当做final变量来处理
		assertTrue("3".equals(to));
	}
	
	/**
	 * 访问成员变量
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Converter<Integer, String> converter = (f) -> {
			this.outerNum = 20;
			return String.valueOf(f + this.outerNum);
		};
		
		String to = converter.convert(5);
		assertTrue("25".equals(to));
	}
	
	/**
	 * 访问静态变量
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		Converter<Integer, String> converter = (f) -> {
			outerStaticNum = 100;
			return String.valueOf(f + outerStaticNum);
		};
		
		String to = converter.convert(10);
		assertTrue("110".equals(to));
	}
}
