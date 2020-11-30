package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forgotPassGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel title, id, email;
    private static JTextField id_t, email_t;
    private static JButton submit;


    public void fpassWindow() {

        // Initialize all the required components for register GUI
        frame = new JFrame("Forgot Password");
        frame.setSize(500, 350);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        title = new JLabel("Forgot Password?");
        title.setForeground(new Color(0, 128, 255));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(50, 50, 350, 40);
        panel.add(title);

        id = new JLabel("ID");
        id.setFont(new Font("DIALOG", Font.BOLD, 16));
        id.setBounds(50,120,150,25);
        panel.add(id);
        id_t = new JTextField();
        id_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        id_t.setBounds(150,120,180,25);
        panel.add(id_t);

        email = new JLabel("Email");
        email.setFont(new Font("DIALOG", Font.BOLD, 16));
        email.setBounds(50,170,150,25);
        panel.add(email);
        email_t = new JTextField();
        email_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        email_t.setBounds(150,170,180,25);
        panel.add(email_t);

        submit = new JButton("Submit");
        submit.setFont(new Font("DIALOG", Font.BOLD, 14));
        submit.setBounds(240,240,90,30);
        submit.addActionListener(this);
        panel.add(submit);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            //submit reset email
        }
    }
}
