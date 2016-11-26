package com.pnt.Java8Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

/**
 * java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。
 * Stream操作可以是中间操作，也可以是完结操作。
 * 完结操作会返回一个某种类型的值，而中间操作会返回流对象本身，
 * 并且你可以通过多次调用同一个流操作方法来将操作结果串起来（就像StringBuffer的append方法一样————译者注）。
 * Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。
 * Stream操作往往可以通过顺序或者并行两种方式来执行。
 *
 * @author hlzhu
 * @date 2016年11月23日
 */
public class StreamsTest {

	private List<String> strList;

	private List<String> values;

	/**
	 * 我们先了解一下序列流。首先，我们通过string类型的list的形式创建示例数据
	 * Java 8中的Collections类的功能已经有所增强，
	 * 你可以之直接通过调用Collections.stream()或者Collection.parallelStream()方法来创建一个流对象。
	 * 下面的章节会解释这个最常用的操作。
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("serial")
	@Before
	public void initStrList() throws Exception {
		strList = new ArrayList<String>(){
			{
				add("pnt");
				add("ghy");
				add("psh");
				add("hlzhu");
			}
		};
	}

	@Before
	public void initVlues() throws Exception {
		int max = 1000000;
		values = new ArrayList<String>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
	}

	/**
	 * Filter接受一个predicate接口类型的变量，并将所有流对象中的元素进行过滤。
	 * 该操作是一个中间操作，因此它允许我们在返回结果的基础上再进行其他的流操作（forEach）。
	 * ForEach接受一个function接口类型的变量，用来执行对每一个元素的操作。
	 * ForEach是一个中止操作。它不返回流，所以我们不能再调用其他的流操作。
	 *
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		strList
			.stream()
			.filter((s) -> s.startsWith("p"))
			.forEach(System.out::println);
	}

	/**
	 * Sorted是一个中间操作，能够返回一个排过序的流对象的视图。
	 * 流对象中的元素会默认按照自然顺序进行排序，除非你自己指定一个Comparator接口来改变排序规则。
	 * 一定要记住，sorted只是创建一个流对象排序的视图，而不会改变原来集合中元素的顺序。
	 * 原来string集合中的元素顺序是没有改变的。
	 *
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		strList
			.stream()
			.sorted()
			.filter((s) -> s.contains("h"))
			.forEach(System.out::println);

		System.out.println(strList);
	}

	/**
	 * map是一个对于流对象的中间操作，通过给定的方法，
	 * 它能够把流对象中的每一个元素对应到另外一个对象上。
	 * 下面的例子就演示了如何把每个string都转换成大写的string.
	 * 不但如此，你还可以把每一种对象映射成为其他类型。
	 * 对于带泛型结果的流对象，具体的类型还要由传递给map的泛型方法来决定。
	 *
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		strList
			.stream()
			.map(String::toUpperCase)
			.sorted((arg1, arg2) -> arg1.compareTo(arg2))
			.forEach(System.out::println);
	}

	/**
	 * 匹配操作有多种不同的类型，都是用来判断某一种规则是否与流对象相互吻合的。
	 * 所有的匹配操作都是终结操作，只返回一个boolean类型的结果。
	 *
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		boolean anyStartsWithP = strList
									.stream()
									.anyMatch((p) -> p.startsWith("p"));
		System.out.println(anyStartsWithP);

		boolean allStartsWithP = strList
									.stream()
									.allMatch((p) -> p.startsWith("p"));
		System.out.println(allStartsWithP);

		boolean noneStartsWithP = strList
									.stream()
									.noneMatch((p) -> p.startsWith("p"));
		System.out.println(noneStartsWithP);
	}

	/**
	 * Count是一个终结操作。
	 * 它的作用是返回一个数值，用来标识当前流对象中包含的元素数量。
	 *
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		long startsWithP = strList
								.stream()
								.filter((p) -> p.startsWith("p"))
								.count();
		System.out.println(startsWithP);
	}

	/**
	 * 该操作是一个终结操作。
	 * 它能够通过某一个方法，对元素进行削减操作。
	 * 该操作的结果会放在一个Optional变量里返回。
	 *
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		Optional<String> reduced = strList
										.stream()
										.sorted()
										.reduce((arg1, arg2) -> arg1.concat("#").concat(arg2));
		reduced.ifPresent(System.out::println);
	}

	/**
	 * 像上面所说的，流操作可以是顺序的，也可以是并行的。
	 * 顺序操作通过单线程执行，而并行操作则通过多线程执行。
	 * 下面的例子就演示了如何使用并行流进行操作来提高运行效率，代码非常简单。
	 *
	 * 顺序排序
	 *
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception {
		long t0 = System.nanoTime();

		long count = values
						.stream()
						.sorted()
						.count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

		System.out.println(String.format("sequential sort took: %d ms", millis));
	}

	/**
	 * 并行排序
	 *
	 * 如你所见，所有的代码段几乎都相同，唯一的不同就是把stream()改成了parallelStream(), 结果并行排序快了50%。
	 *
	 * @throws Exception
	 */
	@Test
	public void test8() throws Exception {
		long t0 = System.nanoTime();

		long count = values
				.parallelStream()
				.sorted()
				.count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

		System.out.println(String.format("parallel sort took: %d ms", millis));
	}
}
