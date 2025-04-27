package A;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class delete extends JFrame{
	void deletecourse() {
		String provider_name="Provider A";
		setSize(300,200);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		JLabel l1=new JLabel("Enter your course id: ");
		JTextField t1=new JTextField();
		t1.setPreferredSize(new Dimension(250,30));
		add(l1);
		add(t1);
		JButton btn1=new JButton("Delete");
		JButton btn2=new JButton("Go Back");
		btn1.addActionListener(e->{
			try {
				if(t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Course id is empty");
					return;
				}
				int courseid=Integer.parseInt(t1.getText());
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				String query="delete from course where courseid=? and provider_name=?";
				PreparedStatement stmt=con.prepareStatement(query);
				stmt.setInt(1, courseid);
				stmt.setString(2, provider_name);
				int deleted = stmt.executeUpdate();
				if (deleted > 0) {
				    JOptionPane.showMessageDialog(this, "Course deleted successfully!");
				    dispose();
				} else {
				    JOptionPane.showMessageDialog(this, "No matching course found or you are not the provider!");
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		btn2.addActionListener(e->{
			dispose();
		});
		add(btn1);
		add(btn2);
		setVisible(true);
	}
}
