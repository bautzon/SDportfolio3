import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class StudentView {
    StudentController control;

    //Create the different parts of the GUI
    private GridPane StartView;
    Button exitBtn = new Button("Exit");
    Button FindStudentBtn = new Button("Find Student");
    Label StudentLbl = new Label("Select student:");
    Label CourseLbl = new Label("Select Course:");
    TextArea StudentText = new TextArea();
    ComboBox<String> StudentCombo = new ComboBox<String>();
    ComboBox<String> CourseCombo = new ComboBox<String>();
    Button avgStudentBtn = new Button("Average student GPA");
    Button avgCourseBtn = new Button("Average Course GPA");


    public StudentView(StudentController control){
        this.control=control;
        CreateAndConfigure();
    }
    private void CreateAndConfigure(){

        StartView=new GridPane();
        StartView.setMinSize(300,200);
        StartView.setPadding(new Insets(10,10,10,10));
        StartView.setVgap(5);
        StartView.setHgap(1);

        StartView.add(StudentLbl, 1, 1);
        StartView.add(CourseLbl, 1,3);
        StartView.add(StudentText,1,7,17,7);
        StartView.add(FindStudentBtn, 15, 6);
        StartView.add(exitBtn, 20, 15);
        StartView.add(StudentCombo, 15,1);
        StartView.add(CourseCombo, 15, 3);
        StartView.add(avgCourseBtn, 16,6);
        StartView.add(avgStudentBtn, 17,6);

        ObservableList<String> studentList = control.getStudent();
        StudentCombo.setItems(studentList);
        StudentCombo.getSelectionModel().selectFirst();

        ObservableList<String> courseList = control.getCourse();
        CourseCombo.setItems(courseList);
        CourseCombo.getSelectionModel().selectFirst();
    }


    public Parent asParent(){
        return StartView;

    }

}
