import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminDashboard extends JFrame implements ActionListener {

    String username;

    JButton viewUsers;
    JButton viewPackages;
    JButton viewHotels;
    JButton logout;

    AdminDashboard(String username){

        this.username = username;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        JLabel heading = new JLabel("ADMIN DASHBOARD");
        heading.setBounds(500, 30, 400, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        viewUsers = new JButton("View All Users");
        viewUsers.setBounds(500,120,250,40);
        viewUsers.addActionListener(this);
        add(viewUsers);

        viewPackages = new JButton("View Package Bookings");
        viewPackages.setBounds(500,190,250,40);
        viewPackages.addActionListener(this);
        add(viewPackages);

        viewHotels = new JButton("View Hotel Bookings");
        viewHotels.setBounds(500,260,250,40);
        viewHotels.addActionListener(this);
        add(viewHotels);

        logout = new JButton("Logout");
        logout.setBounds(500,330,250,40);
        logout.addActionListener(this);
        add(logout);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == logout){
            setVisible(false);
            new Login();
        }

        else if(ae.getSource() == viewUsers){
            
            new ViewAllUsers();
            
        }

        else if(ae.getSource() == viewPackages){
           
            new ViewAllPackages();
        }

        else if(ae.getSource() == viewHotels){
            ;
            new ViewAllHotels();
        }
    }

    public static void main(String[] args){
        new AdminDashboard("Admin");
    }
}