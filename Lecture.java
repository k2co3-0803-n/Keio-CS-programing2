
import java.util.List;

public class Lecture {
	private String lectureID;
	private String lectureName;
	private String classRoom;
	private String dayOfWeek; // 曜日
	private String period; // 時限
	private String professorInCharge;

	// コンストラクタ、ゲッター、セッターなどのメソッドを定義しよう
	// Definition of constructor
	public Lecture(String lectureID, String lectureName, String classRoom, String dayOfWeek,String period, String professorInCharge) {
		this.lectureID = lectureID;
		this.lectureName = lectureName;
		this.classRoom = classRoom;
		this.dayOfWeek = dayOfWeek;
		this.period = period;
		this.professorInCharge = professorInCharge;
	}
	public String getLectureID() {
		return this.lectureID;
	}
	public String getLectureName() {
		return this.lectureName;
	}
	public String getClassRoom() {
		return this.classRoom;
	}

	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	public String getPeriod() {
		return this.period;
	}

	public String getProfessorInCharge() {
		return this.professorInCharge;
	}
}
