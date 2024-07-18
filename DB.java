import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private static Connection connection;

    static {
        try {
            // load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // connect data.db
            String url = "jdbc:sqlite:data.db";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // insert
    public static void insertIntoStudents(String id, String name) {
        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";
        executeUpdate(sql, id, name);
    }

    public static void insertIntoTeacher(String id, String name) {
        String sql = "INSERT INTO teachers (id, name) VALUES (?, ?)";
        executeUpdate(sql, id, name);
    }

    public static void insertIntoLectures(String name, String room, String dayOfWeekID, String period) {
        String sql = "INSERT INTO lectures (name, room, day_of_week_id, period) VALUES (?, ?, ?, ?)";
        executeUpdate(sql, name, room, dayOfWeekID, period);
    }

    public static void insertIntoTakingLectures(String studentID, int lectureID) {
        String sql = "INSERT INTO taking_lectures (student_id, lecture_id) VALUES (?, ?)";
        executeUpdate(sql, studentID, lectureID);
    }

    public static void insertIntoTeachingLectures(String teacherID, int lectureID) {
        String sql = "INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES (?, ?)";
        executeUpdate(sql, teacherID, lectureID);
    }

    // delete
    public static void deleteFromStudents(String id) {
        String sql = "DELETE FROM taking_lectures WHERE student_id = ?";
        executeUpdate(sql, id);
        sql = "DELETE FROM students WHERE id = ?";
        executeUpdate(sql, id);
    }

    public static void deleteFromTeacher(String id) {
        String sql = "DELETE FROM teaching_lectures WHERE teacher_id = ?";
        executeUpdate(sql, id);
        sql = "DELETE FROM teacher WHERE id = ?";
        executeUpdate(sql, id);
    }

    //
    public static void deleteFromLectures(int id) {
        String sql = "DELETE FROM taking_lectures WHERE lecture_id = ?";
        executeUpdate(sql, id);
        sql = "DELETE FROM teaching_lectures WHERE lecture_id = ?";
        executeUpdate(sql, id);
        sql = "DELETE FROM lectures WHERE id = ?";
        executeUpdate(sql, id);
    }

    public static void deleteFromTakingLectures(String studentID, int lectureID) {
        String sql = "DELETE FROM taking_lectures WHERE student_id = ? AND lecture_id = ?";
        executeUpdate(sql, studentID, lectureID);
    }

    public static void deleteFromTeachingLectures(String teacherID, int lectureID) {
        String sql = "DELETE FROM teaching_lectures WHERE teacher_id = ? AND lecture_id = ?";
        executeUpdate(sql, teacherID, lectureID);
    }

    // select
    public static String[][] selectAllFromStudents(){
        String sql = "SELECT * FROM students";
        String[][] data = executeQuery(sql);

        // debug
        // for (String[] row : data) {
        //     for (String col : row) {
        //         System.out.print(col + "\t");
        //     }
        //     System.out.println();
        // }
        
        return data;
    }

    public static String[][] selectAllFromTeachers() {
        String sql = "SELECT * FROM teachers";
        String[][] data = executeQuery(sql);

        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
        
        return data;
    }

    public static String[][] selectAllFromLecturesView() {
        String sql = "SELECT * FROM lectures_view";
        String[][] data = executeQuery(sql);
        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
        return data;
    }

    public static String[][] selectLecturesByStudent(String studentID) {
        String sql = "SELECT * FROM student_and_lecture_view WHERE student_id = ?";
        String[][] data = executeQuery(sql, studentID);
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
        return data;
    }

    public static void selectStudentsByLecture(String lectureID) {
        String sql = "SELECT * FROM student_and_lecture_view WHERE lecture_id = ?";
        String[][] data = executeQuery(sql, lectureID);
        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    public static void selectAllFromDayOfWeek() {
        String sql = "SELECT * FROM day_of_week";
        String[][] data = executeQuery(sql);
        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    public static String[][] selectTeachersByLecture(int lectureID) {
        String sql = "SELECT * FROM teacher_and_lecture_view WHERE lecture_id = ?";
        String[][] data = executeQuery(sql, lectureID);
        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
        return data;
    }

    public static void selectLecturesByTeacher(String teacherID) {
        String sql = "SELECT * FROM teacher_and_lecture_view WHERE teacher_id = ?";
        String[][] data = executeQuery(sql, teacherID);
        // debug
        for (String[] row : data) {
            for (String col : row) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }

    // edit
    public static void updateStudent(String id, String name) {
        String sql = "UPDATE students SET name = ? WHERE id = ?";
        executeUpdate(sql, name, id);
    }

    public static void updateTeacher(String id, String name) {
        String sql = "UPDATE teachers SET name = ? WHERE id = ?";
        executeUpdate(sql, name, id);
    }

    public static void updateLecture(int id, String name, String room, String dayOfWeekID, String period) {
        String sql = "UPDATE lectures SET name = ?, room = ?, day_of_week_id = ?, period = ? WHERE id = ?";
        executeUpdate(sql, name, room, dayOfWeekID, period, id);
    }

    private static String[][] executeQuery(String sql, Object... params) {
        List<String[]> resultList = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            try (ResultSet resultSet = pstmt.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    String[] row = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = resultSet.getString(i);
                    }
                    resultList.add(row);
                }

                // double array
                String[][] resultArray = new String[resultList.size()][];
                for (int i = 0; i < resultList.size(); i++) {
                    resultArray[i] = resultList.get(i);
                }

                return resultArray;
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return new String[0][];
    }

    private static void executeUpdate(String sql, Object... params) {
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            if (e.getMessage().contains("UNIQUE constraint failed")) {
                System.err.println("Error: Duplicate entry found for primary key.");
            } else {
                System.err.println("Error executing update: " + e.getMessage());
            }
            e.printStackTrace();
        }
    }
}
