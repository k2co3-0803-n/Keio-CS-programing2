import java.util.List;

public class Lecture {
	String lectureID;
	String lectureName;
	String classroom;
	String lectureDayAndTime;
	String professorInCharge;
	List<Student> enrolledStudents;

	// コンストラクタ、ゲッター、セッターなどのメソッドは省略
	String getLectureID() {
		return lectureID;
	}
	String getLectureName() {
		return lectureName;
	}
	String getClassRoom() {
		return classroom;
	}
	String getLectureDayAndTime() {
		return lectureDayAndTime;
	}
	String getProfessorInCharge() {
		return professorInCharge;
	}
	List<Student> getEnrolledStudents() {
		return enrolledStudents;
	}
}
