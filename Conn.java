import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class Conn{
    Connection c;
    Statement s;
    public Conn() {
    try {
     Class.forName("");
     
     c=DriverManager.getConnection("");
     
     s=c.createStatement();
      
  //  System.err.println("success");
    } 
    catch (Exception e) {
         e.printStackTrace();
    }

   }
}