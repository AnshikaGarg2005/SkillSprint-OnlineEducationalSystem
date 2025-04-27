package A;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class update extends JFrame {
    public void updatecourse(){
    	String provider_id="Provider A";
        setSize(300,200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        JLabel l2=new JLabel("Enter course id: ");
        JTextField t1=new JTextField();
        add(l2);
        add(t1);
        t1.setPreferredSize(new Dimension(150,30));
        JLabel l1=new JLabel("What you want to change:-");
        String[] options={"Name","Hours","Price","Description"};
        JComboBox<String> combo=new JComboBox<>(options);
        JButton btn=new JButton("Submit");
        JButton btn2=new JButton("Go Back");
        add(l1);
        add(combo);
        add(btn);
        add(btn2);
        btn2.addActionListener(e->{
        	dispose();
        	
        });
        btn.addActionListener(e->{
        	if(t1.getText().isEmpty()) {
        		JOptionPane.showMessageDialog(this, "course id is empty");
        		return;
        	}
        	try {
        		int courseid=Integer.parseInt(t1.getText());
				Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				String query="SELECT * FROM course WHERE courseid = ?";
				PreparedStatement stmt=con.prepareStatement(query);
				stmt.setInt(1, courseid);
				ResultSet rs=stmt.executeQuery();
				if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "Course not found");
                    return;
                }
				if(rs.getString("provider_name")==null  || !rs.getString("provider_name").equals(provider_id)) {
					JOptionPane.showMessageDialog(this, "this is not your course");
					return;
				}
				String optionselected= (String) combo.getSelectedItem();
	            dispose();
	            if(optionselected.equals("Name")){
	                JFrame frame1=new JFrame();
	                frame1.setSize(400,200);
	                frame1.setLayout(new FlowLayout());
	                JLabel label1=new JLabel("Enter the new Name of your course: ");
	                frame1.add(label1);
	                JTextField text1=new JTextField();
	                text1.setPreferredSize(new Dimension(100,30));
	                frame1.add(text1);
	                JButton btn3=new JButton("Update");
	                JButton btn4=new JButton("Go Back");
	                btn3.addActionListener(e2->{
	                	String query2="update course set name=? where courseid=?";
	                	try {
							PreparedStatement stmt2=con.prepareStatement(query2);
							stmt2.setString(1, text1.getText());
							stmt2.setInt(2, courseid);
							stmt2.executeUpdate();
							JOptionPane.showMessageDialog(this, "Updated Successfully");
							frame1.dispose();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	
	                			
	                });
	                frame1.add(btn3);
	                frame1.add(btn4);
	                btn4.addActionListener(f->{
	                    frame1.dispose();
	                    update x=new update();
	                    x.updatecourse();
	                });
	                frame1.setLocationRelativeTo(null);
	                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame1.setVisible(true);
	            }
	            else if(optionselected.equals("Hours")){
	                JFrame frame1=new JFrame();
	                frame1.setSize(400,200);
	                frame1.setLayout(new FlowLayout());
	                JLabel label1=new JLabel("Enter the new course Hours: ");
	                frame1.add(label1);
	                JTextField text1=new JTextField();
	                text1.setPreferredSize(new Dimension(150,30));
	                frame1.add(text1);
	                JButton btn3=new JButton("Update");
	                JButton btn4=new JButton("Go Back");
	                frame1.add(btn3);
	                frame1.add(btn4);
	                btn3.addActionListener(e2->{
	                	String query2="update course set hours=? where courseid=?";
	                	try {
							PreparedStatement stmt2=con.prepareStatement(query2);
							stmt2.setInt(1, Integer.parseInt(text1.getText()));
							stmt2.setInt(2, courseid);
							stmt2.executeUpdate();
							JOptionPane.showMessageDialog(this, "Updated Successfully");
							frame1.dispose();
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
	                	
	                			
	                });
	                btn4.addActionListener(f->{
	                    frame1.dispose();
	                    update x=new update();
	                    x.updatecourse();
	                });
	                frame1.setLocationRelativeTo(null);
	                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame1.setVisible(true);
	            }
	            else if(optionselected.equals("Price")){
	                JFrame frame1=new JFrame();
	                frame1.setSize(400,200);
	                frame1.setLayout(new FlowLayout());
	                JLabel label1=new JLabel("Enter the new course Price: ");
	                frame1.add(label1);
	                JTextField text1=new JTextField();
	                text1.setPreferredSize(new Dimension(150,30));
	                frame1.add(text1);
	                JButton btn3=new JButton("Update");
	                JButton btn4=new JButton("Go Back");
	                frame1.add(btn3);
	                frame1.add(btn4);
	                btn3.addActionListener(e2->{
	                	String query2="update course set price=? where courseid=?";
	                	try {
							PreparedStatement stmt2=con.prepareStatement(query2);
							stmt2.setDouble(1, Double.parseDouble(text1.getText()));
							stmt2.setInt(2, courseid);
							stmt2.executeUpdate();
							JOptionPane.showMessageDialog(this, "Updated Successfully");
							frame1.dispose();
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
	                	
	                			
	                });
	                btn4.addActionListener(f->{
	                    frame1.dispose();
	                    update x=new update();
	                    x.updatecourse();
	                });
	                frame1.setLocationRelativeTo(null);
	                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame1.setVisible(true);
	            }
	            else if(optionselected.equals("Description")){
	                JFrame frame1=new JFrame();
	                frame1.setSize(400,200);
	                frame1.setLayout(new FlowLayout());
	                JLabel label1=new JLabel("Enter the new course Description: ");
	                frame1.add(label1);
	                JTextArea text1=new JTextArea();
	                text1.setPreferredSize(new Dimension(350,60));
	                frame1.add(text1);
	                JButton btn3=new JButton("Update");
	                JButton btn4=new JButton("Go Back");
	                frame1.add(btn3);
	                frame1.add(btn4);
	                btn3.addActionListener(e2->{
	                	String query2="update course set description=? where courseid=?";
	                	try {
							PreparedStatement stmt2=con.prepareStatement(query2);
							stmt2.setString(1, text1.getText());
							stmt2.setInt(2, courseid);
							stmt2.executeUpdate();
							JOptionPane.showMessageDialog(this, "Updated Successfully");
							frame1.dispose();
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
	                	
	                			
	                });
	                btn4.addActionListener(f->{
	                    frame1.dispose();
	                    update x=new update();
	                    x.updatecourse();
	                });
	                frame1.setLocationRelativeTo(null);
	                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame1.setVisible(true);
	                con.close();
	            }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				System.out.print("SQL Exception");
			}
        	
        });
     
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

