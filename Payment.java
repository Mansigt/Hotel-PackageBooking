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

public class Payment extends JFrame implements ActionListener {
    JButton back, pay;
    String username;
    
    public Payment(String username) {
        this.username = username;
        setBounds(500, 200, 800, 600);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/paytm.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 800, 600);
        add(image);

        pay = new JButton("Pay");
        pay.setBounds(420, 0, 80, 40);
        pay.addActionListener(this);
        image.add(pay);

        back = new JButton("Back");
        back.setBounds(520, 0, 80, 40);
        back.addActionListener(this);
        image.add(back);
    
        setVisible(true);
    }

    private int calculateTotalAmount(String username) {
        int total = 0;
        try (Conn c = new Conn()) {
            String q1 = "select price from bookpackage where username = ? and status != 'Cancelled'";
            try (PreparedStatement pstmt1 = c.c.prepareStatement(q1)) {
                pstmt1.setString(1, username);
                try (ResultSet rs1 = pstmt1.executeQuery()) {
                    while (rs1.next()) {
                        String priceStr = rs1.getString("price");
                        if (priceStr != null) {
                            priceStr = priceStr.replaceAll("[^0-9]", ""); // Keep only digits
                            if (!priceStr.isEmpty()) {
                                total += Integer.parseInt(priceStr);
                            }
                        }
                    }
                }
            }
            
            String q2 = "select price from bookhotel where username = ? and status != 'Cancelled'";
            try (PreparedStatement pstmt2 = c.c.prepareStatement(q2)) {
                pstmt2.setString(1, username);
                try (ResultSet rs2 = pstmt2.executeQuery()) {
                    while (rs2.next()) {
                        String priceStr = rs2.getString("price");
                        if (priceStr != null) {
                            priceStr = priceStr.replaceAll("[^0-9]", ""); // Keep only digits
                            if (!priceStr.isEmpty()) {
                                total += Integer.parseInt(priceStr);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            int totalAmount = calculateTotalAmount(username);
            if (totalAmount <= 0) {
                JOptionPane.showMessageDialog(null, "You have no outstanding booking payments.");
                return;
            }
            
            int response = JOptionPane.showConfirmDialog(
                null,
                "Your total outstanding booking amount is Rs " + totalAmount + ". Do you want to pay?",
                "Confirm Payment",
                JOptionPane.YES_NO_OPTION
            );
            
            if (response == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Paytm(username, "Rs" + totalAmount);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Payment("testuser");
    }
}
