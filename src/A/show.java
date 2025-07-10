package A;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
public class show extends JFrame{
	void showcourse() throws SQLException {
		String provider_name="Provider A";
		setExtendedState(MAXIMIZED_BOTH);
		JPanel main=new JPanel();
		main.setLayout(new BorderLayout());
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		panel2.setLayout(new GridLayout(1,1));
		main.add(panel1,BorderLayout.NORTH);
		main.add(panel2,BorderLayout.CENTER);
		add(main);
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
		String query="Select * from course where provider_name=?";
		PreparedStatement stmt=con.prepareStatement(query);
		stmt.setString(1, provider_name);
		ResultSet rs=stmt.executeQuery();
        JPanel panel4=new JPanel();
        while (rs.next()) {
            // You can create a label for each piece of information in the row
            String courseInfo = "Course ID: " + rs.getInt("courseid") + "\n" +
                    "Course Name: " + rs.getString("name") + "\n" +
                    "Hours: " + rs.getInt("hours") + "\n" +
                    "Price: " + rs.getDouble("price") + "\n" +
                    "Description: " + rs.getString("description") + "\n\n";

            JTextArea courseText = new JTextArea(courseInfo);
            courseText.setWrapStyleWord(true);
            courseText.setLineWrap(true);     
            courseText.setEditable(false);     
            panel4.setLayout(new BoxLayout(panel4,BoxLayout.Y_AXIS));
            panel4.add(courseText);
        }
        

        JButton btn=new JButton("Go Back");
        main.add(btn,BorderLayout.SOUTH);
        btn.addActionListener(e->{
        	dispose();
        	new myframe();
        });
        JScrollPane scrollPane = new JScrollPane(panel4);    
        panel2.add(scrollPane);
        panel2.add(panel3);
        ImageIcon imageicon = new ImageIcon(getClass().getResource("/A/assets/R.png"));
        JLabel img=new JLabel(imageicon);
        Image im=imageicon.getImage().getScaledInstance(800, 600,Image.SCALE_SMOOTH);
        img.setIcon(new ImageIcon(im));
        panel2.add(img);

        rs.close();
        stmt.close();
        con.close();
		
		
		setVisible(true);
	}
}
