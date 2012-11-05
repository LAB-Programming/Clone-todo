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
		assertTrue(testDate.equals(new ScheduledAlarm(2013, 8, 4, 12, 14).getSoonestDate()));
	}
	
	@Test
	public void testDateNotEqual() {
		assertFalse(testDate.equals(new ScheduledAlarm(2013, 1, 1, 0, 0).getSoonestDate()));
	}
	
	@Test
	public void testWildcardYearEqual() {
		assertTrue(testDate.equals(new ScheduledAlarm(ScheduledAlarm.WILDCARD, 8, 4, 12, 14).getSoonestDate()));
	}

}
