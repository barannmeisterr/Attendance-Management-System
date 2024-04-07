import java.util.Scanner;
import java.util.ArrayList;
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
public class AttendanceReport {
/*	
Title:AttendanceReport class
Author:Arda Baran	
Description:
The AttendanceReport class serves as a command-line interface for managing attendance records for different courses.

Upon execution, it presents a menu to the user with options to add attendance or generate attendance reports for 
specific courses. The user can also exit the program using the provided option.	
When the user selects the "add" option, they are prompted to input attendance details such as the	
week number, date, day, status (e.g., "H" for present, "A" for absent), and duration.	
This information is then used to add attendance records to the corresponding course
 when the user selects the "report" option, they are prompted to specify the course for which they 
 want to generate an attendance report.
The program then displays the attendance report for the selected course, including details such as the 
course name, current week, total present hours, total absent hours, and total canceled hours.	
Additionally, the program provides an option to add more attendance records for a specific course 
before returning to the main menu, enhancing its functionality and user experience.	
the AttendanceReport class facilitates the efficient management and tracking of attendance records for various courses,
 contributing to effective monitoring of student attendance.

*/	
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
	System.out.println("==============================================================");
	System.out.println("|Write add to add attendance to the specific course          |");
	System.out.println("|Write report to see attendance report of the specific course|");
	System.out.println("|Write exit to terminate the program                         |");
    System.out.println("==============================================================");		
	Scanner sc = new Scanner(System.in);
String command = sc.nextLine();
	System.out.println("");
	 String moreAttendance;
	String courseName;
	Days day;
	int week,duration;
	String date,dayName,status;
        //list of lecture days.there is no lecture on weekend
        ArrayList<Days> lectureDays = new ArrayList<>();
        lectureDays.add(Days.MONDAY);
        lectureDays.add(Days.WEDNESDAY);
        lectureDays.add(Days.FRIDAY);
        lectureDays.add(Days.TUESDAY);
        lectureDays.add(Days.THURSDAY);
        // instances of the attendance for each course       
        Attendance a407 = new Attendance("19-2-2024", "31-5-2024", lectureDays);
        Attendance a423 = new Attendance("19-2-2024", "31-5-2024", lectureDays);
        Attendance c326 = new Attendance("19-2-2024", "31-5-2024", lectureDays);
        //Course instances
        Course ada407 = new Course("ADA 407", true,3);
        ada407.setAttendance(a407);        
        Course ada423 = new Course("ADA 423", true,3);
        ada423.setAttendance(a423);        
        Course cmpe326=new Course("CMPE 326",true,3);
        cmpe326.setAttendance(c326);                
        switch (command) {
    	case "add":
    	System.out.println("Which Course You Want To Add Attendance ?");
    	 courseName=sc.nextLine();
    	System.out.println("");
    	switch(courseName) {   
    	case "ada 407":
    		while (true) {
    		System.out.println("Enter attendance details " );
            System.out.println();
            System.out.println("Enter Week");
             week=sc.nextInt();
            System.out.println("Enter date (e.g., 20-2-2024):");
             date = sc.next();
            System.out.println("Enter day (e.g., TUESDAY):");
             dayName = sc.next();
             day = Days.valueOf(dayName); //Convert input day name to enum 
            System.out.println("Enter status (H/A/C/P):");
            status = sc.next();
            System.out.println("Enter duration:");
             duration = sc.nextInt();           
            ada407.addAttendance(week, date, day, status, duration);
            System.out.println("Do you want to add more attendance for ADA 407? (yes/no)");
           moreAttendance = sc.next().toLowerCase();
            if (!moreAttendance.equalsIgnoreCase("yes")) {
                break;
            }
            sc.nextLine(); 
        }
        break;
    	case "ada 423":
    		 while (true) {
    		System.out.println("Enter attendance details " );
            System.out.println();
            System.out.println("Enter Week");
             week=sc.nextInt();
            System.out.println("Enter date (e.g., 20-2-2024):");
             date = sc.next();
            System.out.println("Enter day (e.g., TUESDAY):");
             dayName = sc.next();
            day = Days.valueOf(dayName); //Convert input day name to enum 
            System.out.println("Enter status (H/A/C/P):");
            status = sc.next();
            System.out.println("Enter duration:");
             duration = sc.nextInt();           
            ada423.addAttendance(week, date, day, status, duration);
            System.out.println("Do you want to add more attendance for ADA 423? (yes/no)");
             moreAttendance = sc.next().toLowerCase();
            if (!moreAttendance.equals("yes")) {
                break;
            }
            sc.nextLine(); // Consume newline
        }
        break;
    	case "cmpe 326":
    		while(true) {
    		System.out.println("Enter attendance details " );
            System.out.println();
            System.out.println("Enter Week");
             week=sc.nextInt();
            System.out.println("Enter date (e.g., 20-2-2024):");
             date = sc.next();
            System.out.println("Enter day (e.g., TUESDAY):");
             dayName = sc.next();
            day = Days.valueOf(dayName); //Convert input day name to enum 
            System.out.println("Enter status (H/A/C/P):");
            status = sc.next();
            System.out.println("Enter duration:");
             duration = sc.nextInt();           
            cmpe326.addAttendance(week, date, day, status, duration);
            System.out.println("Do you want to add more attendance for CMPE 326? (yes/no)");
             moreAttendance = sc.next().toLowerCase();
            if (!moreAttendance.equals("yes")) {
                break;
            }
            sc.nextLine(); 
        }
        break;
    		default:
    			System.out.println("Invalid Course Name");
    		return;	
    	}
    	break;
    	case "report":
    		System.out.println("Which Course You Want To Display Attendance Report ?");
        	 courseName=sc.nextLine();
        	System.out.println("");
        	switch(courseName) {
        	case "ada 407":
        		ada407.readAttendanceFromFile();
        	     System.out.println(ada407);
                 ada407.printRecords();
        	     break;
        	case "ada 423":
        		ada423.readAttendanceFromFile();
        	     System.out.println(ada423);
        	     ada423.printRecords();
        	     break;
        	case "cmpe 326":
        		cmpe326.readAttendanceFromFile();
        	     System.out.println(cmpe326);
        	     cmpe326.printRecords();
        	     break;
        	default:
    			System.out.println("Invalid Course Name");
    		return;	
    	}
        	break;       	
        case "exit":
        System.out.println("Program Terminated...");
        	return;	
        default:
        	System.out.println("Invalid Command");
        }		
	}
}