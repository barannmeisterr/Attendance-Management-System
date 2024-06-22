import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Attendance {
/*Title:Attendance class
Author: Arda Baran
Description:In this class , the record of attendance is introduced.attendance record begins at the start date of the
academic term calendar.attendance record ends at the end date of the academic term calendar.  
every attendance records has weeks ,date of the lecture ,day of the lecture,
status such as presence ,absence,cancellation ,online session or national holiday and duration	
attendance rate indicates the rate of present times.
	
	

*/	
	TimeSlotGenerator timeSlots;
	String attendanceStatus;
int courseHour,numOfPresent,numOfAbsent,numOfCancelled,numOfHoliday,numOfTotalSeasion,numOfOnline,weekID;
LocalDate lecturesBeginsAt,lecturesEndsAt;
List<Days> lectureDays;
String startDate,endDate;
double attendanceRate;
List<AttendanceRecord> attendanceRecords;
List<AttendanceRecord>tedu400;
public Attendance(String startDate,String endDate,ArrayList<Days> lectureDays) {
	this.startDate=startDate;
	this.endDate=endDate;
	String[] sd = startDate.split("-");
	String [] ed =endDate.split("-");
	int dayStart=Integer.parseInt(sd[0]);
	int monthStart=Integer.parseInt(sd[1]);
	int yearStart=Integer.parseInt(sd[2]);	
	int dayEnd=Integer.parseInt(ed[0]);
	int monthEnd=Integer.parseInt(ed[1]);
	int yearEnd=Integer.parseInt(ed[2]);	
	this.lecturesBeginsAt=LocalDate.of(yearStart, monthStart, dayStart);
	this.lecturesEndsAt=LocalDate.of(yearEnd, monthEnd, dayEnd);
	this.attendanceStatus = "";
	this.courseHour = 0;
	this.numOfPresent = 0;
	this.numOfAbsent = 0;
	this.numOfCancelled = 0;
	this.numOfHoliday = 0;
	this.numOfTotalSeasion = 0;
	this.weekID = 0;
	this.numOfOnline=0;
	if (lectureDays == null) {
        this.lectureDays = new ArrayList<>();
    }
	else {
	this.lectureDays = lectureDays;
	}
this.attendanceRate=0.0;
attendanceRecords = new ArrayList<>();
tedu400=new ArrayList<>();
}

public void addAttendanceRecord(AttendanceRecord record) {
    attendanceRecords.add(record);
}
public void addAttendanceRecordTedu400(AttendanceRecord t400) {
	tedu400.add(t400);
}

public int getNumOfOnline() {
	return numOfOnline;
}
public void setNumOfOnline(int numOfOnline) {
	this.numOfOnline = numOfOnline;
}
public int getTotalWeekOfSchedule() {
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    int weekNumber = 1;
    LocalDate currentWeekStart = getLecturesBeginsAt();
    while (currentWeekStart.isBefore(lecturesEndsAt) || currentWeekStart.isEqual(lecturesEndsAt)) {
        LocalDate currentWeekEnd = currentWeekStart.plusDays(6);     
        currentWeekStart = currentWeekStart.plusWeeks(1); // Move to the next week
        weekNumber++;
    }
    return weekNumber - 1; // Return the total number of weeks
}
public double getAttendanceRate() {
	return attendanceRate;
}
public void setAttendanceRate(double attendanceRate) {
	this.attendanceRate = attendanceRate;
}
public TimeSlotGenerator getTimeSlots() {
	return timeSlots;
}
public void setTimeSlots(TimeSlotGenerator timeSlots) {
	this.timeSlots = timeSlots;
}
public String getAttendanceStatus() {
	return attendanceStatus;
}
public void setAttendanceStatus(String attendanceStatus) {
	this.attendanceStatus = attendanceStatus;
}
public int getCourseHour() {
	return courseHour;
}
public void setCourseHour(int courseHour) {
	this.courseHour = courseHour;
}
public int getNumOfPresent() {
	return numOfPresent;
}
public void setNumOfPresent(int numOfPresent) {
	this.numOfPresent = numOfPresent;
}
public int getNumOfAbsent() {
	return numOfAbsent;
}
public void setNumOfAbsent(int numOfAbsent) {
	this.numOfAbsent = numOfAbsent;
}
public int getNumOfCancelled() {
	return numOfCancelled;
}
public void setNumOfCancelled(int numOfCancelled) {
	this.numOfCancelled = numOfCancelled;
}
public int getNumOfHoliday() {
	return numOfHoliday;
}
public void setNumOfHoliday(int numOfHoliday) {
	this.numOfHoliday = numOfHoliday;
}
public int getNumOfTotalSeasion() {
	return numOfTotalSeasion;
}
public void setNumOfTotalSeasion(int numOfTotalSeasion) {
	this.numOfTotalSeasion = numOfTotalSeasion;
}
public int getWeekID() {
	return weekID;
}
public void setWeekID(int weekID) {
	this.weekID = weekID;
}
public LocalDate getLecturesBeginsAt() {
	return lecturesBeginsAt;
}
public void setLecturesBeginsAt(LocalDate lecturesBeginsAt) {
	this.lecturesBeginsAt = lecturesBeginsAt;
}
public LocalDate getLecturesEndsAt() {
	return lecturesEndsAt;
}
public void setLecturesEndsAt(LocalDate lecturesEndsAt) {
	this.lecturesEndsAt = lecturesEndsAt;
}
public List<Days> getLectureDays() {
	return lectureDays;
}
public void setLectureDays(List<Days> lectureDays) {
	this.lectureDays = lectureDays;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public double attendanceRate(int p,int ch) {
double pr =(double)getNumOfPresent();
double cho =(double)getNumOfTotalSeasion();	
return (pr==0.0&&cho==0.0) ? 0.0 : (pr/cho) *100.0;
}
}
