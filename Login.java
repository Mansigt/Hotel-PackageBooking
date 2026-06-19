
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class Login extends JFrame implements ActionListener{
    JButton signup,login,password;
    JTextField tfusername,tfpassword;
    JCheckBox showPassword;
    public Login()
    {
     setSize(900,500);
     setLocation(350,200);
     setLayout(null);

    JPanel p1=new JPanel();
    p1.setBackground(Color.PINK);
    p1.setBounds(0,0, 500,500);
    add(p1);

    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
    Image i2= i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel image=new JLabel(i3);
    p1.setLayout(null);
    image.setBounds(130,100, 200, 200);
    p1.add(image);

    JPanel p2=new JPanel();
    p2.setLayout(null);
    p2.setBounds(500,0, 400,500);
    p2.setBackground(Color.WHITE);
    add(p2);

    JLabel lblusername=new JLabel("Username");
    lblusername.setBounds(20,30,100,20);
    lblusername.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
    p2.add(lblusername);  
  
     tfusername=new JTextField();
    tfusername.setBounds(20,60,300,30);
    p2.add(tfusername);

     JLabel lblpassword=new JLabel("Password");
    lblpassword.setBounds(20,100,100,20);
    lblpassword.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
    p2.add(lblpassword);  
  
    tfpassword=new JPasswordField();
    tfpassword.setBounds(20,140,300,30);
    ((JPasswordField) tfpassword).setEchoChar('*');
    p2.add(tfpassword);
    showPassword = new JCheckBox("Show");
    showPassword.setBounds(20, 190, 100, 20);
    showPassword.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {

        if(showPassword.isSelected()) {
            ((JPasswordField) tfpassword).setEchoChar((char)0);
        }
        else {
            ((JPasswordField) tfpassword).setEchoChar('*');
        }    }
    });
    p2.add(showPassword);
    
    login=new JButton("Login");
    login.setBounds(20,250 ,80,30);
    login.setBackground(Color.LIGHT_GRAY);
    login.setForeground(Color.black);
    login.addActionListener(this);
    p2.add(login);
    
    signup=new JButton("Signup");
    signup.setBounds(200,250 ,80,30);
    signup.setBackground(Color.LIGHT_GRAY);
    signup.setForeground(Color.black);
    signup.addActionListener(this);
    p2.add(signup);

    password=new JButton("Forget Password");
    password.setBounds(20,350 ,130,30);
    password.setBackground(Color.LIGHT_GRAY);
    password.setForeground(Color.black);
    password.addActionListener(this);
    p2.add(password);
    
    JLabel text=new JLabel("Trouble in logging...");
    text.setBounds(170,350,200, 40);
    text.setForeground(Color.RED);
    p2.add(text);
     





    setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
           try {
                String username=tfusername.getText();
                String pass=tfpassword.getText();
                String query="SELECT * FROM account WHERE username='" +username+ "' AND password='" +pass+ "'";
                
                Conn c =new Conn();
                ResultSet rs=c.s.executeQuery(query);
                if(rs.next()){
                    String role = rs.getString("role");
                    System.out.println("Logged in as: " + role);
                    setVisible(false);
                    new Loading(username,role);
                }else{
                    JOptionPane.showMessageDialog(null, "incorrect password or username");
                }
                
            } catch (Exception e) {
                //JOptionPane.showMessageDialog(this, e.getMessage());
               e.printStackTrace();
            }

        }else if(ae.getSource()==signup){
           setVisible(false);
           new Signup();
        }else{
         setVisible(false);
         new ForgetPassword();
        }
    }

      
    
    public static void main(String[] args) {
        new Login();
    }
}
