import java.util.List;

public class Student {
	String studentID;
	String studentName;
	List<Lecture> enrolledLectures;

	// コンストラクタ、ゲッター、セッターなどのメソッドを定義しよう
	// Definition of constructor
	// 
	public Student(String studentID, String studentName, List<Lecture> enrolledLectures) {
		this.studentID = studentID;
		this.studentName = studentName;
		this.enrolledLectures = enrolledLectures;
	}
	public String getStudentID() {
		return this.studentID;
	}
	public String getName() {
		return this.studentName;
	}
	public List<Lecture> getEnrolledLectures() {
		return this.enrolledLectures;
	}
}