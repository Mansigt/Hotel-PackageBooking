import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewAllUsers extends JFrame {

    JTable table;
    DefaultTableModel model;

    ViewAllUsers(){

        String[] columns = {
                "Username",
                "Name",
                "Role",
                "Question",
                "Answer"
        };

        model = new DefaultTableModel(columns,0);
        table = new JTable(model);

        try (Conn c = new Conn();
             PreparedStatement pstmt = c.c.prepareStatement("select * from account");
             ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()){

                model.addRow(new Object[]{

                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("security"),
                        rs.getString("answer")

                });

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        add(new JScrollPane(table));

        setSize(900,500);
        setLocation(300,150);
        setVisible(true);

    }

    public static void main(String[] args){
        new ViewAllUsers();
    }
}