import javafx.application.Platform;

import java.sql.SQLException;

public class StudentController {
    StudentModel model;
    StudentView view;

    public void StudentController(StudentModel model){
        this.model=model;
        try{
            model.connect();
            model.CreateStatement();
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setView(StudentView view) {
        this.view = view;

        view.exitBtn.setOnAction(e-> Platform.exit());
    }
}
