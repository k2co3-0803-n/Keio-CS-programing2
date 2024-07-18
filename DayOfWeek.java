public class DayOfWeek {
	private String dayOfWeekID;
	private String dayOfWeekName;

	// constructor
	public DayOfWeek(String dayOfWeekID, String dayOfWeekName) {
		this.dayOfWeekID = dayOfWeekID;
		this.dayOfWeekName = dayOfWeekName;
	}

	@Override
	public String toString() {
		return this.dayOfWeekName;
	}

	// getter
	public String getDayOfWeekID() {
		return this.dayOfWeekID;
	}

	public String getDayOfWeekName() {
		return this.dayOfWeekName;
	}

	static String convertIntToYoubi(String dayOfWeek) {
		switch (dayOfWeek) {
			case "Monday":
				return "1";
			case "Tuesday":
				return "2";
			case "Wednesday":
				return "3";
			case "Thursday":
				return "4";
			case "Friday":
				return "5";
			case "Saturday":
				return "6";
			case "Sunday":
				return "7";
			default:
				return "Error";
		}
	}
}