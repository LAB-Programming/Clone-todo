package net.clonecomputers.lab.todo;

import static java.util.Calendar.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ScheduledAlarm {

	public static final int NO_VALUE = Integer.MIN_VALUE;
	public static final int WILDCARD = 60;
	
	private Calendar NOW = new GregorianCalendar();
	
	private Date soonestDate = null;
	
	private final int ye;
	private final int mo;
	private final int da;
	private final int ho;
	private final int mi;
	
	private Boolean isValid = null;
	
	/**
	 * Initializes a blank scheduled alarm (all the fields are set to NO_VALUE)
	 */
	public ScheduledAlarm() {
		this(NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE,NO_VALUE);
	}
	
	public ScheduledAlarm(int year, int month, int day, int hour, int minute) {
		ye = year;
		mo = month;
		da = day;
		ho = hour;
		mi = minute;
	}
	
	public Date getSoonestDate() {
		NOW.setTime(new Date());
		if(isValid != null && !isValid) return null;
		if(soonestDate == null || NOW.getTime().after(soonestDate)) {
			soonestDate = getSoonestDateAfter(NOW);
			isValid = soonestDate != null;
		}
		return soonestDate;
	}
	
	private Date getSoonestDateAfter(Calendar curTime) {
		if(ye == NO_VALUE || mo == NO_VALUE || da == NO_VALUE || ho == NO_VALUE || mi == NO_VALUE) return null;
		Calendar alarmTime = new GregorianCalendar(1,1,1,0,0,0);
		alarmTime.set(ye,mo,da,ho,mi);
		if(ye == WILDCARD) alarmTime.set(YEAR, curTime.get(YEAR));
		if(mo == WILDCARD) alarmTime.set(MONTH, curTime.get(MONTH));
		if(da == WILDCARD) alarmTime.set(DATE, curTime.get(MONTH));
		if(ho == WILDCARD) alarmTime.set(HOUR_OF_DAY, curTime.get(HOUR_OF_DAY));
		if(mi == WILDCARD) {
			alarmTime.set(MINUTE, curTime.get(MINUTE));
			alarmTime.add(MINUTE, 1);
		}
		if(ho == WILDCARD) {
			while(!curTime.getTime().before(alarmTime.getTime())) {
				alarmTime.add(HOUR_OF_DAY, 1);
			}
		}
		if(da == WILDCARD) {
			while(!curTime.getTime().before(alarmTime.getTime())) {
				alarmTime.add(DATE, 1);
			}
		}
		if(mo == WILDCARD) {
			while(!curTime.getTime().before(alarmTime.getTime()) || (da != WILDCARD && da > alarmTime.getActualMaximum(DATE))) {
				alarmTime.add(MONTH, 1);
			}
			if(da != WILDCARD) alarmTime.set(DATE, da);
		}
		if(ye != WILDCARD) {
			if(!curTime.getTime().before(alarmTime.getTime())) return null;
		} else {
			while(!curTime.getTime().before(alarmTime.getTime()) || (da != WILDCARD && da > alarmTime.getActualMaximum(DATE))) {
				alarmTime.add(YEAR, 1);
			}
			if(da != WILDCARD) alarmTime.set(DATE, da);
		}
		return alarmTime.getTime();
	}
	
	@SuppressWarnings("unused")
	private Date[] getSoonestDates(int number) {
		NOW.setTime(new Date());
		return getSoonestDatesAfter(NOW, number);
	}
	
	private Date[] getSoonestDatesAfter(Calendar curTime, int number) {
		if(number < 1) throw new IllegalArgumentException("for getSoonestDates(), number must at least be one which it is not");
		Date[] dates = new Date[number];
		for(int i = 0; i < number; i++) {
			dates[i] = getSoonestDateAfter(curTime);
			curTime.setTime(dates[i]);
		}
		return dates;
	}

	public int getYear() {
		return ye;
	}

	public int getMonth() {
		return mo;
	}

	public int getDay() {
		return da;
	}

	public int getHour() {
		return ho;
	}

	public int getMinute() {
		return mi;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ScheduledAlarm)) return false;
		ScheduledAlarm a = (ScheduledAlarm) o;
		return ye == a.ye && mo == a.mo && da == a.da && ho == a.ho && mi == a.mi;
	}
	
	public boolean isValid() {
		if(isValid == null) {
			isValid = (getSoonestDate() != null);
		}
		return isValid;
	}

	@Override
	/**
	 * This formats the ScheduledAlarm into the form MM/dd/yyyy hh:mm
	 * Wildcards are displayed as "**" (or "****" for year)
	 * @return A string version of the SheduledAlarm
	 */
	public String toString() {
		return new String(mo + "/" + da + "/" + ye + " " + (ho < 10 ? "0" : "") + ho + ":" + (mi < 10 ? "0" : "") + mi).replaceAll("([^0-9])?60([^0-9])?", "$1**$2").replaceAll("/\\*\\* ", "/**** ");
	}

}
