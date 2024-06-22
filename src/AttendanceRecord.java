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
    String seminarName,location,time,speaker;
    public AttendanceRecord(int week,String date, String day, String status, int duration) {
       // Constructor for compulsory courses
	this.week=week;
    	this.date = date;
        this.day = day;
        this.status = status;
        this.duration = duration;
    }
    public AttendanceRecord(String date,String seminarName,String day,String speaker,String location,String time) {
    	//Constructor for seminar TEDU 400 course
    	this.date = date;
        this.seminarName=seminarName;
    	this.day = day;
       this.speaker=speaker;
       this.location=location;
       this.time=time;
    
    }
    

	public String getSeminarName() {
		return seminarName;
	}
	public void setSeminarName(String seminarName) {
		this.seminarName = seminarName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
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
