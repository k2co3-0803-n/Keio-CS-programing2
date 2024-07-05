import java.util.List;

public class Student {
	String studentID;
	String name;
	List<Lecture> enrolledLectures;

	// コンストラクタ、ゲッター、セッターなどのメソッドは省略
	String getStudentID() {
		return studentID;
	}
	String getName() {
		return name;
	}
	List<Lecture> getEnrolledLectures() {
		return enrolledLectures;
	}
}
