import java.sql.*;
/*Connection conn = null;
String url = "jdbc:sqlite:!(:memory:){file::identifier.sqlite}?";
conn DriverManager.getConnection(url);*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        StudentView view = new StudentView();
        primaryStage.setTitle("Student database");
        primaryStage.setScene(new Scene(view.asParent(), 600,475));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
        }

}
/*
StudentModel SDB = new StudentModel(url);
SDB.connect();
SDB.CreateStatement();
Arraylist<String> names= SDB.SQLQueryStudentNames();
SDB.PrintStudents(names)

 */
