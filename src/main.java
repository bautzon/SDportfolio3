/*
Made by:
Lau Sivertsen
Bjørn Utzon
Magnus Nielsen
Date: 28-04-2021
*/
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


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
