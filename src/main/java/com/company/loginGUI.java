package com.company;

import javax.swing.*;
import java.awt.*;

public class loginGUI {
    private static JFrame frame;
    private static JPanel main_panel;
    private static JLabel title, user_label, pass_label;
    private static JTextField user_text;
    private static JPasswordField pass_text;
    private static JButton signin, register;


    public static void loginWindow() {
        frame = new JFrame("Login");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        main_panel = new JPanel();
        main_panel.setLayout(null);
        frame.add(main_panel);

        title = new JLabel("Laboratory Inventory System");
        title.setForeground(new Color(0,128,255));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(120,80,350,40);
        main_panel.add(title);


        user_label = new JLabel("Username");
        user_label.setFont(new Font("DIALOG", Font.BOLD, 16));
        user_label.setBounds(150,180,150,25);
        main_panel.add(user_label);

        pass_label = new JLabel("Password");
        pass_label.setFont(new Font("DIALOG", Font.BOLD, 16));
        pass_label.setBounds(150,220,150,25);
        main_panel.add(pass_label);

        user_text = new JTextField();
        user_text.setBounds(250,180,150,25);
        main_panel.add(user_text);

        pass_text = new JPasswordField();
        pass_text.setBounds(250,220,150,25);
        main_panel.add(pass_text);

        signin = new JButton("Sign in");
        signin.setBounds(310,290,90,35);
        signin.setFont(new Font("DIALOG", Font.BOLD, 14));
        main_panel.add(signin);

        register = new JButton("register");
        register.setBounds(150,290,60,25);
        register.setFont(new Font("DIALOG", Font.BOLD, 12));
        register.setBorder(BorderFactory.createEmptyBorder());
        main_panel.add(register);

        frame.setVisible(true);
        //frame.dispose();
    }
}
