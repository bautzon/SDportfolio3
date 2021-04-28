import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class StudentModel {

    Connection conn = null;
    String url;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    StudentModel(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        conn = getConnection(url);
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public void CreateStatement() throws SQLException {
        this.stmt = conn.createStatement();

    }


    public ArrayList<String> SQLQueryStudentNames() {
        ArrayList<String> Names = new ArrayList<>();

        String sql = "Select firstName, lastName From Students;";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String name = rs.getString(1) + " " + rs.getString(2);
                Names.add(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        rs = null;
        return Names;
    }

    public ArrayList<String> SQLQueryCourses() {
        ArrayList<String> Courses = new ArrayList<>();

        String sql = "Select courseName, semester From Course;";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String course = rs.getString(1) + " " + rs.getString(2);
                Courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        rs = null;
        return Courses;
    }

    public ArrayList<Integer> SQLQueryStudentGrade() {
        ArrayList<Integer> Grades = new ArrayList<>();

        String sql = "Select grade From Registration;";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                Integer grade = rs.getInt(1);
                Grades.add(grade);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        rs = null;
        return Grades;
    }

    public void PreparedstmtFindStudent() {
        String sql = "SELECT s.firstName, avg(r.grade) " +
                "FROM Registration AS r JOIN Students AS s ON r.studentID = s.studentID WHERE s.firstName = ?;";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<StudentList> FindStudent(String Student, String Course, Integer Grade) {
        ArrayList<StudentList> findStudent = new ArrayList<>();
        try {
            pstmt.setString(1, Student);
            pstmt.setString(2, Course);
            pstmt.setDouble(3, Grade);
            rs = pstmt.executeQuery();
            if (rs == null) {
                System.out.println("No records fetched.");
            }
            while (rs != null && rs.next()) {
                findStudent.add(new StudentList(rs.getString(1), rs.getString(2), rs.getInt(3)));
                System.out.println(" Student: " + rs.getString(1) + " Course " + rs.getString(2));
                System.out.println(" Grade: " + rs.getInt(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return findStudent;
    }

    class StudentList {
        String Student;
        String Course;
        Integer Grade;

        public StudentList(String Student, String Course, Integer Grade) {
            this.Student = Student;
            this.Course = Course;
            this.Grade = Grade;
        }


    }
}

