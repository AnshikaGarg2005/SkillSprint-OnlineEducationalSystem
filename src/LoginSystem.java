import java.awt.*;
import java.sql.*;
import javax.swing.*;
import A.provGui;
import admin.page1;



public class LoginSystem extends JFrame {

    // Updated JDBC URL with timezone and SSL parameters
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/project?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    private CardLayout cardLayout;
    private JPanel    mainPanel;

    public LoginSystem() {
        // 1) Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "MySQL JDBC Driver not found. Add it to your classpath.",
                    "Driver Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        // 2) Frame setup
        setTitle("Login System");
        setSize(450, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 3) CardLayout for login / register panels
        cardLayout = new CardLayout();
        mainPanel  = new JPanel(cardLayout);
        mainPanel.add(createLoginPanel(),  "login");
        mainPanel.add(createAccountPanel(),"create");
        add(mainPanel);

        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Logo at top
        JLabel logo = new JLabel(new ImageIcon("logo.jpg"), SwingConstants.CENTER);

        // Form in center
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Welcome to SkillSprint", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        form.add(title, gbc);

        // Email
        gbc.gridwidth=1;
        gbc.gridy++;
        form.add(new JLabel("Email:"), gbc);
        gbc.gridx=1;
        JTextField emailField = new JTextField(20);
        form.add(emailField, gbc);

        // Password
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx=1;
        JPasswordField passField = new JPasswordField(20);
        form.add(passField, gbc);

        // Buttons
        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2;
        JButton loginBtn  = new JButton("Login");
        form.add(loginBtn, gbc);
        gbc.gridy++;
        JButton toCreate  = new JButton("Create Account");
        form.add(toCreate, gbc);

        // Login action
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pwd   = new String(passField.getPassword());
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = conn.prepareStatement(
                         "SELECT name, role FROM Logins WHERE email=? AND password=?")) {

                ps.setString(1, email);
                ps.setString(2, pwd);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role").toUpperCase();
                    JOptionPane.showMessageDialog(this,
                            "Login Successful! Welcome " + rs.getString("name"));
                    if ("ADMIN".equals(role)) {
                        admin.page1.main(null);
                    }
                    if ("PROVIDER".equals(role)) {
                        A.provGui.main(null);
                    }
                    if ("CUSTOMER".equals(role)) {
                        page.main(null);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Switch to registration panel
        toCreate.addActionListener(e -> cardLayout.show(mainPanel,"create"));

        panel.add(logo, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAccountPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Logo at top
        JLabel logo = new JLabel(new ImageIcon("logo.png"), SwingConstants.CENTER);

        // Form in center
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill   = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Create Account", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        form.add(title, gbc);

        // Name
        gbc.gridwidth=1;
        gbc.gridy++;
        form.add(new JLabel("Name:"), gbc);
        gbc.gridx=1;
        JTextField nameField = new JTextField(20);
        form.add(nameField, gbc);

        // Qualification
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Qualification:"), gbc);
        gbc.gridx=1;
        JTextField qualField = new JTextField(20);
        form.add(qualField, gbc);

        // Email
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Email:"), gbc);
        gbc.gridx=1;
        JTextField emailField = new JTextField(20);
        form.add(emailField, gbc);

        // Mobile
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Mobile No:"), gbc);
        gbc.gridx=1;
        JTextField mobileField = new JTextField(20);
        form.add(mobileField, gbc);

        // Password
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Password:"), gbc);
        gbc.gridx=1;
        JPasswordField passField = new JPasswordField(20);
        form.add(passField, gbc);

        // Role
        gbc.gridx=0; gbc.gridy++;
        form.add(new JLabel("Role:"), gbc);
        gbc.gridx=1;
        JComboBox<String> roleBox = new JComboBox<>(new String[]{"Customer","Provider"});
        form.add(roleBox, gbc);

        // Register & Back
        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2;
        JButton registerBtn = new JButton("Register");
        form.add(registerBtn, gbc);
        gbc.gridy++;
        JButton backBtn     = new JButton("Back to Login");
        form.add(backBtn, gbc);

        // Register action
        registerBtn.addActionListener(e -> {
            String name  = nameField.getText();
            String qual  = qualField.getText();
            String email = emailField.getText();
            String mob   = mobileField.getText();
            String pwd   = new String(passField.getPassword());
            String role  = (String)roleBox.getSelectedItem();

            String sql = "INSERT INTO Logins (name,qualification,email,phone,password,role) VALUES (?,?,?,?,?,?)";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, name);
                ps.setString(2, qual);
                ps.setString(3, email);
                ps.setString(4, mob);
                ps.setString(5, pwd);
                ps.setString(6, role);
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int id = keys.getInt(1);
                    // Format as 4-digit zero-padded string (e.g., 0001, 0002, ...)
                    String uid = String.format("%04d", id);
                    JOptionPane.showMessageDialog(this,
                            "Account created! Your ID: " + uid);
                }
                cardLayout.show(mainPanel, "login");

            } catch (SQLIntegrityConstraintViolationException dup) {
                JOptionPane.showMessageDialog(this,
                        "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back to login
        backBtn.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        panel.add(logo, BorderLayout.NORTH);
        panel.add(form, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginSystem::new);
    }
}

