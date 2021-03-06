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

//Makes an SQL Query to get the student names
    public ArrayList<String> SQLQueryStudentNames() {
        ArrayList<String> Names = new ArrayList<>();

        String sql = "Select firstName From Students;";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                String name = rs.getString(1);
                Names.add(name);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        rs = null;
        return Names;
    }
//makes an SQL query to get course names and semester
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
//prepared statement to to get students names and grades
    public void PreparedstmtFindStudent() {
        String sql = "SELECT s.firstName, c.courseName, r.grade " +
                "FROM Registration AS r JOIN Students AS s ON r.studentID = s.studentID JOIN Course C on C.courseID = r.courseID WHERE s.firstName = ? AND c.courseName=? AND c.semester=?;";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//enters the student names and grades into and arraylist for printing
    public ArrayList<StudentList> FindStudent(String Student, String Course) {
        ArrayList<StudentList> findStudent = new ArrayList<>();
        String name = Course.substring(0, Course.indexOf(' '));
        String semester = Course.substring(Course.indexOf(' ') + 1);
        try {
            pstmt.setString(1, Student);
            pstmt.setString(2, name);
            pstmt.setString(3, semester);
            rs = pstmt.executeQuery();
            if (rs == null) {
                System.out.println("No records fetched.");
            }
            while (rs != null && rs.next()) {
                findStudent.add(new StudentList(rs.getString(1), rs.getString(2), rs.getDouble(3)));
                System.out.println(" Student: " + rs.getString(1) + " Course " + rs.getString(2));
                System.out.println(" Grade: " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return findStudent;
    }
    //prepared statement to to get students names and average grades
    public void PreparedstmtFindStudentAvg() {
        String sql = "SELECT s.firstName, s.lastName, avg(r.grade)\n" +
                "FROM Registration AS r JOIN Students AS s ON r.studentID = s.studentID Group By s.firstName";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//enters the student names and the grade average into an array for printing
    public ArrayList<StudentList> FindStudentAvg() {
        ArrayList<StudentList> findStudent = new ArrayList<>();
        try {
            rs = pstmt.executeQuery();
            if (rs == null) {
                System.out.println("No records fetched.");
            }
            while (rs != null && rs.next()) {
                findStudent.add(new StudentList(rs.getString(1), rs.getString(2), rs.getDouble(3)));
                System.out.println(" Student: " + rs.getString(1) + " " + rs.getString(2));
                System.out.println(" Grade: " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return findStudent;
    }
    //prepared statement to to get courses and average grades
    public void PreparedstmtFindCourseAvg() {
        String sql = "SELECT s.courseName, s.semester, avg(r.grade)\n" +
                "FROM Registration AS r JOIN Course AS s ON r.courseID = s.courseID Group By s.courseID";
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    //enters the courses and the grade average into an array for printing
    public ArrayList<StudentList> FindCourseAvg() {
        ArrayList<StudentList> findStudent = new ArrayList<>();
        try {
            rs = pstmt.executeQuery();
            if (rs == null) {
                System.out.println("No records fetched.");
            }
            while (rs != null && rs.next()) {
                findStudent.add(new StudentList(rs.getString(1), rs.getString(2), rs.getDouble(3)));
                System.out.println(" Student: " + rs.getString(1) + " " + rs.getString(2));
                System.out.println(" Grade: " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return findStudent;
    }
//student list object
    class StudentList {
        String Student;
        String Course;
        Double Grade;

        public StudentList(String Student, String Course, Double Grade) {
            this.Student = Student;
            this.Course = Course;
            this.Grade = Grade;
        }

    }
}

