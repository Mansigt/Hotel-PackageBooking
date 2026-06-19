import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class Dashboard extends JFrame implements ActionListener {
  String username,role;
  JButton addpersonaldetails,viewpersonaldetails,updatepersonaldetails,checkpackages,bookpackages;
  JButton viewpackage,viewhotels,destinations,bookhotel,viewbookedhotel,payments,calculators,about,notepad,deletepersonaldetails;
  Dashboard(String username,String role){
    this.username=username;
    this.role=role;
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setLayout(null);
    JPanel p1=new JPanel();
    p1.setLayout(null);
    p1.setBackground(new Color(0,0,102));
    p1.setBounds(0,0,1600,65);
    add(p1);

    ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/bookpackage.jpg"));
    Image i2=i1.getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT);
    ImageIcon i3=new ImageIcon(i2);
    JLabel icon =new JLabel(i3);
    icon.setBounds(5,0,70,70);
    p1.add(icon);

    JLabel heading=new JLabel("DASHBOARD");
    heading.setBounds(80,10,300,40);
   heading.setForeground(Color.WHITE);
   heading.setFont(new Font("Tahoma",Font.BOLD,16) );
    p1.add(heading);
    
    //role
    JLabel rolelabel = new JLabel("Role : " + role);
    rolelabel.setBounds(1200,10,250,40);
    rolelabel.setForeground(Color.WHITE);
    rolelabel.setFont(new Font("Tahoma",Font.BOLD,16));
    p1.add(rolelabel);
    //

     JPanel p2=new JPanel();
    p2.setLayout(null);
    p2.setBackground(new Color(0,0,102));
    p2.setBounds(0,65,300,900);
    add(p2);

     addpersonaldetails=new JButton("Add personal details");
    addpersonaldetails.setBounds(0,0,300,50);
    addpersonaldetails.setBackground(new Color(0,0,102));
    addpersonaldetails.setForeground(Color.white);
    addpersonaldetails.setFont(new Font("Tahoma",Font.PLAIN,20));
    addpersonaldetails.setMargin( new Insets(0,0,0,60));
    addpersonaldetails.addActionListener(this);
    p2.add(addpersonaldetails);
    
    updatepersonaldetails=new JButton("Update personal details");
    updatepersonaldetails.setBounds(0,50,300,50);
    updatepersonaldetails.setBackground(new Color(0,0,102));
    updatepersonaldetails.setForeground(Color.white);
    updatepersonaldetails.setFont(new Font("Tahoma",Font.PLAIN,20));
    updatepersonaldetails.setMargin( new Insets(0,0,0,30));
    updatepersonaldetails.addActionListener(this);
    p2.add(updatepersonaldetails);

     viewpersonaldetails=new JButton("View details");
    viewpersonaldetails.setBounds(0,100,300,50);
    viewpersonaldetails.setBackground(new Color(0,0,102));
    viewpersonaldetails.setForeground(Color.white);
    viewpersonaldetails.setFont(new Font("Tahoma",Font.PLAIN,20));
    viewpersonaldetails.setMargin( new Insets(0,0,0,130));
    viewpersonaldetails.addActionListener(this);
    p2.add(viewpersonaldetails);

    deletepersonaldetails=new JButton("Delete personal details");
    deletepersonaldetails.setBounds(0,150,300,50);
    deletepersonaldetails.setBackground(new Color(0,0,102));
    deletepersonaldetails.setForeground(Color.white);
    deletepersonaldetails.setFont(new Font("Tahoma",Font.PLAIN,20));
    deletepersonaldetails.setMargin( new Insets(0,0,0,40));
    deletepersonaldetails.addActionListener(this);
    p2.add(deletepersonaldetails);

    checkpackages=new JButton("Check Packages");
    checkpackages.setBounds(0,200,300,50);
    checkpackages.setBackground(new Color(0,0,102));
    checkpackages.setForeground(Color.white);
    checkpackages.setFont(new Font("Tahoma",Font.PLAIN,20));
    checkpackages.setMargin( new Insets(0,0,0,100));
    checkpackages.addActionListener(this);
    p2.add(checkpackages);

    bookpackages=new JButton("Book Packages");
    bookpackages.setBounds(0,250,300,50);
    bookpackages.setBackground(new Color(0,0,102));
    bookpackages.setForeground(Color.white);
    bookpackages.setFont(new Font("Tahoma",Font.PLAIN,20));
    bookpackages.setMargin( new Insets(0,0,0,100));
    bookpackages.addActionListener(this);
    p2.add(bookpackages);

    viewpackage=new JButton("View packages");
    viewpackage.setBounds(0,300,300,50);
    viewpackage.setBackground(new Color(0,0,102));
    viewpackage.setForeground(Color.white);
    viewpackage.setFont(new Font("Tahoma",Font.PLAIN,20));
    viewpackage.setMargin( new Insets(0,0,0,100));
    viewpackage.addActionListener(this);
        p2.add(viewpackage);

    viewhotels=new JButton("View hotels");
    viewhotels.setBounds(0,350,300,50);
    viewhotels.setBackground(new Color(0,0,102));
    viewhotels.setForeground(Color.white);
    viewhotels.setFont(new Font("Tahoma",Font.PLAIN,20));
    viewhotels.setMargin( new Insets(0,0,0,130));
    viewhotels.addActionListener(this);
    p2.add(viewhotels);

     bookhotel=new JButton("Book Hotel");
    bookhotel.setBounds(0,400,300,50);
    bookhotel.setBackground(new Color(0,0,102));
    bookhotel.setForeground(Color.white);
    bookhotel.setFont(new Font("Tahoma",Font.PLAIN,20));
    bookhotel.setMargin( new Insets(0,0,0,130));
    bookhotel.addActionListener(this);
    p2.add(bookhotel);

   viewbookedhotel=new JButton("View Booked Hotel");
    viewbookedhotel.setBounds(0,450,300,50);
    viewbookedhotel.setBackground(new Color(0,0,102));
    viewbookedhotel.setForeground(Color.white);
    viewbookedhotel.setFont(new Font("Tahoma",Font.PLAIN,20));
    viewbookedhotel.setMargin( new Insets(0,0,0,70));
    viewbookedhotel.addActionListener(this);
    p2.add(viewbookedhotel);

    destinations=new JButton("Destinations");
    destinations.setBounds(0,500,300,50);
    destinations.setBackground(new Color(0,0,102));
    destinations.setForeground(Color.white);
   destinations.setFont(new Font("Tahoma",Font.PLAIN,20));
    destinations.setMargin( new Insets(0,0,0,130));
    destinations.addActionListener(this);
    p2.add(destinations);

    payments=new JButton("Payment");
    payments.setBounds(0,550,300,50);
    payments.setBackground(new Color(0,0,102));
    payments.setForeground(Color.white);
    payments.setFont(new Font("Tahoma",Font.PLAIN,20));
    payments.setMargin( new Insets(0,0,0,130));
    payments.addActionListener(this);
    p2.add(payments);

    calculators=new JButton("Calculator");
    calculators.setBounds(0,600,300,50);
    calculators.setBackground(new Color(0,0,102));
    calculators.setForeground(Color.white);
    calculators.setFont(new Font("Tahoma",Font.PLAIN,20));
    calculators.setMargin( new Insets(0,0,0,130));
    calculators.addActionListener(this);
    p2.add(calculators);

    notepad=new JButton("Notepad");
    notepad.setBounds(0,650,300,50);
    notepad.setBackground(new Color(0,0,102));
    notepad.setForeground(Color.white);
    notepad.setFont(new Font("Tahoma",Font.PLAIN,20));
    notepad.setMargin( new Insets(0,0,0,130));
    notepad.addActionListener(this);
    p2.add(notepad);

    about=new JButton("About");
    about.setBounds(0,700,300,50);
    about.setBackground(new Color(0,0,102));
    about.setForeground(Color.white);
    about.setFont(new Font("Tahoma",Font.PLAIN,20));
    about.setMargin( new Insets(0,0,0,130));
    about.addActionListener(this);
    p2.add(about);

    ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
    Image i5=i4.getImage().getScaledInstance(1650, 1000, Image.SCALE_DEFAULT);
    ImageIcon i6=new ImageIcon(i5);
    JLabel image=new JLabel(i6);
    image.setBounds(0,0,1650,1000);
    add(image);

    JLabel text=new JLabel("Travel And Tourism");
    text.setBounds(400,70,1000,70);
    text.setFont(new Font("Tahoma",Font.BOLD,50));
    text.setForeground(Color.yellow);
    image.add(text);

    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==addpersonaldetails){
      new Addcustomer(username);
    }else if(ae.getSource()==viewpersonaldetails){
      new Viewcustomer(username);
     }else if(ae.getSource()==updatepersonaldetails){
      new Updatecustomer(username);
     }else if(ae.getSource()==checkpackages){
      new Checkpackage();
     }else if(ae.getSource()==bookpackages){
      new Bookpackage(username);
     }else if(ae.getSource()==viewpackage){
      new Viewpackage(username);
     }else if(ae.getSource()==viewhotels){
      new Checkhotel();
     }else if(ae.getSource()==destinations){
      new Destination();
     }else if(ae.getSource()==bookhotel){
      new Bookhotel(username);
     }else if(ae.getSource()==viewbookedhotel){
      new Viewbookedhotel(username);
     }else if(ae.getSource()==payments){
      new Payment(username);
     }else if(ae.getSource()==calculators){
      try {
          Runtime.getRuntime().exec("calc.exe");
      } catch (Exception e) {
        e.printStackTrace();
      }
     }else if(ae.getSource()==notepad){
      try {
          Runtime.getRuntime().exec("notepad.exe");
      } catch (Exception e) {
        e.printStackTrace();
      }
     }else if(ae.getSource()==about){
      new About();
     }else{
      new Deletedetail(username);
     }
  }
  public static void main(String[] args) {
      new Dashboard("","Customer");
  }  
}
