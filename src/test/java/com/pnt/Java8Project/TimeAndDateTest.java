package com.pnt.Java8Project;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * 时间日期API
 * 
 * Java 8 包含了全新的时间日期API，这些功能都放在了java.time包下。
 * 新的时间日期API是基于Joda-Time库开发的，但是也不尽相同。
 * 下面的例子就涵盖了大多数新的API的重要部分。 
 * 
 * @author Administrator
 * @date 2016年11月20日
 */
public class TimeAndDateTest {

	/**
	 * Clock
	 * 
	 * Clock提供了对当前时间和日期的访问功能。
	 * Clock是对当前时区敏感的，并可用于替代System.currentTimeMillis()方法来获取当前的毫秒时间。
	 * 当前时间线上的时刻可以用Instance类来表示。Instance也能够用于创建原先的java.util.Date对象。 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		System.out.println(millis);
		
		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant);//legacy java.util.Date
		System.out.println(legacyDate);
	}
	
	/**
	 * Timezones
	 * 
	 * 时区类可以用一个ZoneId来表示。
	 * 时区类的对象可以通过静态工厂方法方便地获取。
	 * 时区类还定义了一个偏移量，用来在当前时刻或某时间与目标时区时间之间进行转换。
	 * 
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception {
		//prints all available timezone ids
		System.out.println(ZoneId.getAvailableZoneIds());
		
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());
	}
	
	/**
	 * LocalTime
	 * 
	 * 本地时间类表示一个没有指定时区的时间，例如，10 p.m.或者17：30:15，
	 * 下面的例子会用上面的例子定义的时区创建两个本地时间对象。
	 * 然后我们会比较两个时间，并计算它们之间的小时和分钟的不同。 
	 * 
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception {
		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Brazil/East");
		
		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);
		
		System.out.println(now1);
		System.out.println(now2);
		
		System.out.println(now1.isBefore(now2));
		
		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
		
		System.out.println(hoursBetween);
		System.out.println(minutesBetween);
	}
	
	/**
	 * LocalTime
	 * 
	 * LocalTime是由多个工厂方法组成，其目的是为了简化对时间对象实例的创建和操作，
	 * 包括对时间字符串进行解析的操作。
	 * 
	 * @throws Exception
	 */
	@Test
	public void test4() throws Exception {
		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late);
		
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
															 .withLocale(Locale.GERMAN);
		LocalTime leetTime = LocalTime.parse("13:37", germanFormatter);
		System.out.println(leetTime);
	}
	
	/**
	 * LocalDate
	 * 
	 * 本地时间表示了一个独一无二的时间，例如：2014-03-11。这个时间是不可变的，与LocalTime是同源的。
	 * 下面的例子演示了如何通过加减日，月，年等指标来计算新的日期。记住，每一次操作都会返回一个新的时间对象。
	 * 
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		
		System.out.println(today);
		System.out.println(tomorrow);
		System.out.println(yesterday);
		
		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		
		System.out.println(independenceDay);
		System.out.println(dayOfWeek);
		
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
				 .withLocale(Locale.GERMAN);
		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas);
	}
	
	/**
	 * LocalDateTime
	 * 
	 * LocalDateTime表示的是日期-时间。它将刚才介绍的日期对象和时间对象结合起来，形成了一个对象实例。
	 * LocalDateTime是不可变的，与LocalTime和LocalDate的工作原理相同。我们可以通过调用方法来获取日期时间对象中特定的数据域。
	 * 
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception {
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);
		
		DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek);
		
		Month month = sylvester.getMonth();
		System.out.println(month);
		
		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay);
		
		/*
		 * 如果再加上的时区信息，LocalDateTime能够被转换成Instance实例。
		 * Instance能够被转换成以前的java.util.Date对象。
		 */
		Instant instant = sylvester.atZone(ZoneId.systemDefault())
								   .toInstant();
		Date legacyDate = Date.from(instant);
		System.out.println(legacyDate);
		
		/*
		 * 格式化日期-时间对象就和格式化日期对象或者时间对象一样。
		 * 除了使用预定义的格式以外，我们还可以创建自定义的格式化对象，然后匹配我们自定义的格式。
		 * 不同于java.text.NumberFormat，新的DateTimeFormatter类是不可变的，也是线程安全的。
		 */
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd - HH:mm");
		LocalDateTime parsed = LocalDateTime.parse("20161010 - 07:13", formatter);
		System.out.println(formatter.format(parsed));
	}
}
