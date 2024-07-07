import java.util.List;

public class Professor {
	String professorID;
	String professorName;
	List<Lecture> lecturesInCharge;

	// コンストラクタ、ゲッター、セッターなどのメソッドは省略
	// Definition of constructor
	public Professor(String professorID, String professorName, List<Lecture> lecturesInCharge) {
		this.professorID = professorID;
		this.professorName = professorName;
		this.lecturesInCharge = lecturesInCharge;
	}
	public String getProfessorID() {
		return this.professorID;
	}
	public String getName() {
		return this.professorName;
	}
	public List<Lecture> getLecturesInCharge() {
		return this.lecturesInCharge;
	}
}
