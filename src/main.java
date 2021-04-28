import java.sql.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
/*Connection conn = null;
String url = "jdbc:sqlite:!(:memory:){file::identifier.sqlite}?";
conn DriverManager.getConnection(url);*/

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String url = "jdbc:sqlite:C:/Users/Lau/Documents/GitHub/SDportfolio3/identifier.sqlite";
        StudentModel SDB = new StudentModel(url);
        StudentController control = new StudentController(SDB);

        StudentView view = new StudentView(control);
        control.setView(view);
        primaryStage.setTitle("Student database");
        primaryStage.setScene(new Scene(view.asParent(), 600,475));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
/*
   String url = "C:\\Users\\Lau\\Documents\\GitHub\\SDportfolio3\\identifier.sqlite";
        StudentModel SDB = new StudentModel(url);
        try {
            SDB.connect();
            SDB.CreateStatement();
            ArrayList<String> names= SDB.SQLQueryStudentNames();
            SDB.PrintStudents(names);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 */
