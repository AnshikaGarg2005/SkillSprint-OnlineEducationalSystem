package A;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
class create extends JFrame {
	public JTextField t1,t2,t3;
	public JTextArea t4;
    public void createcourse(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel main=new JPanel(new GridLayout(1,1));
        JPanel panel1=new JPanel();
        JPanel image=new JPanel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/A/assets/course.jpg"));
        JLabel imageLabel = new JLabel(icon);

        
        Image img = icon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        image.add(imageLabel);
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
        JLabel l1=new JLabel("Enter your Course Details");
        panel1.add(l1);
        JPanel btn=new JPanel(new GridLayout(5,2,20,20));
        JLabel name=new JLabel("Name of the course");
        t1=new JTextField();
        btn.add(name);
        btn.add(t1);
        JLabel hour=new JLabel("Total Course Hours");
        t2=new JTextField();
        btn.add(hour);
        btn.add(t2);
        JLabel price=new JLabel("Enter course price");
        t3=new JTextField();
        btn.add(price);
        btn.add(t3);
        JLabel desc=new JLabel("Describe your course");
        t4=new JTextArea();
        btn.add(desc);
        btn.add(t4);
        JButton btn1=new JButton("ADD");
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.white);
        btn.add(btn1);
        JButton btn2=new JButton("GO BACK");
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.white);
        btn2.addActionListener(e->{
            dispose();
            new myframe();
        });
        btn.add(btn2);
        panel1.add(btn);
        main.add(panel1);
        main.add(image);
        add(main);
        btn1.addActionListener(e->{
        	addcourse();
        });
        setVisible(true);
    }
    void addcourse() {
    	String name=t1.getText();
    	String hourstxt=t2.getText();
    	String pricetxt=t3.getText();
    	String description=t4.getText();
    	String provider_name="Provider A";
    	
    	if(name.isEmpty()||hourstxt.isEmpty()||pricetxt.isEmpty()||description.isEmpty()) {
    		JOptionPane.showMessageDialog(this, "All field must be filled");
    		return;
    	}
    	try {
    		int hours=Integer.parseInt(hourstxt);
    		double price=Double.parseDouble(pricetxt);
    		
    		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
    		String query="Insert into course (provider_name,name,hours,price,description) values (?,?,?,?,?)";
    		PreparedStatement stmt = con.prepareStatement(query);
            
            // Setting parameters in the PreparedStatement
            stmt.setString(1, provider_name);
            stmt.setString(2, name);
            stmt.setInt(3, hours);
            stmt.setDouble(4, price);
            stmt.setString(5, description);
            stmt.executeUpdate();
    		stmt.close();
    		con.close();
    		t1.setText("");
    		t2.setText("");
    		t3.setText("");
    		t4.setText("");
    		 JOptionPane.showMessageDialog(this, "Course added successfully!");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for Hours and Price.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding course: " + ex.getMessage());
    	}
    }
}

