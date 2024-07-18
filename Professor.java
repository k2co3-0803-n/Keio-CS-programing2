import java.util.List;

public class Professor {
	String professorID;
	String professorName;

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
	
	@Override
	public String toString() {
		return this.professorName;
	}
}
