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
			case "月曜日":
				return "1";
			case "火曜日":
				return "2";
			case "水曜日":
				return "3";
			case "木曜日":
				return "4";
			case "金曜日":
				return "5";
			case "土曜日":
				return "6";
			case "日曜日":
				return "7";
			default:
				return "エラー";
		}

	}
}