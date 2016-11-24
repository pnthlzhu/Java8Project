package com.pnt.Java8Project;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 像上面所说的，流操作可以是顺序的，也可以是并行的。
 * 顺序操作通过单线程执行，而并行操作则通过多线程执行。
 * 下面的例子就演示了如何使用并行流进行操作来提高运行效率，代码非常简单。
 *
 * @author hlzhu
 * @date 2016年11月23日
 */
public class ParallelStreamsTest {

	private List<String> values;

	/**
	 * 顺序排序
	 *
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
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
	public void test2() throws Exception {
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
