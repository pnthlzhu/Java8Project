package com.pnt.Java8Project;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FormulaTest {

	/**
	 * formula对象以匿名对象的形式实现了Formula接口。
	 * 代码很啰嗦：用了6行代码才实现了一个简单的计算功能：a*100开平方根。
	 * 我们在下一节会看到，Java 8 还有一种更加优雅的方法，能够实现包含单个函数的对象。 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Formula formula = new Formula() {
			
			@Override
			public double calculate(int a) {
				return sqrt(a);
			}
		};
		
		assertTrue(formula.calculate(100) == 10);
		assertTrue(formula.sqrt(16) == 4);
	}
}
