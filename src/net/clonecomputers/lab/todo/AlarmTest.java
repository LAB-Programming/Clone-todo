package net.clonecomputers.lab.todo;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.junit.Test;

public class AlarmTest {
	
	private final Date testDate = new GregorianCalendar(2013, 11, 4, 12, 14).getTime();
	private final Date testLeapYearDate = new GregorianCalendar(2016, 1, 29, 12, 14).getTime();
	private final Scanner in = new Scanner(System.in);

	/*@Test
	public void test() {
		assertTrue(false);
	}*/
	
	@Test
	public void testCalendarClass() {
		Calendar cal1 = new GregorianCalendar(2000, 2, 2);
		Calendar cal2 = new GregorianCalendar(2000, 2, 2, 0, 0);
		Calendar cal3 = new GregorianCalendar(2000, 2, 2, 0, 0, 0);
		System.out.println("Cal1: " + cal1.getTime());
		System.out.println("Cal2: " + cal2.getTime());
		System.out.println("Cal3: " + cal3.getTime());
		System.out.println("Current Time: " + new GregorianCalendar());
		System.out.println("Actual Maximum Day: " + new GregorianCalendar(2012, 1, 1).getActualMaximum(Calendar.DATE));
		assertEquals(cal2, cal1);
		assertEquals(cal3, cal2);
	}
	
	@Test
	public void testDateEquals() {
		Date alarmDate = new ScheduledAlarm(2013, 11, 4, 12, 14).getSoonestDate();
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
		Date alarmDate = new ScheduledAlarm(ScheduledAlarm.WILDCARD, 11, 4, 12, 14).getSoonestDate();
		printTestInfo("testWildcardYearEqual", alarmDate);
		assertTrue(testDate.equals(alarmDate));
	}
	
	@Test
	public void testWildcardYearEqualWithLeapYear() {
		Date alarmDate = new ScheduledAlarm(ScheduledAlarm.WILDCARD, 1, 29, 12, 14).getSoonestDate();
		printTestInfo("testWildcardYearEqualWithLeapYear", alarmDate, testLeapYearDate);
		assertTrue(testLeapYearDate.equals(alarmDate));
	}
	
	//@Test
	//Broken
	public void testWildcardMonthEqual() {
		Date alarmDate = new ScheduledAlarm(2013, ScheduledAlarm.WILDCARD, 4, 12, 14).getSoonestDate();
		printTestInfo("testWildcardMonthEqual", alarmDate);
		assertTrue(testDate.equals(alarmDate));
	}
	
	@Test
	public void testFromUserInput() {
		System.out.println("Hi World!");
		String response = "";
		int aYear = 0;
		int aMonth = 0;
		int aDay = 0;
		int aHour = 0;
		int aMinute = 0;
		int eYear = 0;
		int eMonth = 0;
		int eDay = 0;
		int eHour = 0;
		int eMinute = 0;
        while(!response.equalsIgnoreCase("y")) {
        	System.out.println("AlarmTime:");
        	System.out.println("Input Year:");
        	aYear = in.nextInt();
        	System.out.println("Input Month (0-11):");
        	aMonth = in.nextInt();
        	System.out.println("Input Day (1-?):");
        	aDay = in.nextInt();
        	System.out.println("Input Hour (0-23):");
        	aHour = in.nextInt();
        	System.out.println("Input Minute (0-59):");
        	aMinute = in.nextInt();
        	System.out.println();
        	
        	System.out.println("ExpectedNextTime:");
        	System.out.println("Input Year:");
        	eYear = in.nextInt();
        	System.out.println("Input Month (0-11):");
        	eMonth = in.nextInt();
        	System.out.println("Input Day (1-?):");
        	eDay = in.nextInt();
        	System.out.println("Input Hour (0-23):");
        	eHour = in.nextInt();
        	System.out.println("Input Minute (0-59):");
        	eMinute = in.nextInt();
        	
        	ScheduledAlarm alarm = new ScheduledAlarm(aYear, aMonth, aDay, aHour, aMinute);
        	long startTime = System.currentTimeMillis();
        	Date alarmTime = alarm.getSoonestDate();
        	long elapsedTime = System.currentTimeMillis() - startTime;
        	Date expectedNextTime = new GregorianCalendar(eYear, eMonth, eDay, eHour, eMinute, 0).getTime();
        	
        	printTestInfo("testFromUserInput, time: " + elapsedTime, alarmTime, expectedNextTime);
        	
        	assertTrue(expectedNextTime.equals(alarmTime));
        	
    		System.out.println("Quit? (y)");
    		response = in.nextLine();
        }
	}
	
	private void printTestInfo(String testName, Date alarmDate) {
		System.out.println("-------------------------------------------");
		System.out.println("Test: " + testName);
		System.out.println("    Test Date:  " + testDate);
		System.out.println("    Alarm Date: " + alarmDate);
		System.out.println("    Equal?:     " + testDate.equals(alarmDate));
		System.out.println("-------------------------------------------");
	}
	
	private void printTestInfo(String testName, Date alarmDate, Date theTestDate) {
		System.out.println("-------------------------------------------");
		System.out.println("Test: " + testName);
		System.out.println("    Test Date:  " + theTestDate);
		System.out.println("    Alarm Date: " + alarmDate);
		System.out.println("    Equal?:     " + theTestDate.equals(alarmDate));
		System.out.println("-------------------------------------------");
	}

}