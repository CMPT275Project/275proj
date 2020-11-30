package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class deviceGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel main_panel;
    private static JLabel title, user_label, pass_label;
    private static JTextField user_text;
    private static JPasswordField pass_text;
    private static JButton signin, register;
    private static JCheckBox show_pass;

    public void deviceWindow() {
        //Getting the account type from Login
        loginGUI temp = new loginGUI();
        String actype = temp.getType();

        frame = new JFrame("Main Page - " + actype);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        main_panel = new JPanel();
        main_panel.setLayout(null);
        frame.add(main_panel);

        title = new JLabel("Main");
        title.setForeground(new Color(0, 128, 255));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(120, 80, 350, 40);
        main_panel.add(title);


        frame.setVisible(true);
        //frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
