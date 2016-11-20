package com.pnt.Java8Project;

import java.lang.annotation.Repeatable;

/**
 * 定义一个包装注解，它包括了一个实际注解的数组
 * 只要在前面加上注解名：@Repeatable，Java 8 允许我们对同一类型使用多重注解
 * @author Administrator
 * @date 2016年11月20日
 */
@Repeatable(Hints.class)
public @interface Hint {
	String value();
}
