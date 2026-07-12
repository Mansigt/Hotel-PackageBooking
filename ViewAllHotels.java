import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewAllHotels extends JFrame {

    JTable table;
    DefaultTableModel model;

    ViewAllHotels() {

        setTitle("All Hotel Bookings");

        String[] columns = {
                "Username",
                "Persons",
                "ID",
                "Number",
                "Phone",
                "Price",
                "Status"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        try (Conn c = new Conn();
             PreparedStatement pstmt = c.c.prepareStatement("select * from bookhotel");
             ResultSet rs = pstmt.executeQuery()) {

            while(rs.next()) {

                model.addRow(new Object[] {

                        rs.getString("username"),
                        
                        rs.getString("persons"),
                        rs.getString("id"),
                        rs.getString("number"),
                        rs.getString("phone"),
                        rs.getString("price"),
                        rs.getString("status")

                });
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        add(sp);

        setSize(900,500);
        setLocation(300,150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ViewAllHotels();
    }
}
