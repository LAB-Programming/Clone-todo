package net.clonecomputers.lab.todo;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

public class MainTest {
	
	private final Date testDate = new GregorianCalendar(2013, 8, 4, 12, 14).getTime();

	/*@Test
	public void test() {
		assertTrue(false);
	}*/
	
	@Test
	public void testDateEquals() {
		Date alarmDate = new ScheduledAlarm(2013, 8, 4, 12, 14).getSoonestDate();
		printTestInfo("testDateEquals", alarmDate);
		assertTrue(testDate.equals(alarmDate));
	}
	
	@Test
	public void testDateNotEqual() {
		Date alarmDate = new ScheduledAlarm(2013, 1, 1, 0, 0).getSoonestDate();
		printTestInfo("testDateNotEqual", alarmDate);
		assertFalse(testDate.equals(alarmDate));
	}
	
	@Test
	public void testWildcardYearEqual() {
		Date alarmDate = new ScheduledAlarm(ScheduledAlarm.WILDCARD, 8, 4, 12, 14).getSoonestDate();
		printTestInfo("testWildcardYearEqual", alarmDate);
		assertTrue(testDate.equals(alarmDate));
	}
	
	private void printTestInfo(String testName, Date alarmDate) {
		System.out.println("-------------------------------------------");
		System.out.println("Test: " + testName);
		System.out.println("    Test Date:  " + testDate);
		System.out.println("    Alarm Date: " + alarmDate);
		System.out.println("-------------------------------------------");
	}

}
