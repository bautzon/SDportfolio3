import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class StudentModel {

    Connection conn = null;
    String url;

    Statement stmt = null;
    ResultSet rs=null;

    StudentModel(String url){
        this.url=url;
    }

    public void connect() throws SQLException {
        conn=getConnection(url);
    }

    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    public void CreateStatement() throws SQLException{
        this.stmt=conn.createStatement();

    }


    public ArrayList<String> SQLQueryStudentNames(){
        ArrayList<String> Names=new ArrayList<>();

        String sql="Select firstName From Students;";
        try {
            rs = stmt.executeQuery(sql);
            while(rs != null && rs.next()){
                String name =rs.getString(1);
                Names.add(name);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        rs=null;
        return Names;
    }

    public void PrintStudents(ArrayList<String> Students){
        for(int i=0; i<Students.size();i++){
            System.out.println(Students.get(i));
        }

    }

}
