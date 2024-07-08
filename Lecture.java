
import java.util.List;

public class Lecture {
	private String lectureID;
	private String lectureName;
	private String classRoom;
	private String lectureDayAndTime;
	private String professorInCharge;
	private List<Student> enrolledStudents;

	// コンストラクタ、ゲッター、セッターなどのメソッドを定義しよう
	// Definition of constructor
	public Lecture(String lectureID, String lectureName, String classRoom, String lectureDayAndTime, String professorInCharge, List<Student> enrollStudents) {
		this.lectureID = lectureID;
		this.lectureName = lectureName;
		this.classRoom = classRoom;
		this.lectureDayAndTime = lectureDayAndTime;
		this.professorInCharge = professorInCharge;
		this.enrolledStudents = enrollStudents;
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
	public String getLectureDayAndTime() {
		return this.lectureDayAndTime;
	}
	public String getProfessorInCharge() {
		return this.professorInCharge;
	}
	public List<Student> getEnrolledStudents() {
		return this.enrolledStudents;
	}
}
