// Entry point

import java.util.ArrayList;

public class MyApp {
	static ArrayList<Student> students = new ArrayList<Student>();
	static ArrayList<Lecture> lectures = new ArrayList<Lecture>();
	static ArrayList<Professor> professors = new ArrayList<Professor>();
	
	static DayOfWeek[] dayOfWeeks = new DayOfWeek[7];

	// 生徒の初期化
	static void initStudents() {
		students.clear();
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
		professors.clear();
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
		lectures.clear();
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

			Lecture lecture = new Lecture(lectureID, lectureName, classRoom, dayOfWeek, period,
					professorInChargeExist ? professorInCharge : "");
			lectures.add(lecture);
		}
	}
	
	// 曜日の初期化
	static void initDayOfWeeks() {
		String[][] dayOfWeekRawData = DB.selectAllFromDayOfWeek();
		for (String[] dayOfWeekRaw : dayOfWeekRawData) {
			String dayOfWeekID = dayOfWeekRaw[0];
			String dayOfWeekName = dayOfWeekRaw[1];
			DayOfWeek dayOfWeek = new DayOfWeek(dayOfWeekID, dayOfWeekName);
			dayOfWeeks[Integer.parseInt(dayOfWeekID) - 1] = dayOfWeek;
		}
	}
	

	static void initdata() {
		MyApp.initStudents();
		MyApp.initTeachers();
		MyApp.initLectures();
	}

	// clase method
	public static void main(String[] args) {
		// initialize
		initDayOfWeeks();
		MyApp.initdata();
		Home.createHome();
	}
}
