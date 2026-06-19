
import java.awt.Choice;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Signup extends JFrame implements ActionListener
{
    JButton create,back;
    JTextField tfname,tfusername,tfpassword,tfanswer;
    Choice security,role;
  Signup(){
   setLayout(null);
   setBounds(350,200,900,360); 
   getContentPane().setBackground(Color.WHITE);
  
   JPanel p1=new JPanel();
   p1.setBackground(Color.pink);
   p1.setLayout(null);
   p1.setBounds(0,0,500,400);
   add(p1);

   JLabel lblusername=new JLabel("Username");
   lblusername.setFont(new Font("Tahoma",Font.BOLD,14));
   lblusername.setBounds(50,20,125,25);
   p1.add(lblusername);

    tfusername=new JTextField();
   tfusername.setBounds(190,20,180,25);
   p1.add(tfusername);
   
  JLabel lblname=new JLabel("Name");
   lblname.setFont(new Font("Tahoma",Font.BOLD,14));
   lblname.setBounds(50,60,125,25);
   p1.add(lblname);

    tfname=new JTextField();
   tfname.setBounds(190,60,180,25);
   p1.add(tfname);

   JLabel lblpassword=new JLabel("Password");
   lblpassword.setFont(new Font("Tahoma",Font.BOLD,14));
   lblpassword.setBounds(50,100,125,25);
   p1.add(lblpassword);

   tfpassword=new JTextField();
   tfpassword.setBounds(190,100,180,25);
   p1.add(tfpassword);

   JLabel lblsecurity=new JLabel("Security Question");
   lblsecurity.setFont(new Font("Tahoma",Font.BOLD,14));
   lblsecurity.setBounds(50,140,135,25);
   p1.add(lblsecurity);
   
   security=new Choice();
   security.add("fav character from the boys");
   security.add("fav marvel hero");
   security.add("your childhood superhero");
   security.setBounds(190,140,190,55);
   p1.add(security);
   
   JLabel lblanswer=new JLabel("Answer");
   lblanswer.setFont(new Font("Tahoma",Font.BOLD,14));
   lblanswer.setBounds(50,180,125,25);
   p1.add(lblanswer);

   tfanswer=new JTextField();
   tfanswer.setBounds(190,180,180,25);
   p1.add(tfanswer);
   
   //role selection
   JLabel l6 = new JLabel("Role");
   l6.setBounds(50, 210, 125, 25);
   l6.setFont(new Font("Tahoma", Font.BOLD, 14));
   p1.add(l6);

   role = new Choice();
   role.add("Customer");
   role.add("Admin"); 
   role.setBounds(190, 210, 180, 25);
   p1.add(role);
   
   //end
   
   create=new JButton("Create");
   create.setBackground(Color.LIGHT_GRAY);
   create.setBounds(80,250,100,30);
   create.addActionListener(this);
   p1.add(create);

  
   back=new JButton("Back");
   back.setBackground(Color.LIGHT_GRAY);
   back.setBounds(250,250,100,30);
   back.addActionListener(this);
   p1.add(back);
  

    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/signup.png"));
    Image i2= i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
   
    image.setBounds(600,50, 200, 200);
    add(image);
   


   
   setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==create){
        String username=tfusername.getText();
        String name=tfname.getText();
        if(!name.matches("[a-zA-Z ]+")){
    JOptionPane.showMessageDialog(null,
        "Name cannot contain numbers");
    return;
}
        String password=tfpassword.getText();
        String question=security.getSelectedItem();
        String answer=tfanswer.getText();
        String userrole=role.getSelectedItem();

        String query="insert into account values('"+username+"','"+name+"','"+password+"','"+question+"','"+answer+"','"+userrole+"')";
        try {
          Conn c=new Conn();
          String checkQuery ="select * from account where username='" + username + "'";
          ResultSet rs = c.s.executeQuery(checkQuery);

        if(rs.next()){
             JOptionPane.showMessageDialog(null, "Username already exists");
             return;
         }
         if(username.isEmpty() || name.isEmpty() || password.isEmpty() || answer.isEmpty()){

            JOptionPane.showMessageDialog(null,"All fields are required");
            return;
             }
          c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null,"account created successfully");
            setVisible(false);
            new Login();
        } catch (Exception e) {
           
            e.printStackTrace();
        }
    }else if(ae.getSource()==back){
        setVisible(false);
        new Login();
    }

  }
    public static void main(String[] args) {
        new Signup();

    }
}