package admin;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Next {
    JFrame frame;
    JTextField C_IdField, P_IdField;
    JTextArea outputArea;
    Connection conn;

    public Next() {
        frame = new JFrame("Admin Panel");
        frame.setSize(1100, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(Color.BLACK);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(9, 1, 10, 10));
        JLabel Label = new JLabel("ADMIN PANEL");
        Label.setFont(new Font("Arial", Font.BOLD, 32));
        Label.setForeground(Color.WHITE);
        header.add(Label);
        frame.add(header, BorderLayout.NORTH);

        JButton analyticsBtn = new JButton("DASHBOARD");
        JLabel courseLabel = new JLabel("Course ID:");
        C_IdField = new JTextField();
        JButton CourseBtn = new JButton("Delete Course");
        JLabel providerLabel = new JLabel("Provider ID:");
        P_IdField = new JTextField();
        JButton banProviderBtn = new JButton("Ban Provider");
        JButton providerDetailsBtn = new JButton("View Provider Details");
        JButton courseDetailsBtn = new JButton("View Course Details");

        inputPanel.add(analyticsBtn);
        inputPanel.add(courseLabel);
        inputPanel.add(C_IdField);
        inputPanel.add(CourseBtn);
        inputPanel.add(providerLabel);
        inputPanel.add(P_IdField);
        inputPanel.add(banProviderBtn);
        inputPanel.add(providerDetailsBtn);
        inputPanel.add(courseDetailsBtn);

        mainPanel.add(inputPanel);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane Scroll = new JScrollPane(outputArea);
        mainPanel.add(Scroll);

        frame.add(mainPanel, BorderLayout.CENTER);

        analyticsBtn.addActionListener(e -> showAnalytics());
        providerDetailsBtn.addActionListener(e -> showProviderDetails());
        courseDetailsBtn.addActionListener(e -> showCourseDetails());
        CourseBtn.addActionListener(e -> deleteCourse());
        banProviderBtn.addActionListener(e -> banProvider());

        frame.setVisible(true);
        connectToMySQL();
    }

    private void connectToMySQL() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
            System.out.println("Connected to MySQL");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "MySQL Connection Failed");
        }
    }

    private void showProviderDetails() {
        try {
            String result = "============================ PROVIDER DETAILS ==============================\n\n";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Logins WHERE role = 'PROVIDER'");
            while (rs.next()) {
                result += "ID: " + rs.getInt("id") + "\n";
                result += "Role: " + rs.getString("role") + "\n";
                result += "Name: " + rs.getString("name") + "\n";
                result += "Email: " + rs.getString("email") + "\n";
                result += "Phone: " + rs.getString("phone") + "\n";
                result += "Qualification: " + rs.getString("qualification") + "\n";
                result += "Password: " + rs.getString("password") + "\n";
                result += "Status: " + rs.getString("status") + "\n";
                result += "\n---------------------------------------------------------------------------------------------------------------------------------------\n";
            }
            outputArea.setText(result);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching provider details.");
        }
    }

    private void showCourseDetails() {
        try {
            String result = "=============================== COURSE DETAILS ==============================\n\n";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Courses");
            while (rs.next()) {
                result += "Course ID: " + rs.getString("courseid") + "\n"
                        + "Title: " + rs.getString("name") + "\n"
                        + "Price: " + rs.getDouble("price") + "\n"
                        + "Description: " + rs.getString("description") + "\n"
                        + "Duration: " + rs.getString("hours") + " hours\n\n"
                        + "---------------------------------------------------------------------------------------------------------------------------------------\n";
            }
            outputArea.setText(result);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching course details.");
        }
    }

    private void deleteCourse() {
        String courseId = C_IdField.getText().trim();
        if (courseId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a Course ID to delete.");
            return;
        }
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Courses WHERE courseid = ?");
            pstmt.setString(1, courseId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) {
                outputArea.setText("Course ID " + courseId + " not found!");
                return;
            }
            String result = "";
            result += "Course ID: " + rs.getString("courseid") + "\n";
            result += "Provider Name: " + rs.getString("provider_name") + "\n";
            result += "Price: " + rs.getDouble("price") + "\n";

            Statement stmt = conn.createStatement();
            stmt.execute(" SET FOREIGN_KEY_CHECKS=0");

            PreparedStatement pstDeleteReport = conn.prepareStatement("DELETE FROM Reports WHERE course_id = ?");
            pstDeleteReport.setString(1, courseId); // corrected to course_id
            pstDeleteReport.executeUpdate();

            PreparedStatement pstDeleteCourse = conn.prepareStatement("DELETE FROM Courses WHERE courseid = ?");
            pstDeleteCourse.setString(1, courseId);
            int rows = pstDeleteCourse.executeUpdate();

            stmt.execute("SET FOREIGN_KEY_CHECKS=1");

            if (rows > 0) {
                outputArea.setText("Course Deleted Successfully!\n\nDeleted Course Details:\n" + result);
                JOptionPane.showMessageDialog(frame, "Course and its reports deleted successfully!");
            } else {
                outputArea.setText("Course ID " + courseId + " not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error deleting course.\n");
        }
    }


    private void banProvider() {
        String providerId = P_IdField.getText().trim();
        if (providerId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a Provider ID to ban.");
            return;
        }
        try {
            PreparedStatement pst = conn.prepareStatement("UPDATE Logins SET status = 'BANNED' WHERE id = ? AND role = 'PROVIDER'");
            pst.setInt(1, Integer.parseInt(providerId));
            int rows = pst.executeUpdate();
            if (rows > 0) {
                outputArea.setText("Provider ID " + providerId + " banned successfully!");
            } else {
                outputArea.setText("Provider ID " + providerId + " not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error banning provider.");
        }
    }

    private void showAnalytics() {
        int providerCount = 0, customerCount = 0, adminCount = 0, courseCount = 0;
        String result = "================================= ANALYTICS ==================================\n\n";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT role, COUNT(*) AS total FROM Logins GROUP BY role");
            while (rs.next()) {
                String role = rs.getString("role").toUpperCase();
                int count = rs.getInt("total");
                switch (role) {
                    case "PROVIDER":
                        providerCount = count;
                        break;
                    case "CUSTOMER":
                        customerCount = count;
                        break;
                    case "ADMIN":
                        adminCount = count;
                        break;
                }
            }
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM Courses");
            if (rs.next()) {
                courseCount = rs.getInt("total");
            }
            result += "Total Providers : " + providerCount + "\n";
            result += "Total Customers : " + customerCount + "\n";
            result += "Total Admins : " + adminCount + "\n";
            result += "Total Courses : " + courseCount + "\n\n";

            result += "================================= REPORTS ====================================\n\n";
            rs = stmt.executeQuery("SELECT course_id, no_of_reports, provider_id FROM Reports");
            while (rs.next()) {
                result += "Course ID: " + rs.getString("course_id") + " provided by (Provider ID: " + rs.getInt("provider_id")
                        + ") has reports " + rs.getInt("no_of_reports") + "\n";
            }
            outputArea.setText(result);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching analytics.");
        }
    }
}

public class NextPage {
    public static void main(String[] args) {
        Next n = new Next();
    }
}
