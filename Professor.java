import java.util.List;

public class Professor {
	String professorID;
	String name;
	List<Lecture> lecturesInCharge;

	// コンストラクタ、ゲッター、セッターなどのメソッドは省略
	String getProfessorID() {
		return professorID;
	}
	String getName() {
		return name;
	}
	List<Lecture> getLecturesInCharge() {
		return lecturesInCharge;
	}
}
