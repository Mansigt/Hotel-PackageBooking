import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Paytm extends JFrame implements ActionListener {
    String username;
    String amount;
    JButton back, pay;
    JTextField tfName, tfCardNumber, tfExpiry;
    JPasswordField pfCvv;

    public Paytm(String username, String amount) {
        this.username = username;
        this.amount = amount;
        
        setTitle("Paytm Payment Gateway Simulator");
        setBounds(500, 150, 800, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Header / Merchant Info (HTML View)
        JEditorPane pane = new JEditorPane();
        pane.setEditable(false);
        pane.setContentType("text/html");
        
        String htmlCode = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f7f9fc; margin: 10px; color: #333333; }" +
                ".header { background-color: #002970; color: white; padding: 12px; text-align: center; font-size: 22px; font-weight: bold; border-radius: 4px; }" +
                ".container { background-color: white; border: 1px solid #e1e4e8; border-radius: 6px; padding: 15px; margin-top: 10px; }" +
                ".row { margin-bottom: 8px; font-size: 14px; }" +
                ".label { font-weight: bold; color: #555555; width: 180px; display: inline-block; }" +
                ".value { color: #002970; font-weight: bold; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='header'>Paytm Secure Payment checkout</div>" +
                "<div class='container'>" +
                "    <div class='row'><span class='label'>Merchant Name:</span> <span class='value'>Travel & Hotel Booking Ltd.</span></div>" +
                "    <div class='row'><span class='label'>Username:</span> <span class='value'>" + username + "</span></div>" +
                "    <div class='row'><span class='label'>Outstanding Balance:</span> <span class='value' style='color: #2e7d32; font-size: 18px;'>" + amount + "</span></div>" +
                "    <div class='row'><span class='label'>Security Protocol:</span> <span class='value' style='color: #00baf2;'>256-Bit SSL Secured</span></div>" +
                "</div>" +
                "</body>" +
                "</html>";
        pane.setText(htmlCode);

        JScrollPane sp = new JScrollPane(pane);
        sp.setBounds(20, 20, 740, 220);
        add(sp);

        // Interactive Card Input GUI Elements
        JLabel lblFormTitle = new JLabel("ENTER CARD DETAILS");
        lblFormTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblFormTitle.setForeground(new Color(0, 41, 112));
        lblFormTitle.setBounds(50, 260, 300, 30);
        add(lblFormTitle);

        JLabel lblName = new JLabel("Cardholder Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblName.setBounds(50, 310, 150, 25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(250, 310, 300, 25);
        add(tfName);

        JLabel lblCardNumber = new JLabel("Card Number");
        lblCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCardNumber.setBounds(50, 350, 150, 25);
        add(lblCardNumber);

        tfCardNumber = new JTextField();
        tfCardNumber.setBounds(250, 350, 300, 25);
        add(tfCardNumber);

        JLabel lblExpiry = new JLabel("Expiry Date (MM/YY)");
        lblExpiry.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblExpiry.setBounds(50, 390, 150, 25);
        add(lblExpiry);

        tfExpiry = new JTextField();
        tfExpiry.setBounds(250, 390, 100, 25);
        add(tfExpiry);

        JLabel lblCvv = new JLabel("CVV");
        lblCvv.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCvv.setBounds(50, 430, 150, 25);
        add(lblCvv);

        pfCvv = new JPasswordField();
        pfCvv.setBounds(250, 430, 100, 25);
        add(pfCvv);

        // Buttons
        pay = new JButton("Confirm Payment");
        pay.setBackground(new Color(0, 41, 112));
        pay.setForeground(Color.WHITE);
        pay.setFont(new Font("Tahoma", Font.BOLD, 14));
        pay.setBounds(200, 510, 180, 40);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Cancel");
        back.setBackground(Color.RED);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Tahoma", Font.BOLD, 14));
        back.setBounds(420, 510, 150, 40);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            String name = tfName.getText().trim();
            String cardNumber = tfCardNumber.getText().trim();
            String expiry = tfExpiry.getText().trim();
            String cvv = new String(pfCvv.getPassword()).trim();

            if (name.isEmpty() || cardNumber.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all card details to complete payment");
                return;
            }

            // Clean & Validate Card Number (must be 16 digits)
            cardNumber = cardNumber.replaceAll("\\s+", ""); // remove spaces
            if (!cardNumber.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(null, "Card Number must be exactly 16 digits");
                return;
            }

            // Expiry verification
            if (!expiry.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
                JOptionPane.showMessageDialog(null, "Expiry Date must be in MM/YY format");
                return;
            }

            // CVV verification
            if (!cvv.matches("\\d{3}")) {
                JOptionPane.showMessageDialog(null, "CVV must be exactly 3 digits");
                return;
            }

            // If validated, record payment in DB
            String query = "insert into payments(username, amount, payment_type, status) values(?, ?, 'Online', 'Completed')";
            try (Conn c = new Conn();
                 PreparedStatement pstmt = c.c.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, amount);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Payment of " + amount + " Recorded Successfully!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            setVisible(false);
            new Payment(username);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Payment(username);
        }
    }

    public static void main(String[] args) {
        new Paytm("testuser", "Rs0");
    }
}
