package com.pnt.Java8Project;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * Map
 * 正如前面已经提到的那样，map是不支持流操作的。
 * 而更新后的map现在则支持多种实用的新方法，来完成常规的任务。
 *
 * @author hlzhu
 * @date 2016年11月23日
 */
public class MapTest {

	private Map<Integer, String> map;

	@Before
	public void init() throws Exception {
		map = new HashMap<Integer, String>();

		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}
	}

	/**
	 * 代码风格是完全自解释的：putIfAbsent避免我们将null写入；forEach接受一个消费者对象，
	 * 从而将操作实施到每一个map中的值上。
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		map.forEach((key, val) -> System.out.println(val));
	}

	/**
	 * 如何使用函数来计算map的编码
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		map.computeIfPresent(3, (num, val) -> val + num);
		System.out.println(map.get(3));

		map.computeIfPresent(9, (num, val) -> null);
		System.out.println(map.containsKey(9));

		map.computeIfAbsent(23, (num) -> "val" + num);
		System.out.println(map.containsKey(23));

		map.computeIfAbsent(3, (num) -> "bam");
		System.out.println(map.get(3));
	}

	/**
	 * 当给定一个key值时，如何把一个实例从对应的key中移除
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		map.remove(3, "val33");
		System.out.println(map.get(3));//val3

		map.remove(3, "val3");
		System.out.println(map.get(3));//null

		System.out.println(map.getOrDefault(1, "not found"));//val1
		System.out.println(map.getOrDefault(42, "not found"));//not found
	}

	/**
	 * 将map中的实例合并
	 * 合并操作先看map中是否没有特定的key/value存在，
	 * 如果是，则把key/value存入map，
	 * 否则merging函数就会被调用，对现有的数值进行修改。
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		map.merge(9, "val9", (oldVal, newVal) -> oldVal.concat(newVal));
		System.out.println(map.get(9));//val9val9

		map.merge(9, "#", (oldVal, newVal) -> oldVal.concat(newVal));
		System.out.println(map.get(9));//val9val9#
	}
}
