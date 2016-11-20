package com.pnt.Java8Project;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Lambda表达式
 * @author Administrator
 * @date 2016年11月20日
 */
public class LambdaTest {
	
	List<String> nameList;

	@Before
	public void init() {
		nameList = Arrays.asList("hlzhu", "ghy", "tom", "jerry");
	}
	
	/**
	 * 使用java8之前的方式对String列表进行排序
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Collections.sort(nameList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		System.out.println(nameList);
	}
	
	/**
	 * 使用java8提供的lambda表达式对String列表进行排序
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Collections.sort(nameList, (String arg1, String arg2) -> {
			return arg1.compareTo(arg2);
		});
		System.out.println(nameList);
	}
	/**
	 * 使用java8提供的lambda表达式对String列表进行排序
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Collections.sort(nameList, (String arg1, String arg2) -> arg1.compareTo(arg2));
		System.out.println(nameList);
	}
	/**
	 * 使用java8提供的lambda表达式对String列表进行排序
	 * Java编译器能够自动识别参数的类型，所以你就可以省略掉类型不写
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		Collections.sort(nameList, (arg1, arg2) -> arg1.compareTo(arg2));
		System.out.println(nameList);
	}
}
