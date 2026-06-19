
import java.awt.Color;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener{

    public About() {
        setBounds(600,200,500,550);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel l1=new JLabel("ABOUT");
        l1.setBounds(200,10,100,40);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(l1);

        String s=" \"                                    About Projects          \\n" + //
                        "  \"\r\n" + //
                        "                + \"\\n" + //
                        "The objective of the Travel and Tourism Management System\"\r\n" + //
                        "                + \"project is to develop a system that automates the processes \"\r\n" + //
                        "                + \"and activities of a travel and the purpose is to design a \"\r\n" + //
                        "                + \"system using which one can perform all operations related to \"\r\n" + //
                        "                + \"traveling.\\n" + //
                        "\\n" + //
                        "\"\r\n" + //
                        "                + \"This application will help in accessing the information related \"\r\n" + //
                        "                + \"to the travel to the particular destination with great ease. \"\r\n" + //
                        "                + \"The users can track the information related to their tours with \"\r\n" + //
                        "                + \"great ease through this application. The travel agency information \"\r\n" + //
                        "                + \"can also be obtained through this application.\\n" + //
                        "\\n" + //
                        "\"\r\n" + //
                        "                + \"Advantages of Project:\"\r\n" + //
                        "                + \"\\n" + //
                        "Gives accurate information\"\r\n" + //
                        "                + \"\\n" + //
                        "Simplifies the manual work\"\r\n" + //
                        "                + \"\\n" + //
                        "It minimizes the documentation related work\"\r\n" + //
                        "                + \"\\n" + //
                        "Provides up to date information\"\r\n" + //
                        "                + \"\\n" + //
                        "Friendly Environment by providing warning messages.\"\r\n" + //
                        "                + \"\\n" + //
                        "travelers details can be provided\"\r\n" + //
                        "                + \"\\n" + //
                        "booking confirmation notification\"\r\n" + //
                        "                ;\r\n" + //
                        "";

        TextArea area=new TextArea(s,10,40,Scrollbar.VERTICAL);
        area.setEditable(false);
        area.setBounds(20,100,450,300);
        add(area);
      
        JButton back=new JButton("Back");
        back.setBounds(200,420,100,25);
        back.addActionListener(this);
        add(back);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
    }
    public static void main(String[] args) {
        new About();
    }
}
