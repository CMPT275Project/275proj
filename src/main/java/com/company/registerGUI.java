package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel title, id, lname, fname, email, uname, pass, cpass, acctype;
    private static JTextField id_t, lname_t, fname_t, email_t, uname_t, pass_t, cpass_t;
    private static JButton signup, close;
    private static JComboBox acctype_b;

    public void regWindow() {
        frame = new JFrame("Register");
        frame.setSize(500, 800);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        title = new JLabel("Register New Account");
        title.setForeground(new Color(0,128,255));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(50,50,350,40);
        panel.add(title);

        id = new JLabel("ID");
        id.setFont(new Font("DIALOG", Font.BOLD, 16));
        id.setBounds(50,120,100,25);
        panel.add(id);
        id_t = new JTextField();
        id_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        id_t.setBounds(250,120,150,25);
        panel.add(id_t);

        lname = new JLabel("Last Name");
        lname.setFont(new Font("DIALOG", Font.BOLD, 16));
        lname.setBounds(50,170,100,25);
        panel.add(lname);
        lname_t = new JTextField();
        lname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        lname_t.setBounds(250,170,150,25);
        panel.add(lname_t);

        fname = new JLabel("First Name");
        fname.setFont(new Font("DIALOG", Font.BOLD, 16));
        fname.setBounds(50,220,100,25);
        panel.add(fname);
        fname_t = new JTextField();
        fname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        fname_t.setBounds(250,220,150,25);
        panel.add(fname_t);


        email = new JLabel("Email");
        email.setFont(new Font("DIALOG", Font.BOLD, 16));
        email.setBounds(50,270,100,25);
        panel.add(email);
        email_t = new JTextField();
        email_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        email_t.setBounds(250,270,150,25);
        panel.add(email_t);

        uname = new JLabel("Username");
        uname.setFont(new Font("DIALOG", Font.BOLD, 16));
        uname.setBounds(50,350,100,25);
        panel.add(uname);
        uname_t = new JTextField();
        uname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        uname_t.setBounds(250,350,150,25);
        panel.add(uname_t);

        pass = new JLabel("Password");
        pass.setFont(new Font("DIALOG", Font.BOLD, 16));
        pass.setBounds(50,400,100,25);
        panel.add(pass);
        pass_t = new JTextField();
        pass_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        pass_t.setBounds(250,400,150,25);
        panel.add(pass_t);

        cpass = new JLabel("Confirm Password");
        cpass.setFont(new Font("DIALOG", Font.BOLD, 16));
        cpass.setBounds(50,450,150,25);
        panel.add(cpass);
        cpass_t = new JTextField();
        cpass_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        cpass_t.setBounds(250,450,150,25);
        panel.add(cpass_t);

        acctype = new JLabel("Account Type");
        acctype.setFont(new Font("DIALOG", Font.BOLD, 16));
        acctype.setBounds(50,500,150,25);
        panel.add(acctype);
        String[] type = {"--Select Type--","Admin", "Student"};
        acctype_b = new JComboBox(type);
        acctype_b.setBounds(250,500,120,25);
        acctype_b.addActionListener(this);
        panel.add(acctype_b);

        signup = new JButton("Sign Up");
        signup.setBounds(320, 600, 80, 40);
        signup.addActionListener(this);
        panel.add(signup);

        close = new JButton("Close");
        close.setBounds(50, 600, 80, 40);
        close.addActionListener(this);
        panel.add(close);


        frame.setVisible(true);
        //frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == acctype_b){
            System.out.println(acctype_b.getSelectedItem());
        }
        if(e.getSource() == close){
            System.out.println("Close");
            frame.dispose();
        }
        if(e.getSource() == signup){
            System.out.println("trying to sign up");
        }
    }
}
