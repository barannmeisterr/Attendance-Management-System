import java.text.DecimalFormat;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Course {
/*
Title:Course class	
Author:Arda Baran	
Description:	
The Course class represents an academic course within an educational institution. 
Each course encapsulates essential attributes such as attendance policy (which may be either mandatory or optional),
 course name, and credit hours.
This class serves as a fundamental component of managing attendance of academic programs,
The Course class provides methods for adding attendance records and saves these records to a txt file when a course instance
is created and an attendance record added to the course,
calculates attendance statistics of course such as total taken ,cancelled ,present,absent,break due to national holidays and   
online seasions.
 accessing attendance reports week by week from txt file, and manipulating course information.
this class also provides the calculation of maximum possible attendance such as if you attend 9 hours of 12 hours in 8 week
your attendance rate is %75. According to the academic term calendar , there are 7 remained weeks to lectures end. 
the purpose of this calculation  to find the answer of what would your attendance rate if you attend all classes
 for remained 7 weeks
*/
String courseName;
boolean isAttendanceMandatory;
Attendance attendance;
public Scanner sc;//for read attendance records
int courseCreditHour;
public Course(String courseName,boolean isAttendanceMandatory,int courseCreditHour) {
	this.courseName=courseName;
	this.isAttendanceMandatory=isAttendanceMandatory;
    sc=new Scanner(System.in);
this.courseCreditHour=courseCreditHour;
}
public int getCourseCreditHour() {
	return courseCreditHour;
}
public void setCourseCreditHour(int courseCreditHour) {
	this.courseCreditHour = courseCreditHour;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public boolean isAttendanceMandatory() {
	return isAttendanceMandatory;
}
public void setAttendanceMandatory(boolean isAttendanceMandatory) {
	this.isAttendanceMandatory = isAttendanceMandatory;
}
public Attendance getAttendance() {
	return attendance;//returns attendance class
}
public void setAttendance(Attendance attendance) {
	this.attendance = attendance;
}
public void addAttendance(int weekid,String date ,Days day,String status,int duration) {
	
//Save attendances to txt file
saveAttendance(weekid, date, day, status, duration);
// Add the attendance record to the Attendance object
attendance.addAttendanceRecord(new AttendanceRecord(weekid,date, day.getDayName(), status, duration));
}
public void addAttendance400(String date,String seminarName,Days day,String speaker,String location,String time) {
	saveAttendanceTedu400(date,seminarName,day,speaker,location,time);
attendance.addAttendanceRecordTedu400(new AttendanceRecord(date,seminarName,day.getDayName(),speaker,location,time));
}
public void printTedu400Records()throws IOException{
	String fileName = getCourseName()+"attendance.txt";
	String filePath = "src/resources/"+fileName;
	try {
		System.out.printf("%-15s %-100s %-15s %-30s %-15s %-10s%n", "Date", "Seminar Name", "Day", "Speaker", "Location","Time");		
		Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] record = line.split(",");
            if (record.length >= 5) {              
                String date=record[0].trim();
                String seminarName=record[1].trim();
                String day=record[2].trim();
                String speaker=record[3].trim();
                String location=record[4].trim();
                String time = record[5].trim();

            System.out.printf("%-15s %-100s %-15s %-30s %-15s %-10s%n",date,seminarName,day,speaker,location,time); 
                 
            }
        }
   
		}catch (FileNotFoundException e) {
            e.printStackTrace();
    	}
	
	
	
}

public void printRecords()throws IOException {
//prints attendance records week by week from week 1 to the current week.
	
	String statusPrefix;
	String fileName = getCourseName()+"attendance.txt";
	String filePath = "src/resources/"+fileName;

	System.out.printf("%-10s %-15s %-15s %-30s %-10s%n", "Week", "Date", "Day", "Attendance Status", "Duration");
	try {
        // read datas from file
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] record = line.split(",");
            if (record.length >= 3) {
                int weekid =Integer.parseInt(record[0].trim());                
                String date=record[1].trim();
                String day=record[2].trim();
                String status = record[3].trim();
                int duration = Integer.parseInt(record[4].trim()); 
            switch(status) {
            case "P":
            	statusPrefix="Present";
            break;
            case "A":
            	statusPrefix="Absent";
                break;
            case "C":
            	statusPrefix="Lecture Cancelled";
            break;
            case "H":
            	statusPrefix="National Holiday";
                break;
            case "O":
            	statusPrefix="Online Lecture";
                break;
            default:
            	return;
            }
            System.out.printf("%-10d %-15s %-15s %-30s %-10d%n",weekid,date,day,statusPrefix,duration); 
           
            
            
            }
        }
    
        
        
	}catch (FileNotFoundException e) {
            e.printStackTrace();
    	}
	
		
	}	
		
	
	

	
	
public void readTedu400()throws IOException{
	int w = 0;
	int present=attendance.getNumOfPresent();
	int totalHour=attendance.getNumOfTotalSeasion();
	String fileName = getCourseName()+"attendance.txt";
	String filePath = "src/resources/"+fileName;
	try {
        // read datas from file
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
        	String line = sc.nextLine();
            String[] record = line.split(",");
            if (record.length >= 4) {
        	
        	
        	present=present+1;
        	attendance.setNumOfPresent(present);
        	totalHour=totalHour+1;
        	attendance.setNumOfTotalSeasion(totalHour);
             attendance.setNumOfAbsent(0);
             attendance.setNumOfCancelled(0);
             attendance.setNumOfOnline(0);
             attendance.setNumOfHoliday(0);
             attendance.setWeekID(w);
             //In order to calculate attendance report statistics week of record , attendance status of record and attendance status
      //of record are required.      	
   	
            	double rate =attendance.attendanceRate(present, totalHour);
            attendance.setAttendanceRate(rate);
        w++;    
            }
            }            
}
	catch (FileNotFoundException e) {
        e.printStackTrace();
	}
}
public void readAttendanceFromFile()throws IOException {
/*	
In this method ,attendance records in txt file are proccessed step by step with getters and setters of Attendance class.
if a person is absent or present during lecture week,date and time it has impact on total hour , else it does not has impact
on total hour because if a lecture would be cancelled or not carried out due to national holidays or some private issues 	
and online then it doesnt make sense to count these hours as taken total course hour	
	
*/	
	int present=attendance.getNumOfPresent();
	int absent=attendance.getNumOfAbsent();
	int holiday=attendance.getNumOfHoliday();
	int cancelled=attendance.getNumOfCancelled();
	int totalHour=attendance.getNumOfTotalSeasion();
	int online=attendance.getNumOfOnline();
	String fileName = getCourseName()+"attendance.txt";
	String filePath = "src/resources/"+fileName;
	try {
        // read datas from file
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] record = line.split(",");
            if (record.length >= 3) {
      //In order to calculate attendance report statistics week of record , attendance status of record and attendance status
      //of record are required.      	
            	
            	int weekid =Integer.parseInt(record[0].trim());                
                String status = record[3].trim();
                int duration = Integer.parseInt(record[4].trim());                

            if((status.equalsIgnoreCase("P")||status.equalsIgnoreCase("A")||status.equalsIgnoreCase("C")||status.equalsIgnoreCase("H"))
            		||status.equalsIgnoreCase("O")) {
            		attendance.setAttendanceStatus(status);
            	}else {
            		return;
            	}
            	if(status.equalsIgnoreCase("P")) {
            		present=present+duration;
            	attendance.setNumOfPresent(present);
            	totalHour=totalHour+duration;
            	attendance.setNumOfTotalSeasion(totalHour);
            	}else if(status.equalsIgnoreCase("A")) {
            		absent=absent+duration;
            		attendance.setNumOfAbsent(absent);	
            		totalHour=totalHour+duration;
            		attendance.setNumOfTotalSeasion(totalHour);	
            	}else if (status.equalsIgnoreCase("C")) {
            		cancelled=cancelled+duration;
            		attendance.setNumOfCancelled(cancelled);
            	}else if(status.equalsIgnoreCase("O")) {
            		online=online+duration;
            	    attendance.setNumOfOnline(online);
            	}
            	else {
            		holiday=holiday+duration;
            		attendance.setNumOfHoliday(holiday);
            	}
            	attendance.setWeekID(weekid);
            	double rate =attendance.attendanceRate(present, totalHour);
            attendance.setAttendanceRate(rate);
            }            
}
	}catch (FileNotFoundException e) {
        e.printStackTrace();
	}
}
public void saveAttendanceTedu400(String date,String seminarName,Days day,String speaker,String location,String time) {
	AttendanceRecord record = new AttendanceRecord(date,seminarName, day.getDayName(),speaker,location,time);
	 attendance.addAttendanceRecordTedu400(record);
	 writeAttendanceForTedu400(record);
}
public void saveAttendance(int weekid, String date, Days day, String status, int duration) {
    // Create a new attendance record
    AttendanceRecord record = new AttendanceRecord(weekid,date, day.getDayName(), status, duration);
    // Add the attendance record to the Attendance object
    attendance.addAttendanceRecord(record);
    // Write attendance record to the txt file
    writeAttendanceToFile(record);
}
public void writeAttendanceForTedu400(AttendanceRecord record) {
	String fileName = getCourseName()+"attendance.txt";
    String filePath = Paths.get("src", "resources", fileName).toString();
	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	        // Write attendance record to file
	      
	    	   writer.write(record.getDate()+","+record.getSeminarName()+","+record.getDay()+","+ record.getSpeaker()+","+record.getLocation()+","+record.getTime());
	       writer.newLine();
	       }catch (IOException e) {
	    	   System.err.println("An error occurred while writing to the file: " + e.getMessage());
	       }	
}
public void writeAttendanceToFile(AttendanceRecord record) {
	String fileName = getCourseName()+"attendance.txt";
    String filePath = Paths.get("src", "resources", fileName).toString();
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
		writer.write(record.getWeek()+","+record.getDate()+","+record.getDay()+","+ record.getStatus()+","+record.getDuration());
	        writer.newLine(); // Add a new line after each record
	       }
	        catch (IOException e) {
	        System.err.println("An error occurred while writing to the file: " + e.getMessage());
	       }
	       
	}




public double maxPossibleAttendanceRate() {
/*	
The maxPossibleAttendanceRate method calculates the maximum possible attendance rate that a 
student can achieve for the remainder of the course based on the current attendance records and course schedule.	
	
	
	*/
	double currWeek=(double)getAttendance().getWeekID();
    double currTakenCourseHour=(double)getAttendance().getNumOfTotalSeasion();
    double present=(double)getAttendance().getNumOfPresent();
    double totalWeek=(double)getAttendance().getTotalWeekOfSchedule();
double remainedWeek=totalWeek-currWeek;
double courseCredHour=(double)getCourseCreditHour();
double remainedPossiblePresentStatus=remainedWeek * courseCredHour;

return ((remainedPossiblePresentStatus + present) / (currTakenCourseHour +  remainedPossiblePresentStatus)) * 100.0;  

}
@Override
public String toString() {
//attendance report of the course
	
	String mandatoryOrNotMandatory="";	
	boolean attendanceReq=isAttendanceMandatory();
	if(attendanceReq) {
		mandatoryOrNotMandatory=mandatoryOrNotMandatory+"Attendance is Mandatory";	
	}else {
		mandatoryOrNotMandatory=mandatoryOrNotMandatory+"Attendance is NOT mandatory, but strongly recommended.";
	}
	System.out.print("----------------------------------------------------------------------------------------------------\n");
	if(!getCourseName().equalsIgnoreCase("TEDU 400")) {
	DecimalFormat df = new DecimalFormat("#.##");
	return" Course Name: " + getCourseName() +"\n"+
mandatoryOrNotMandatory+"\n"
+"----------------------------------------------------------------------------------------------------\n"
+"Current Week: "+getAttendance().getWeekID()+"\n"
+"Total Present Hours: "+getAttendance().getNumOfPresent()+"\n"
	+"Total Absent Hours: "+getAttendance().getNumOfAbsent()+"\n"
	+"Total Cancelled Hours: "+getAttendance().getNumOfCancelled()+"\n"
	+"Total Online Hours:"+getAttendance().getNumOfOnline()+"\n"
	+"Total National Holiday Hours: "+getAttendance().getNumOfHoliday()+"\n"
	+"(First Two Weeks Attendance is Not Taken Due To Add-Drop Week)\n"
	+"Total Taken Course Hours: "+getAttendance().getNumOfTotalSeasion()+"\n"
	+"Attendance Rate: %" + df.format(getAttendance().getAttendanceRate()) + "\n"
+"Remained Week To Lectures End: "+ (getAttendance().getTotalWeekOfSchedule()-getAttendance().getWeekID())+"\n"
+"Maximum Possible Attendance: %"+df.format(maxPossibleAttendanceRate())+"\n"
+"----------------------------------------------------------------------------------------------------\n";
	}
	return" Course Name: " + getCourseName() +"\n"+
	"Required Seminar Participation To Pass TEDU 400:"+getAttendance().getTotalWeekOfSchedule()+"\n"
	+"------------------------------------------------------------------------------------------------------\n"	
	+"Total Seminar Participation For TEDU 400: "+getAttendance().getNumOfPresent()+"\n"
	+"Remained Participation To Pass TEDU 400: "+ (getAttendance().getTotalWeekOfSchedule()-getAttendance().getNumOfPresent())+"\n"
	+"------------------------------------------------------------------------------------------------------\n";
	
}

}
