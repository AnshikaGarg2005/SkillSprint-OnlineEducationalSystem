package A;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.sql.SQLException;
class myframe extends JFrame{
    myframe(){
        ImageIcon icon=new ImageIcon("assets/skillsprint.jpg");
        JLabel l1=new JLabel("Welcome to our Provider Panel!!!");
        this.setIconImage(icon.getImage());
        this.setSize(800,500);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainpanel=new JPanel();
        JPanel sidepanel=new JPanel();
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));
        JPanel image=new JPanel();
        ImageIcon imageicon = new ImageIcon(getClass().getResource("/A/assets/R.png"));
        JLabel img=new JLabel(imageicon);
        Image im=imageicon.getImage().getScaledInstance(800, 600,Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(im));
        image.add(img);
        sidepanel.add(image);
        JPanel panel1=new JPanel();
        panel1.add(l1);
        JPanel panel2=new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.Y_AXIS));
        JLabel l2=new JLabel();
        l2.setText("Choose what you want to do?");
        JPanel btnform=new JPanel();
        btnform.setLayout(new GridLayout(2,2));
        JButton btn1=new JButton("Create");
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(e -> {
            remove(mainpanel);
            dispose();
            create f=new create();
            f.createcourse();

        });
        JPanel button1=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        button1.add(btn1);
        JButton btn2=new JButton("Update");
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.WHITE);
        btn2.addActionListener(e -> {
            update upd=new update();
            upd.updatecourse();

        });
        JPanel button2=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        button2.add(btn2);
        JButton btn3=new JButton("Delete");
        btn3.setBackground(Color.BLACK);
        btn3.setForeground(Color.white);
        JPanel button3=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        btn3.addActionListener(e->{
        	delete del=new delete();
            del.deletecourse();

        });
        button3.add(btn3);
        JButton btn4=new JButton("Display");
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        btn4.addActionListener(e->{
        	dispose();
        	show s=new show();
        	try {
        		s.showcourse();
        	}catch(SQLException a) {
        		a.printStackTrace();
        	}
        });
        JPanel button4=new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        button4.add(btn4);
        btnform.add(button1);
        btnform.add(button2);
        btnform.add(button3);
        btnform.add(button4);
        panel2.add(l2);
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);

        panel2.add(btnform);
        sidepanel.add(panel2);
        this.setTitle("PROVIDER PANEL");
        mainpanel.setLayout(new BorderLayout());
        mainpanel.add(panel1,BorderLayout.NORTH);
        mainpanel.add(sidepanel,BorderLayout.CENTER);
        this.add(mainpanel);
        this.setVisible(true);

    }
}
class provgui{
    public static void main(String args[]){
        new myframe();
    }
}
