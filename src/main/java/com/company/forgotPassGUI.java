package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forgotPassGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel title, uname, email;
    private static JTextField uname_t, email_t;
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

        uname = new JLabel("Username");
        uname.setFont(new Font("DIALOG", Font.BOLD, 16));
        uname.setBounds(50,120,150,25);
        panel.add(uname);
        uname_t = new JTextField();
        uname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        uname_t.setBounds(150,120,180,25);
        panel.add(uname_t);

        /*email = new JLabel("Email");
        email.setFont(new Font("DIALOG", Font.BOLD, 16));
        email.setBounds(50,170,150,25);
        panel.add(email);
        email_t = new JTextField();
        email_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        email_t.setBounds(150,170,180,25);
        panel.add(email_t);*/

        submit = new JButton("Submit");
        submit.setFont(new Font("DIALOG", Font.BOLD, 14));
        submit.setBounds(240,240,90,30);
        submit.addActionListener(this);
        panel.add(submit);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registerController reg = new registerController();
        if(e.getSource() == submit){
            String uname_tem = uname_t.getText();
            if (!reg.checkUname(uname_tem)) {
                JOptionPane.showMessageDialog(null, "Username does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                //send the email with password

            }
        }
    }
}
