// Entry point

import java.util.ArrayList;

public class MyApp {
	static ArrayList<Student> students = new ArrayList<Student>();
	static ArrayList<Lecture> lectures = new ArrayList<Lecture>();
	static ArrayList<Professor> professors = new ArrayList<Professor>();

	// 生徒の初期化
	static void initStudents() {
		String[][] studentsRawData = DB.selectAllFromStudents();
		for (String[] studentRawData : studentsRawData) {
			String studentID = studentRawData[0];
			String studentName = studentRawData[1];
			Student student = new Student(studentID, studentName);
			students.add(student);
		}
	}
	
	// 教員の初期化
	static void initTeachers() {
		String[][] teachersRawData = DB.selectAllFromTeachers();
		for (String[] teacherRawData : teachersRawData) {
			String teacherID = teacherRawData[0];
			String teacherName = teacherRawData[1];
			Professor teacher = new Professor(teacherID, teacherName);
			professors.add(teacher);
		}
	}
	
	// 講義の初期化
	// selectAllFromLecturesView()を使う
	static void initLectures() {
		String[][] lecturesRawData = DB.selectAllFromLecturesView();
		for (String[] lectureRawData : lecturesRawData) {
			String lectureID = lectureRawData[0];
			String lectureName = lectureRawData[1];
			String classRoom = lectureRawData[2];
			String dayOfWeek = lectureRawData[3];
			String period = lectureRawData[4];

			String[][] professorInChargeRaw = DB.selectTeachersByLecture(Integer.parseInt(lectureID));
			String professorInCharge = "";
			boolean professorInChargeExist = false;
			for (String[] row : professorInChargeRaw) {
				professorInCharge = row[3];
				professorInChargeExist = true;
			}

			Lecture lecture = new Lecture(lectureID, lectureName, classRoom, dayOfWeek, period, professorInChargeExist ? professorInCharge : "");
			lectures.add(lecture);
		}
	}
	

	

	// clase method
	public static void main(String[] args) {
		// initialize
		MyApp.initStudents();
		MyApp.initTeachers();
		MyApp.initLectures();
		
		Home.createHome();
	}
}
