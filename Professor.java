import java.util.List;

public class Professor {
	String professorID;
	String professorName;

	// コンストラクタ、ゲッター、セッターなどのメソッドを定義しよう
	// Definition of constructor
	public Professor(String professorID, String professorName) {
		this.professorID = professorID;
		this.professorName = professorName;
	}
	public String getProfessorID() {
		return this.professorID;
	}
	public String getName() {
		return this.professorName;
	}
}
