
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Payment extends JFrame implements ActionListener{
    JButton back,pay;
    String username;
    public Payment(String username) {
        this.username=username;
        setBounds(500,200,800,600);
        setLayout(null);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/paytm.jpeg"));
        Image i2=i1.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,800,600);
        add(image);

        pay=new JButton("Pay");
        pay.setBounds(420,0,80,40);
        pay.addActionListener(this);
        image.add(pay);

        back=new JButton("Back");
        back.setBounds(520,0,80,40);
        back.addActionListener(this);
        image.add(back);
    
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==pay){
       String amount = JOptionPane.showInputDialog(
        null,
        "Enter Payment Amount"
    );

    if(amount != null && !amount.trim().equals("")){
            try{

            Conn c = new Conn();

            String query = "insert into payments(username,amount,payment_type,status) values('"+ username + "','"+ amount + "','Online','Completed')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Payment Recorded Successfully");

        }catch(Exception e){
            e.printStackTrace();
        }
        setVisible(false);
        new Paytm();
           }
          }
    }
    public static void main(String[] args) {
        new Payment("testuser");
    }
}
