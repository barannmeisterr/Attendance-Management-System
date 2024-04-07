import java.time.LocalDate;
public class TimeSlotGenerator {
/* 
Title:TimeSlotGenerator class
Author:Arda Baran
Description:
 the TimeSlotGenerator class serves as a utility for generating time slots within a given date range, 
 which can be useful in various applications such as scheduling systems, calendar applications, and time management tools.
The class provides methods to access and modify the start date and end date, allowing for flexibility in defining 
the time range for generating time slots. 	
*/	
	public LocalDate startDate;
    public LocalDate endDate;
    public TimeSlotGenerator(LocalDate startDate, LocalDate endDate) {
//The constructor initializes the start date and end date with the provided values     
    	this.startDate = startDate;
        this.endDate = endDate;
    }
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}