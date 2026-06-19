
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Viewbookedhotel extends JFrame implements ActionListener{
    JButton back,cancel;
    String username;
    JLabel labelstatus;

    public Viewbookedhotel(String username) {
         this.username=username;
         setBounds(400,200,1000,600);
         getContentPane().setBackground(Color.WHITE);
         setLayout(null);

         JLabel text=new JLabel("VIEW BOOKED HOTEL DETAILS");
         text.setFont(new Font("Tahoma",Font.BOLD,20));
         text.setBounds(60,0,400,30);
         add(text);

         JLabel lblusername=new JLabel("Username");
         lblusername.setBounds(30,50,150,25);
         add(lblusername);

         JLabel labelusername=new JLabel();
         labelusername.setBounds(220,50,150,25);
         add(labelusername);

        JLabel lblid=new JLabel("Hotel Namw");
         lblid.setBounds(30,90,150,25);
         add(lblid);

         JLabel labelpackage=new JLabel();
         labelpackage.setBounds(220,90,150,25);
         add(labelpackage);

         JLabel lblnumber=new JLabel("Total persons");
         lblnumber.setBounds(30,130,150,25);
         add(lblnumber);

         JLabel labelpersons=new JLabel();
         labelpersons.setBounds(220,130,150,25);
         add(labelpersons);

         JLabel lbldays=new JLabel("Total Days");
         lbldays.setBounds(30,170,150,25);
         add(lbldays);

         JLabel labeldays=new JLabel();
         labeldays.setBounds(220,170,150,25);
         add(labeldays);

         JLabel lblac=new JLabel("AC/NON-AC");
         lblac.setBounds(30,210,150,25);
         add(lblac);

         JLabel labelac=new JLabel();
         labelac.setBounds(220,210,150,25);
         add(labelac);

         JLabel lblfood=new JLabel("Food Included?");
         lblfood.setBounds(30,250,150,25);
         add(lblfood);

         JLabel labelfood=new JLabel();
         labelfood.setBounds(220,250,150,25);
         add(labelfood);

         JLabel lblname=new JLabel("ID");
         lblname.setBounds(30,290,150,25);
         add(lblname);

         JLabel labelid=new JLabel();
         labelid.setBounds(220,290,150,25);
         add(labelid);

         JLabel lblgender=new JLabel("Number");
         lblgender.setBounds(30,330,150,25);
         add(lblgender);

         JLabel labelnumber=new JLabel();
         labelnumber.setBounds(220,330,150,25);
         add(labelnumber);

         JLabel lblcountry=new JLabel("Phone");
         lblcountry.setBounds(30,370,150,25);
         add(lblcountry);

         JLabel labelphone=new JLabel();
         labelphone.setBounds(220,370,150,25);
         add(labelphone);

         JLabel lbladdress=new JLabel("Total Cost");
         lbladdress.setBounds(30,410,150,25);
         add(lbladdress);

         JLabel labelprice=new JLabel();
         labelprice.setBounds(220,410,150,25);
         add(labelprice);

         //
         JLabel l11 = new JLabel("Status");
         l11.setBounds(30,430,150,25);
         add(l11);

         labelstatus = new JLabel();
         labelstatus.setBounds(220,430,150,25);
         add(labelstatus);
         //

         back=new JButton("Back");
         back.setBackground(Color.BLACK);
         back.setForeground(Color.WHITE);
         back.setBounds(13,460,100,25);
         back.addActionListener(this);
         add(back);
         
         //
         cancel = new JButton("Cancel Booking");
         cancel.setBounds(150,460,150,25);
         cancel.setBackground(Color.RED);
         cancel.setForeground(Color.WHITE);
         cancel.addActionListener(this);
         add(cancel);
         //
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bookedDetails.jpg"));  
         Image i2=i1.getImage().getScaledInstance(500,400,Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel image=new JLabel(i3);
         image.setBounds(400,20,500,400);  
         add(image);  

         
         try {
             Conn conn=new Conn();
             String query="select * from bookhotel where username='"+username+"'";
            ResultSet rs= conn.s.executeQuery(query);
            while(rs.next()){
                labelusername.setText(rs.getString("username"));
                
                labelid.setText(rs.getString("id"));
               
                labelnumber.setText(rs.getString("number"));
                 labelpackage.setText(rs.getString("name"));
                
                labelprice.setText(rs.getString("price"));
                labelphone.setText(rs.getString("phone"));
                labelpersons.setText(rs.getString("persons"));
                 labelfood.setText(rs.getString("food"));
               labelac.setText(rs.getString("ac"));
               labeldays.setText(rs.getString("days"));
               labelstatus.setText(rs.getString("status"));
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

        try{

        Conn c = new Conn();
        String query ="update bookhotel set status='Cancelled' where username='"+username+"'";
        c.s.executeUpdate(query);
        JOptionPane.showMessageDialog(null,
        "Hotel booking cancelled successfully");

        labelstatus.setText("Cancelled");

        }catch(Exception e){
        e.printStackTrace();
       }
      }
    }
    public static void main(String[] args) {
        new Viewbookedhotel("");
    }
}
