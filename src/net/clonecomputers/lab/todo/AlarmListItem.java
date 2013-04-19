package net.clonecomputers.lab.todo;

public class AlarmListItem {
	
	private String n;
	private ScheduledAlarm a;
	
	public AlarmListItem(String name, ScheduledAlarm alarm) {
		n = name;
		a = alarm;
	}
	
	public String getName() {
		return n;
	}
	
	public ScheduledAlarm getAlarm() {
		return a;
	}
	
	public void setName(String name) {
		n = name;
	}
	
	public void setAlarm(ScheduledAlarm alarm) {
		a = alarm;
	}
	
	public void setFields(AlarmListItem other) {
		n = other.n;
		a = other.a;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof AlarmListItem)) return false;
		AlarmListItem other = (AlarmListItem) o;
		return a.equals(other.a) && n.equals(other.n);
	}
	
}
