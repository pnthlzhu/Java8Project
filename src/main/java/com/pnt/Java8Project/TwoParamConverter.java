package com.pnt.Java8Project;

/**
 * 函数式接口
 * @author hlzhu
 * @date 2016年12月1日
 */
@FunctionalInterface
public interface TwoParamConverter<A, B, C> {
	C convert(A a, B b);
}
