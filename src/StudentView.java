import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class StudentView {

    private GridPane StartView;
    Button exitBtn = new Button("Exit");
    Button FindStudentBtn = new Button("Find Student");
    Label StudentLbl = new Label("Select student:");
    Label CourseLbl = new Label("Select Course:");
    TextArea StudentText = new TextArea();
    ComboBox<String> StudentCombo = new ComboBox<String>();
    ComboBox<String> CourseCombo = new ComboBox<String>();


    public StudentView(){

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
        StartView.add(StudentText,1,7,15,7);
        StartView.add(FindStudentBtn, 15, 6);
        StartView.add(exitBtn, 20, 15);
        StartView.add(StudentCombo, 15,1);
        StartView.add(CourseCombo, 15, 3);

    }


    public Parent asParent(){
        return StartView;

    }

}

