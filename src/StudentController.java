import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController {
    StudentModel model;
    StudentView view;

    public StudentController(StudentModel model) {
        this.model = model;
        try {
            model.connect();
            model.CreateStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
//calculates the students GPA
    public void setView(StudentView view) {
        this.view = view;

        view.exitBtn.setOnAction(e -> Platform.exit());
        view.FindStudentBtn.setOnAction(e -> HandlePrintStudent(view.StudentCombo.getValue(), view.CourseCombo.getValue(),
                view.StudentText));
        view.avgStudentBtn.setOnAction(e -> HandleStudentAvg(view.StudentText));
        view.avgCourseBtn.setOnAction(e -> HandleCourseAvg(view.StudentText));
    }
//Retrieves student list
    public ObservableList<String> getStudent() {
        ArrayList<String> Names = model.SQLQueryStudentNames();
        ObservableList<String> StudentNames = FXCollections.observableList(Names);
        return StudentNames;
    }
//retrieves the couselist
    public ObservableList<String> getCourse() {
        ArrayList<String> Course = model.SQLQueryCourses();
        ObservableList<String> Courses = FXCollections.observableList(Course);
        return Courses;
    }
//Retreives a given students grade from selected course
    public void HandlePrintStudent (String Student, String Course, TextArea txtArea){
        txtArea.clear();
        txtArea.appendText("Student name: Course: Grade: \n");

        model.PreparedstmtFindStudent();
        ArrayList<StudentModel.StudentList> Students = model.FindStudent(Student, Course);
        for(int i =0; i<Students.size();i++){
            txtArea.appendText( Students.get(i).Student+ " "+ Students.get(i).Course+ ": "+ Students.get(i).Grade + "\n");

        }
    }
    //Returns the students GPA
    public void HandleStudentAvg (TextArea txtArea){
        txtArea.clear();
        txtArea.appendText("Student name: Grade: \n");

        model.PreparedstmtFindStudentAvg();
        ArrayList<StudentModel.StudentList> Students = model.FindStudentAvg();
        for(int i =0; i<Students.size();i++){
            txtArea.appendText( Students.get(i).Student+ " "+ Students.get(i).Course+ ": "+ Students.get(i).Grade + "\n");

        }

    }
    //return the average grade for the course in the GUI menu
    public void HandleCourseAvg (TextArea txtArea){
        txtArea.clear();
        txtArea.appendText("Student name: Grade: \n");

        model.PreparedstmtFindCourseAvg();
        ArrayList<StudentModel.StudentList> Students = model.FindCourseAvg();
        for(int i =0; i<Students.size();i++){
            txtArea.appendText( Students.get(i).Student+ " "+ Students.get(i).Course+ ": "+ Students.get(i).Grade + "\n");

        }

    }


}

