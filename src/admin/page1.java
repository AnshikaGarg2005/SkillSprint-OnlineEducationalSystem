package admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class page1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Welcome Admin Panel");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
        ImageIcon icon = new ImageIcon("/Users/anshikagarg/Desktop/Project/Skillsprint-logo-horizontal-1-1.png");
        Image resizedImage = icon.getImage().getScaledInstance(1000, 500, Image.SCALE_SMOOTH); // Resize image
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);

        JLabel label = new JLabel("Welcome to Admin Panel");
        label.setFont(new Font("SansSerif", Font.BOLD, 50));
        label.setForeground(Color.BLUE);

        JButton nextButton = new JButton("Next");
        nextButton.setPreferredSize(new Dimension(100, 50));
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Next n=new Next();
            }
        });

        frame.add(imageLabel);
        frame.add(label);
        frame.add(nextButton);
        frame.setVisible(true);
    }
}
