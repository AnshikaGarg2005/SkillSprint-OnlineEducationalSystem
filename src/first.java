import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Taskbar.State;
import java.sql.*;
import java.util.StringTokenizer;

class utility {
    int calc_w;
    int calc_h;

    String dataBaseName = "oops";
    String usn = "root";
    String pswd = "@Happyhead1423";

    utility() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.calc_w = screenSize.width;
        this.calc_h = screenSize.height;
    } 

    boolean checkIdAvailable(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from courses");
            int check = 0;

            while(rs.next()) {
                if(rs.getInt(1) == Integer.parseInt(id)) {
                    check = 1;
                    break;
                }else {
                    check = 0;
                }
            }
            con.close();
            if(check == 1) {
                return true;
            }else {
                return false;
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    void addCourse(String csid,int currentLogin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();
            
            String query = "UPDATE owned SET courses = CASE WHEN courses IS NULL OR courses = '' THEN '" + csid + "' ELSE CONCAT(courses, '," + csid + "') END WHERE cstid = " + currentLogin;
            stmt.executeUpdate(query);

            con.close();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    boolean checkCourseStatus(String csid, int currentLogin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();
            int check = 0;

            ResultSet rs = stmt.executeQuery("select * from owned where cstid = " + currentLogin);
            String[] temp = new String[10];

            while(rs.next()) {
                if(rs.getString(2) == null) {
                    return false;
                }else {
                    int index = 0;
                    StringTokenizer st = new StringTokenizer(rs.getString(2), ",");
                    while(st.hasMoreTokens()) {
                        temp[index] = st.nextToken();
                        index += 1;
                    }   
                }    
            }

            con.close();

            for(String a: temp) {
                if(a != null) {
                    if(a.equals(csid)){
                        check = 1;
                        break;
                    }
                }
            }

            if (check == 1) {
                return true;
            }else {
                return false;
            }
            
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }  
        return true;
    }

    void buyCourse(JTextField csID, JTextField upiID, JFrame mainFrame, int currentLogin) {
        if(!csID.getText().equals("") && !upiID.getText().equals("")) {
            if(checkIdAvailable(csID.getText())) {
                if(checkCourseStatus(csID.getText(), currentLogin) == false) {
                    addCourse(csID.getText(), currentLogin);
                    JOptionPane.showMessageDialog(mainFrame, "Course with Course ID " + csID.getText().toUpperCase() + " added to your subscription.");
                }else {
                    JOptionPane.showMessageDialog(mainFrame, "You Already own this course.");
                }
                // System.out.println("true");
            }else {
                JOptionPane.showMessageDialog(mainFrame, "Please enter a valid course id.");
            }
            csID.setText(null);
        }else {
            JOptionPane.showMessageDialog(mainFrame, "Please enter all required details.");
        }
    }

    void displayMainScreen(JPanel toRemove,JPanel toDisplay) {
        toRemove.setVisible(false);
        toDisplay.setVisible(true);
    }

    String getAllCourses() {
        try {
            String result = "";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from courses");
            
            while(rs.next()) {
                result += ("\n ID: "+rs.getInt(1)+
                "\n Provider Name: "+rs.getString(2)+
                "\n Course Name: "+rs.getString(3)+
                "\n Course Hours: "+rs.getInt(4)+
                "\n Price: "+rs.getInt(5)+
                "\n Description: "+rs.getString(6)+
                "\n Ratings: "+rs.getDouble(7) + "\n");
            }
            con.close();
            return result;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    void viewCourses(JFrame mainFrame, JPanel toRemove1, int crntLogin) {
        toRemove1.setVisible(false);

        JPanel mid2 = new JPanel(new GridLayout(1,2));
        mid2.setPreferredSize(new Dimension(calc_w,calc_h));
        
        JPanel midLft = new JPanel(new FlowLayout(FlowLayout.CENTER,0,15));
        midLft.setPreferredSize(new Dimension(calc_w/2,calc_h));
        // midLft.setBackground(Color.red);

        JLabel headLbl = new JLabel("Course Details");
        headLbl.setFont(new Font("Times New Roman",Font.BOLD,18));
        JTextArea detailsContainer = new JTextArea(35,60);
        // detailsContainer.setPreferredSize(new Dimension(calc_w/3+200,calc_h-250));
        detailsContainer.setEditable(false);
        detailsContainer.setFont(new Font("Times New Roman",Font.BOLD,16));
        // detailsContainer.setText("\n  Course ID: \n  Course Name: \n  Description: \n  Provider Name: ");
        detailsContainer.setText(getAllCourses());
        JScrollPane scrollPane = new JScrollPane(detailsContainer);
        
        midLft.add(headLbl);
        // midLft.add(detailsContainer);
        midLft.add(scrollPane);
        detailsContainer.setCaretPosition(0);

        JPanel midRgt = new JPanel(new FlowLayout(FlowLayout.CENTER,0,15));
        midRgt.setPreferredSize(new Dimension(calc_w/2,calc_h));

        JLabel headLbl2 = new JLabel("Buy Course");
        headLbl2.setFont(new Font("Times New Roman",Font.BOLD,18));
        
        JPanel n1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        n1.setPreferredSize(new Dimension(700,100));

        JLabel csLbl = new JLabel("Enter the Course ID: ");
        csLbl.setFont(new Font("Ariel",Font.ROMAN_BASELINE,16));
        JTextField csId = new JTextField();
        csId.setPreferredSize(new Dimension(680,50));
        csId.setFont(new Font("Ariel",Font.BOLD + Font.ITALIC,16));
        
        n1.add(csLbl);
        n1.add(csId);
        
        JPanel n2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        n2.setPreferredSize(new Dimension(700,100));

        JLabel upiLbl = new JLabel("Enter your UPI ID: ");
        upiLbl.setFont(new Font("Ariel",Font.ROMAN_BASELINE,16));
        JTextField upiId = new JTextField();
        upiId.setPreferredSize(new Dimension(680,50));
        upiId.setFont(new Font("Ariel",Font.BOLD + Font.ITALIC,16));
        
        n2.add(upiLbl);
        n2.add(upiId);

        JPanel n3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        n3.setPreferredSize(new Dimension(700,100));

        JButton buy = new JButton("Buy Course");
        buy.setPreferredSize(new Dimension(300,50));
        buy.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
        buy.setFocusPainted(false);
        buy.addActionListener(e -> 
            buyCourse(csId, upiId, mainFrame, crntLogin)
        );
        // n3.setBackground(Color.red);
        n3.add(buy);
        
        JPanel n4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        n4.setPreferredSize(new Dimension(700,100));

        JButton goback = new JButton("Go Back");
        goback.setPreferredSize(new Dimension(300,50));
        goback.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
        goback.setFocusPainted(false);
        // n3.setBackground(Color.red);
        goback.addActionListener(e -> 
        displayMainScreen(mid2,toRemove1)
        );
        n4.add(goback);

        midRgt.add(headLbl2);
        midRgt.add(n1);
        midRgt.add(n2);
        midRgt.add(n3);
        midRgt.add(n4);

        mid2.add(midLft);
        mid2.add(midRgt);

        mainFrame.add(mid2);
    }
    
    String[] myCourses(int currentLogin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from owned where cstid = " + currentLogin);
            String[] temp = new String[10];

            while(rs.next()) {
                if(rs.getString(2) == null) {
                    
                }else {
                    int index = 0;
                    StringTokenizer st = new StringTokenizer(rs.getString(2), ",");
                    while(st.hasMoreTokens()) {
                        temp[index] = st.nextToken();
                        index += 1;
                    }   
                }    
            }
            
            con.close();

            return temp;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }

        String[] test = {"Empty String"};
        return test;
    }

    String getMyCourses(int currentLogin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from owned where cstid = " + currentLogin);
            String[] temp = new String[10];

            while(rs.next()) {
                if(rs.getString(2) == null) {
                    
                }else {
                    int index = 0;
                    StringTokenizer st = new StringTokenizer(rs.getString(2), ",");
                    while(st.hasMoreTokens()) {
                        temp[index] = st.nextToken();
                        index += 1;
                    }   
                }    
            }
            String result = "";
            for(String a: temp) {
                // System.out.println(a);
                if(a != null) {
                    rs = stmt.executeQuery("select * from courses");
                    while (rs.next()) {
                        if(Integer.parseInt(a) == rs.getInt(1)) {
                            result += ("\n ID: "+rs.getInt(1)+
                            "\n Provider Name: "+rs.getString(2)+
                            "\n Course Name: "+rs.getString(3)+
                            "\n Course Hours: "+rs.getInt(4)+
                            "\n Price: "+rs.getInt(5)+
                            "\n Description: "+rs.getString(6)+
                            "\n Ratings: "+rs.getDouble(7) + "\n");
                        }   
                    }
                }
            }
            con.close();

            return result;
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    String displayData(int csId, int currentLogin) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery("select * from courses where csid = " + csId);
            
            String[] my = myCourses(currentLogin);
            int check = 0;

            for(String a: my) {
                if(a != null) {
                    if(Integer.parseInt(a) == csId) {
                        check = 1;
                        break;
                    }
                }
            }

            if(check == 0) {
                return "Error 401";
            }else {
                while(rs.next()) {
                    return rs.getString(8);
                }
            }

            con.close();

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    void goBack(JPanel p1, JPanel p2, JTextArea p3, JPanel p4) {
        p1.setVisible(true);
        p2.setVisible(true);
        p3.setVisible(false);
        p4.setVisible(false);
    }

    void study(JPanel toRemove1, JPanel toRemove2, JPanel toAddIn, String csid, JFrame mainFrame, int currentLogin) {
        String data = displayData(Integer.parseInt(csid), currentLogin);
        if(data != "Error 401") {
            toRemove1.setVisible(false);
            toRemove2.setVisible(false);

            JTextArea studyArea = new JTextArea(30,60);
            studyArea.setText(data);
            studyArea.setFont(new Font("Times New Roman",Font.BOLD,16));
            studyArea.setEditable(false);

            JPanel bott = new JPanel();
            bott.setPreferredSize(new Dimension(700,80));

            JButton report = new JButton("Report Course");
            report.setPreferredSize(new Dimension(300,50));
            report.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
            report.setFocusPainted(false);
            report.addActionListener(e -> 
                reportACourse(csid)
            );
            
            JButton gb = new JButton("Go Back");
            gb.setPreferredSize(new Dimension(300,50));
            gb.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
            gb.setFocusPainted(false);
            gb.addActionListener(e -> 
                goBack(toRemove1, toRemove2, studyArea, bott)
            );
            
            bott.add(report);
            bott.add(gb);
            
            toAddIn.add(studyArea);
            toAddIn.add(bott);

        }else {
            JOptionPane.showMessageDialog(mainFrame, "You do not own this course yet.");
        }   
    }

    void addReport(JFrame frm, String csid) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dataBaseName, usn, pswd);
            Statement stmt = con.createStatement();

            String query = "UPDATE reports SET reports = reports + 1 WHERE csid = " + Integer.parseInt(csid);

            stmt.executeUpdate(query);
            con.close();

            frm.dispose();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    void reportACourse(String courseID) {
        JFrame frame2 = new JFrame();
        frame2.setBounds(100, 100, 350, 300);
        String[] type = { "Course Content Accuracy", "Incomplete Course", "Course Access Problems", "Course Discrepancy", "Technical Difficulties" };
        
        JLabel lbl = new JLabel("Select type of report: ");
        lbl.setBounds(50, 10, 200, 30);

        JComboBox<String> cb = new JComboBox<>(type);
        cb.setBounds(50, 50, 200, 30);
        
        JButton rp = new JButton("REPORT");
        rp.setBounds(50, 100, 100, 30);
        rp.addActionListener(e -> 
            addReport(frame2, courseID)
        );
        frame2.setLayout(null);
        frame2.add(lbl);
        frame2.add(cb);
        frame2.add(rp);
        frame2.setVisible(true);
    }

    void viewMyCourses(JFrame mainFrame, JPanel toRemove1, int crntLogin) {
        toRemove1.setVisible(false);

        JPanel mid2 = new JPanel(new GridLayout(1,2));
        mid2.setPreferredSize(new Dimension(calc_w,calc_h));
        // mid2.setBackground(Color.blue);

        JPanel midLft = new JPanel(new FlowLayout(FlowLayout.CENTER,0,15));
        midLft.setPreferredSize(new Dimension(calc_w/2,calc_h));
        // midLft.setBackground(Color.red);
        
        JLabel headLbl = new JLabel("Subscribed Courses");
        headLbl.setFont(new Font("Times New Roman",Font.BOLD,18));
        JTextArea detailsContainer = new JTextArea(35,60);
        // detailsContainer.setPreferredSize(new Dimension(calc_w/3+200,calc_h-250));
        detailsContainer.setEditable(false);
        detailsContainer.setFont(new Font("Times New Roman",Font.BOLD,16));
        // detailsContainer.setText("\n  Course ID: \n  Course Name: \n  Description: \n  Provider Name: ");
        detailsContainer.setText(getMyCourses(crntLogin));
        JScrollPane scrollPane = new JScrollPane(detailsContainer);
        
        midLft.add(headLbl);
        // midLft.add(detailsContainer);
        midLft.add(scrollPane);
        detailsContainer.setCaretPosition(0);

        JPanel midRgt = new JPanel(new FlowLayout(FlowLayout.CENTER,0,15));
        midRgt.setPreferredSize(new Dimension(calc_w/2,calc_h));
        // midRgt.setBackground(Color.blue);

        JLabel headLbl2 = new JLabel("Study Course");
        headLbl2.setFont(new Font("Times New Roman",Font.BOLD,18));
        
        JPanel n1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        n1.setPreferredSize(new Dimension(700,100));

        JLabel csLbl = new JLabel("Enter the Course ID: ");
        csLbl.setFont(new Font("Ariel",Font.ROMAN_BASELINE,16));
        JTextField csId = new JTextField();
        
        csId.setPreferredSize(new Dimension(680,50));
        csId.setFont(new Font("Ariel",Font.BOLD + Font.ITALIC,16));
        
        n1.add(csLbl);
        n1.add(csId);
        
        JPanel n2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        n2.setPreferredSize(new Dimension(700,100));
        
        JButton std = new JButton("Study");
        std.setPreferredSize(new Dimension(300,50));
        std.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
        std.setFocusPainted(false);
        std.addActionListener(e -> 
            study(n1, n2, midRgt, csId.getText(), mainFrame, crntLogin)
        );
        
        JButton gb = new JButton("Go Back");
        gb.setPreferredSize(new Dimension(300,50));
        gb.setFont(new Font("Times New Roman",Font.CENTER_BASELINE,18));
        gb.setFocusPainted(false);
        gb.addActionListener(e -> 
        displayMainScreen(mid2,toRemove1)
        );
        
        n2.add(std);
        n2.add(gb);
        midRgt.add(headLbl2);
        midRgt.add(n1);
        midRgt.add(n2);

        mid2.add(midLft);
        mid2.add(midRgt);
        mainFrame.add(mid2);
    }
}

class first {
    public static void main(String[] args) {
        int currentLogin = 1;
        utility u = new utility();
        JFrame frame = new JFrame("Customer Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 100, 1500, 800);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int all_wd = screenSize.width;
        int top_h = (screenSize.height / 10);
        int mid_h = (screenSize.height);
        // System.out.println(screenSize);
        
        //TOP PANEL TO STORE THE LOGO AND NAME OF CUSTOMER
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER,205,10));
        top.setPreferredSize(new Dimension(all_wd, top_h));
        // top.setBackground(Color.BLACK);
        ImageIcon icon = new ImageIcon("logo.png");
        JLabel logoLbl = new JLabel(icon);
        
        JPanel extPnl = new JPanel();
        // extPnl.setBackground(Color.red);
        extPnl.setPreferredSize(new Dimension(all_wd/3,top_h));
        // JButton chngPay = new JButton("Pay Options");
        // chngPay.setFocusPainted(false);

        // chngPay.setPreferredSize(new Dimension(all_wd/10,top_h/3));
        String usrName = "Username Here";
        JLabel nameLbl = new JLabel(usrName);// CHANGE NAME HERE
        nameLbl.setFont(new Font("Times New Roman", Font.BOLD, 18));

        extPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        // extPnl.add(chngPay);
        extPnl.add(nameLbl);

        top.add(logoLbl);
        top.add(extPnl);


        //MID PANEL TO STORE CUSTOMER OPTIONS AND GUI IMAGE
        JPanel mid = new JPanel();
        // mid.setBackground(Color.red);
        mid.setPreferredSize(new Dimension(all_wd, mid_h));
        JPanel midLeft = new JPanel();
        midLeft.setPreferredSize(new Dimension(all_wd/2, mid_h));
        Image orig = new ImageIcon("vector.png").getImage();
        Image resimg = orig.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        
        ImageIcon icon2 = new ImageIcon(resimg);
        JLabel lbl = new JLabel(icon2);
        midLeft.add(lbl);

        JPanel midRight = new JPanel(new FlowLayout(FlowLayout.CENTER,400,100));
        midRight.setPreferredSize(new Dimension(all_wd/2, mid_h));
        
        JButton btn1 = new JButton("View all courses");
        btn1.setPreferredSize(new Dimension(400,100));
        btn1.setFocusPainted(false);
        btn1.addActionListener(e -> 
            u.viewCourses(frame, mid, currentLogin)
        );

        JButton btn2 = new JButton("My Courses");
        btn2.setPreferredSize(new Dimension(400,100));
        btn2.setFocusPainted(false);
        btn2.addActionListener(e -> 
            u.viewMyCourses(frame, mid, currentLogin)
        );

        JButton btn3 = new JButton("Exit");
        btn3.setPreferredSize(new Dimension(400,100));
        btn3.setFocusPainted(false);
        btn3.addActionListener(e -> 
            frame.dispose()
        );

        midRight.add(btn1);
        midRight.add(btn2);
        midRight.add(btn3);

        mid.setLayout(new GridLayout());
        mid.add(midLeft);
        mid.add(midRight);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(new FlowLayout());
        frame.add(top);
        frame.add(mid);
        frame.setVisible(true);
    }    
}