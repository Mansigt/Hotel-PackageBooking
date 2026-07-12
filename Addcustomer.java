
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;






public class Addcustomer extends JFrame implements ActionListener {
   JLabel labelusername,labelname;
   JComboBox comboid;
   JTextField tfnumber,tfcountry,tfaddress,tfemail,tfphone;
   JButton add,back;
   JRadioButton rmale,rfemale;
    public Addcustomer(String username) {
        setBounds(450,200,850,550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblusername=new JLabel("Username");
        lblusername.setBounds(30,50,150,25);
        add(lblusername);

        labelusername=new JLabel();
        labelusername.setBounds(220,50,150,25);
        add(labelusername);
         
        JLabel lblid=new JLabel("id");
        lblid.setBounds(30,90,150,25);
        add(lblid);

        comboid=new JComboBox<>(new String[]{"passport","aadhar card","pan card","ration card"});
        comboid.setBounds(220,90,130,25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber=new JLabel("Number");
        lblnumber.setBounds(30,130,150,25);
        add(lblnumber);

        tfnumber=new JTextField();
        tfnumber.setBounds(220,130,150,25);
        add(tfnumber);

        JLabel lblname=new JLabel("Name");
        lblname.setBounds(30,170,150,25);
        add(lblname);

        labelname=new JLabel();
        labelname.setBounds(220,170,150,25);
        add(labelname);

        JLabel lblgender=new JLabel("Gender");
        lblgender.setBounds(30,210,70,25);
        add(lblgender);
        
        rmale=new JRadioButton("Male");
        rmale.setBounds(220,210,70,25);
        rmale.setBackground(Color.white);
        add(rmale);

        rfemale=new JRadioButton("Female");
        rfemale.setBounds(300,210,70,25);
        rfemale.setBackground(Color.white);
        add(rfemale);

        ButtonGroup bg=new ButtonGroup();
        bg.add(rmale);
        bg.add(rfemale);

        JLabel lblcountry=new JLabel("Country");
        lblcountry.setBounds(30,250,150,25);
        add(lblcountry);

        tfcountry=new JTextField();
        tfcountry.setBounds(220,250,150,25);
        add(tfcountry);

        JLabel lbladdress=new JLabel("Address");
        lbladdress.setBounds(30,290,150,25);
        add(lbladdress);

        tfaddress=new JTextField();
        tfaddress.setBounds(220,290,150,25);
        add(tfaddress);

        JLabel lblphone=new JLabel("Phone");
        lblphone.setBounds(30,330,150,25);
        add(lblphone);

        tfphone=new JTextField();
        tfphone.setBounds(220,330,150,25);
        add(tfphone);

        JLabel lblemail=new JLabel("Email");
        lblemail.setBounds(30,370,150,25);
        add(lblemail);

        tfemail=new JTextField();
        tfemail.setBounds(220,370,150,25);
        add(tfemail);
 
        add=new JButton("Add");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.white);
        add.setBounds(70,430,100,25);
        add.addActionListener(this);
        add(add);

        back=new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.setBounds(220,430,100,25);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/newcustomer.jpg"));
        Image i2=i1.getImage().getScaledInstance(400,500,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400,40,450,420);
        add(image);

        String accountQuery = "select * from account where username=?";
        try (Conn c = new Conn();
             PreparedStatement pstmt = c.c.prepareStatement(accountQuery)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                while(rs.next()){
                    labelusername.setText(rs.getString("username"));
                    labelname.setText(rs.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        

        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            String username=labelusername.getText();
            String id=(String)comboid.getSelectedItem();
            String number=tfnumber.getText();
            String name=labelname.getText();
            String gender=null;
            if(rmale.isSelected()){
                gender="Male";

            }else{
                gender="Female";

            }
            String country=tfcountry.getText();
            String address=tfaddress.getText();
            String phone=tfphone.getText();
            String email=tfemail.getText();

            String insertQuery = "insert into customer values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (Conn c = new Conn();
                 PreparedStatement pstmt = c.c.prepareStatement(insertQuery)) {
                pstmt.setString(1, username);
                pstmt.setString(2, id);
                pstmt.setString(3, number);
                pstmt.setString(4, name);
                pstmt.setString(5, gender);
                pstmt.setString(6, country);
                pstmt.setString(7, address);
                pstmt.setString(8, phone);
                pstmt.setString(9, email);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "customer details added succcessfully");
                setVisible(false);        
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Addcustomer("");
    }
}
