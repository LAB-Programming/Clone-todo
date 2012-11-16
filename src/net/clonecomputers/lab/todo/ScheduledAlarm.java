package net.clonecomputers.lab.todo;

import static java.util.Calendar.*;

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
		Calendar alarmTime = new GregorianCalendar(1,1,1,0,0,0);
		NOW.setTime(new Date());
		alarmTime.set(ye,mo,da,ho,mi);
		if(ye == WILDCARD) alarmTime.set(YEAR, NOW.get(YEAR));
		if(mo == WILDCARD) alarmTime.set(MONTH, NOW.get(MONTH));
		if(da == WILDCARD) alarmTime.set(DATE, NOW.get(MONTH));
		if(ho == WILDCARD) alarmTime.set(HOUR_OF_DAY, NOW.get(HOUR_OF_DAY));
		if(mi == WILDCARD) {
			alarmTime.set(MINUTE, NOW.get(MINUTE));
			alarmTime.add(MINUTE, 1);
		}
		if(ho == WILDCARD) {
			while(!NOW.getTime().before(alarmTime.getTime())) {
				alarmTime.add(HOUR_OF_DAY, 1);
			}
		}
		if(da == WILDCARD) {
			while(!NOW.getTime().before(alarmTime.getTime())) {
				alarmTime.add(DATE, 1);
			}
		}
		if(mo == WILDCARD) {
			while(!NOW.getTime().before(alarmTime.getTime()) || da > alarmTime.getActualMaximum(DATE)) {
				alarmTime.add(MONTH, 1);
			}
			alarmTime.set(DATE, da);
		}
		if(ye != WILDCARD) {
			if(!NOW.getTime().before(alarmTime.getTime())) return null;
		} else {
			while(!NOW.getTime().before(alarmTime.getTime()) || da > alarmTime.getActualMaximum(DATE)) {
				alarmTime.add(YEAR, 1);
			}
			alarmTime.set(DATE, da);
		}
		return alarmTime.getTime();
	}
}
