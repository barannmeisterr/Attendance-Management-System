
public enum Days {
/*
Title: Enum Days class	
Author:Arda Baran
Description:
The Days enum class represents the days of the week on which lectures are held. It consists of five constants, 
each corresponding to a specific day: Monday, Tuesday, Wednesday, Thursday, and Friday.
The purpose of this enum is to provide a convenient way to represent and work with the days of the week in Java programs. 
By using enum constants, it ensures type safety and allows for more readable and maintainable code.
Each enum constant has a corresponding day name associated with it, which can be accessed using the getDayName() method.
 Additionally, the setDayName() method allows for the modification of the day name if necessary.
*/	
	MONDAY("Monday"),TUESDAY("Tuesday"),WEDNESDAY("Wednesday"),THURSDAY("Thursday"),FRIDAY("Friday");
String dayName;
	private Days(String dayName) {
	this.dayName=dayName;
}
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
}