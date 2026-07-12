
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Viewpackage extends JFrame implements ActionListener{
    JButton back,cancel,receipt;
    String username;
    JLabel labelusername,labelpackage,labelpersons,labelid,labelphone,labelprice,labelstatus;

    public Viewpackage(String username) {
         this.username=username;
         setBounds(450,200,900,450);
         getContentPane().setBackground(Color.WHITE);
         setLayout(null);

         JLabel text=new JLabel("VIEW PACKAGE DETAILS");
         text.setFont(new Font("Tahoma",Font.BOLD,20));
         text.setBounds(60,0,300,30);
         add(text);

         JLabel lblusername=new JLabel("Username");
         lblusername.setBounds(30,50,150,25);
         add(lblusername);

          labelusername=new JLabel();
         labelusername.setBounds(220,50,150,25);
         add(labelusername);

        JLabel lblid=new JLabel("Package");
         lblid.setBounds(30,90,150,25);
         add(lblid);

         labelpackage=new JLabel();
         labelpackage.setBounds(220,90,150,25);
         add(labelpackage);

         JLabel lblnumber=new JLabel("Total persons");
         lblnumber.setBounds(30,130,150,25);
         add(lblnumber);

          labelpersons=new JLabel();
         labelpersons.setBounds(220,130,150,25);
         add(labelpersons);

         JLabel lblname=new JLabel("ID");
         lblname.setBounds(30,170,150,25);
         add(lblname);

          labelid=new JLabel();
         labelid.setBounds(220,170,150,25);
         add(labelid);

         JLabel lblgender=new JLabel("Number");
         lblgender.setBounds(30,210,150,25);
         add(lblgender);

         JLabel labelnumber=new JLabel();
         labelnumber.setBounds(220,210,150,25);
         add(labelnumber);

         JLabel lblcountry=new JLabel("Phone");
         lblcountry.setBounds(30,250,150,25);
         add(lblcountry);

          labelphone=new JLabel();
         labelphone.setBounds(220,250,150,25);
         add(labelphone);

         JLabel lbladdress=new JLabel("Price");
         lbladdress.setBounds(30,290,150,25);
         add(lbladdress);

         labelprice=new JLabel();
         labelprice.setBounds(220,290,150,25);
         add(labelprice);
        
         //
         JLabel l8 = new JLabel("Status");
        l8.setBounds(30,310,150,25);
        add(l8);

        labelstatus = new JLabel();
        labelstatus.setBounds(220,310,200,25);
        add(labelstatus);
         //
         

         back=new JButton("Back");
         back.setBackground(Color.BLACK);
         back.setForeground(Color.WHITE);
         back.setBounds(13,360,100,25);
         back.addActionListener(this);
         add(back);
         
         //new
         cancel = new JButton("Cancel Booking");
         cancel.setBounds(150,360,150,25);
         cancel.setBackground(Color.RED);
         cancel.setForeground(Color.WHITE);
         cancel.addActionListener(this);
         add(cancel);
         
         
         receipt = new JButton("Generate Receipt");
         receipt.setBounds(13,390,180,20);
         receipt.addActionListener(this);
         add(receipt);
         
         //

         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));  
         Image i2=i1.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel image=new JLabel(i3);
         image.setBounds(400,20,500,400);  
         add(image);  

         
          String selectQuery = "select * from bookpackage where username=?";
          try (Conn conn = new Conn();
               PreparedStatement pstmt = conn.c.prepareStatement(selectQuery)) {
              pstmt.setString(1, username);
              try (ResultSet rs = pstmt.executeQuery()) {
                  while(rs.next()){
                      labelusername.setText(rs.getString("username"));
                      labelid.setText(rs.getString("id"));
                      labelnumber.setText(rs.getString("number"));
                      labelpackage.setText(rs.getString("package"));
                      labelprice.setText(rs.getString("price"));
                      labelphone.setText(rs.getString("phone"));
                      labelpersons.setText(rs.getString("persons"));
                      labelstatus.setText(rs.getString("status"));
                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
          }
         setVisible(true);

    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back){
       setVisible(false);
      }
       else if(ae.getSource()==cancel){

        try (Conn c = new Conn();
             PreparedStatement pstmt = c.c.prepareStatement("update bookpackage set status='Cancelled' where username=?")) {
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Package booking cancelled successfully");
        } catch(Exception e){
            e.printStackTrace();
        }

}
     else if(ae.getSource()==receipt){

    GeneratePackageReceipt.generate(

        labelusername.getText(),
        labelpackage.getText(),
        labelpersons.getText(),
        labelid.getText(),
        labelphone.getText(),
        labelprice.getText(),
        labelstatus.getText()
        

    );

    JOptionPane.showMessageDialog(
        null,
        "Receipt Generated Successfully"
    );
}
    }
    public static void main(String[] args) {
        new Viewpackage("");
    }
}
