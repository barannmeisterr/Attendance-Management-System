public class AttendanceRecord {
/*
Title:AttendanceRecord class
Author:Arda Baran
Description: AttendenceRecord class helps to write attendance records to the txt file. 	
*/	
	String date;
    String day;
    String status;
    int duration;
    int week;
    public AttendanceRecord(int week,String date, String day, String status, int duration) {
        this.week=week;
    	this.date = date;
        this.day = day;
        this.status = status;
        this.duration = duration;
    }
    public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}