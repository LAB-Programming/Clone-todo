package net.clonecomputers.lab.todo;

import static java.util.Calendar.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ScheduledAlarm {

	public static final int NO_VALUE = Integer.MIN_VALUE; //Used because ScheduledAlarm does all checking on dates
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
		//sanity checks
		if(
				(year == NO_VALUE) || (month == NO_VALUE) || (day == NO_VALUE) || (hour == NO_VALUE) || (minute == NO_VALUE) || // if any of them have no value
				(month != WILDCARD && (month < 0 || month > 11)) || // or if month is out of bounds
				(!isDayWithinMonth(year, month, day)) || // or if day is out of bounds (month specific)
				(hour != WILDCARD && (hour < 0 || hour > 23)) || // or if hour is out of bounds
				(minute != WILDCARD && (minute < 0 || minute > 59)) // or if minute is out of bounds
		) isValid = false; // then this is not a valid alarm
		
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
		Calendar alarmTime = getQuickEarlyDateFrom(curTime);
		if(alarmTime == null) return null;
		if(alarmTime.after(curTime)) return alarmTime.getTime();
		
		if(mi == WILDCARD) {
			while(alarmTime.get(MINUTE) < 59) {
				alarmTime.add(MINUTE, 1);
				if(alarmTime.after(curTime)) return alarmTime.getTime();
			}
			alarmTime.set(MINUTE, 0);
		}
		
		if(ho == WILDCARD) {
			while(alarmTime.get(HOUR_OF_DAY) < 23) {
				alarmTime.add(HOUR_OF_DAY, 1);
				if(alarmTime.after(curTime)) return alarmTime.getTime();
			}
			alarmTime.set(HOUR_OF_DAY, 0);
		}
		
		if(da == WILDCARD) {
			while(alarmTime.get(DATE) < alarmTime.getActualMaximum(DATE)) {
				alarmTime.add(DATE, 1);
				if(alarmTime.after(curTime)) return alarmTime.getTime();
			}
			alarmTime.set(DATE, 1);
		}
		
		if(mo == WILDCARD) {
			int month = alarmTime.get(MONTH);
			while(month < 11) {
				++month;
				if(isDayWithinMonth(alarmTime.get(YEAR), month, alarmTime.get(DATE))) {
					alarmTime.set(MONTH, month);
					if(alarmTime.after(curTime)) return alarmTime.getTime();
				}
			}
			alarmTime.set(MONTH, 0);
		}
		
		if(ye == WILDCARD) {
			int year = alarmTime.get(YEAR);
			while(!alarmTime.after(curTime)) {
				++year;
				if(isDayWithinMonth(year, alarmTime.get(MONTH), alarmTime.get(DATE))) {
					alarmTime.set(YEAR, year);
				}
			}
		}
		
		if(!alarmTime.after(curTime)) return null;
		
		return alarmTime.getTime();
		/*if(mi == WILDCARD) {
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
		}*/
	}
	
	
	private Calendar getQuickEarlyDateFrom(Calendar curTime) {
		
		int year;
		if(ye == WILDCARD) {
			year = curTime.get(YEAR);
			if(da == 29 && mo == 1) {
				while(year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) {
					++year; //I could use math instead of a loop I think
				}
			}
		} else if(ye < curTime.get(YEAR)) {
				return null;
		} else {
			year = ye;
		}
		
		int month = mo;
		if(mo == WILDCARD) {
			if(year == curTime.get(YEAR)) {
				month = curTime.get(MONTH);
				while(!isDayWithinMonth(year, month, da)) { //This should not cause month to get bigger than 11 (since December has 31 days)
					++month;
				}
			} else {
				month = 0;
			}
		}
		
		int day = da;
		if(da == WILDCARD) {
			if(year == curTime.get(YEAR) && month == curTime.get(MONTH)) {
				day = curTime.get(DATE);
			} else {
				day = 1;
			}
		}
		
		int hour = ho;
		if(ho == WILDCARD) {
			if(year == curTime.get(YEAR) && month == curTime.get(MONTH) && day == curTime.get(DATE)) {
				hour = curTime.get(HOUR_OF_DAY);
			} else {
				hour = 0;
			}
		}
		
		int minute = mi;
		if(mi == WILDCARD) {
			if(year == curTime.get(YEAR) && month == curTime.get(MONTH) && day == curTime.get(DATE) && hour == curTime.get(HOUR_OF_DAY)) {
				minute = curTime.get(MINUTE);
			} else {
				minute = 0;
			}
		}
		
		return new GregorianCalendar(year, month, day, hour, minute);
	}
	
	public static boolean isDayWithinMonth(int year, int month, int day) {
		if(month != WILDCARD && (month < 0 || month > 11))
			throw new IllegalArgumentException("Invalid month");
		if(day == WILDCARD) return true;
		if(day < 1 || day > 31) return false;
		switch(month) {
			case 0:
			case 2:
			case 4:
			case 6:
			case 7:
			case 9:
			case 11:
			case WILDCARD:
				return true;
			case 3:
			case 5:
			case 8:
			case 10:
				if(day > 30)
					throw new IllegalArgumentException("for Apr, Jun, Sep, Nov, day cannot be greater than 30");
				return true;
			case 1:
				if(day > 29 || (day > 28 && (year != WILDCARD && (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)))))
					//checking for a defined year whether Feb has 28 or 29 days
					return false;
				return true;
			default:
				throw new IllegalArgumentException("This shouldn't happen: Invalid month");
		}
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
		return new String((mo + 1) + "/" + da + "/" + ye + " " + (ho < 10 ? "0" : "") + ho + ":" + (mi < 10 ? "0" : "") + mi).replaceAll("([^0-9])?60([^0-9])?", "$1**$2").replaceAll("/\\*\\* ", "/**** ");
	}

}
