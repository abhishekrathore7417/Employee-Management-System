package emplyoee.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Statement s;
    Connection c;
    public Conn(){

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        c= DriverManager.getConnection("jdbc:mysql:///employeemanagementsystem","root","12345678");
        s=c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
