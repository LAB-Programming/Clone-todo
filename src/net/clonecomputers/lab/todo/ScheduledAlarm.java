package net.clonecomputers.lab.todo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ScheduledAlarm {

	public static final int NO_VALUE = 0;
	public static final int WILDCARD = 60;
	
	private final Calendar NOW = new GregorianCalendar();
	
	private final int ye;
	private final int mo;
	private final int da;
	private final int ho;
	private final int mi;
	
	public ScheduledAlarm(int year, int month, int day, int hour, int minute) {
		ye = year;
		mo = month;
		da = day;
		ho = hour;
		mi = minute;
	}
	
	Date getSoonestDate() {
		Calendar alarmTime = new GregorianCalendar();
		NOW.setTime(new Date());
		alarmTime.set(ye,mo,da,ho,mi);
		if(ye != 60) {
			return NOW.getTime().before(alarmTime.getTime()) ? alarmTime.getTime() : null;
		} else {
			int i = 0;
			do {
				alarmTime.set(Calendar.YEAR, NOW.get(Calendar.YEAR+i++));
			} while(NOW.getTime().before(alarmTime.getTime()));
			return alarmTime.getTime();
		}
	}

}
