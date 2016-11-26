package com.pnt.Java8Project;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * JDK 1.8 API中包含了很多内置的函数式接口。
 * 有些是在以前版本的Java中大家耳熟能详的，例如Comparator接口，或者Runnable接口。
 * 对这些现成的接口进行实现，可以通过@FunctionalInterface 标注来启用Lambda功能支持。
 *
 * 此外，Java 8 API 还提供了很多新的函数式接口，来降低程序员的工作负担。
 * 有些新的接口已经在Google Guava库中很有名了。
 * 如果你对这些库很熟的话，你甚至闭上眼睛都能够想到，这些接口在类库的实现过程中起了多么大的作用。
 *
 * @author hlzhu
 * @date 2016年11月23日
 */
public class BuiltInFunctionalInterfaceTest {

	/**
	 * Predicate是一个布尔类型的函数，该函数只有一个输入参数。
	 * Predicate接口包含了多种默认方法，用于处理复杂的逻辑动词（and, or，negate）
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Predicate<String> predicate = (s) -> s.length() > 0;

		System.out.println(predicate.test("hlzhu"));//true
		System.out.println(predicate.negate().test("hlzhu"));//false

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		System.out.println(nonNull.test(true));//true
		System.out.println(isNull.test(null));//true

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();

		System.out.println(isEmpty.test("hlzhu"));//false
		System.out.println(isNotEmpty.test("hlzhu"));//true
	}

	/**
	 * Function接口接收一个参数，并返回单一的结果。
	 * 认方法可以将多个函数串在一起（compse, andThen）
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		Function<String, Integer> toInteger = Integer::valueOf;
		System.out.println(toInteger.apply("123"));

		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		System.out.println(backToString.apply("123"));
	}

	/**
	 * Supplier接口产生一个给定类型的结果。
	 * 与Function不同的是，Supplier没有输入参数。
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		Supplier<Person> supplier = Person::new;
		System.out.println(supplier.get());
	}

	/**
	 * Consumer代表了在一个输入参数上需要进行的操作。
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		Consumer<Person> consumer = (p) -> System.out.println("helo " + p.firstName);
		consumer.accept(new Person("hlzhu", "pig"));
	}

	/**
	 * Comparator接口在早期的Java版本中非常著名。
	 * Java 8 为这个接口添加了不同的默认方法。
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		Person p1 = new Person("hlzhu", "pig");
		Person p2 = new Person("ghy", "rabbit");
		System.out.println(comparator.compare(p1, p2));//>0
		System.out.println(comparator.reversed().compare(p1, p2));//<0
	}

	/**
	 * Optional不是一个函数式接口，而是一个精巧的工具接口，用来防止NullPointerEception产生。
	 * 这个概念在下一节会显得很重要，所以我们在这里快速地浏览一下Optional的工作原理。
	 * Optional是一个简单的值容器，这个值可以是null，也可以是non-null。
	 * 考虑到一个方法可能会返回一个non-null的值，也可能返回一个空值。
	 * 为了不直接返回null，我们在Java 8中就返回一个Optional。
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		Optional<String> optional = Optional.of("hlzhu");

		System.out.println(optional.isPresent());//true
		System.out.println(optional.get());//hlzhu
		System.out.println(optional.orElse("orElse"));//hlzhu
		optional.ifPresent((p) -> System.out.println(p));//hlzhu
	}
}
