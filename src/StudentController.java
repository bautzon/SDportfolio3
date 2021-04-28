import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    public void setView(StudentView view) {
        this.view = view;

        view.exitBtn.setOnAction(e -> Platform.exit());
        EventHandler<ActionEvent> PrintStudent = e -> HandlePrintStudent(view.StudentCombo.getValue(), view.CourseCombo.getValue(),
                getGrades().get(1), view.StudentText);

        view.FindStudentBtn.setOnAction(PrintStudent);
    }

    public ObservableList<String> getStudent() {
        ArrayList<String> Names = model.SQLQueryStudentNames();
        ObservableList<String> StudentNames = FXCollections.observableList(Names);
        return StudentNames;
    }

    public ObservableList<String> getCourse() {
        ArrayList<String> Course = model.SQLQueryCourses();
        ObservableList<String> Courses = FXCollections.observableList(Course);
        return Courses;
    }

    public ObservableList<Integer> getGrades() {
        ArrayList<Integer> Grade = model.SQLQueryStudentGrade();
        ObservableList<Integer> Grades = FXCollections.observableList(Grade);
        return Grades;
    }

        public void HandlePrintStudent (String Student, String Course, Integer Grade, TextArea txtArea){
            txtArea.clear();
            txtArea.appendText("Student name: Course: Grade: \n");

            model.PreparedstmtFindStudent();
            ArrayList<StudentModel.StudentList> Students = model.FindStudent(Student, Course, Grade);
            for(int i =0; i<Students.size();i++){
                String grad= String.format("%.2f", Students.get(i).Grade);
                txtArea.appendText(i +":"+ Students.get(i).Student+ ":"+ Students.get(i).Course+ ":"+ Students.get(i).Grade + "\n");

            }
        }
    }

