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

        // Initialize all the required components for register GUI
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


        fname = new JLabel("First Name");
        fname.setFont(new Font("DIALOG", Font.BOLD, 16));
        fname.setBounds(50,170,100,25);
        panel.add(fname);
        fname_t = new JTextField();
        fname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        fname_t.setBounds(250,170,150,25);
        panel.add(fname_t);

        lname = new JLabel("Last Name");
        lname.setFont(new Font("DIALOG", Font.BOLD, 16));
        lname.setBounds(50,220,100,25);
        panel.add(lname);
        lname_t = new JTextField();
        lname_t.setFont(new Font("DIALOG", Font.PLAIN, 14));
        lname_t.setBounds(250,220,150,25);
        panel.add(lname_t);

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

        // Actionlistner for combobox (drop box)
        if(e.getSource() == acctype_b){
            System.out.println(acctype_b.getSelectedItem());
        }
        if(e.getSource() == close){
            System.out.println("Close");
            frame.dispose();
        }
        if(e.getSource() == signup) {
            String error_msg = "";
            String id_tem = id_t.getText();
            String fname_tem = fname_t.getText();
            String lname_tem = lname_t.getText();
            String email_tem = email_t.getText();
            String uname_tem = uname_t.getText();
            String pass_tem = pass_t.getText();
            String cpass_tem = cpass_t.getText();
            String actype_tem = acctype_b.getSelectedItem().toString();

            registerController reg = new registerController();
            if (id_tem.length() == 0) {
                error_msg += "Invalid ID: Empty ID\n";
            } else if (!reg.IDValidator(id_tem)) {
                error_msg += "Invalid ID: only integers are allowed. No spaces.\n";
            }

            if (fname_tem.length() == 0) {
                error_msg += "Invalid First Name: Empty First Name\n";
            } else if (!reg.UNValidator(fname_tem)) {
                error_msg += "Invalid First Name: Only letters are allowed. No spaces.\n";
            }

            if (lname_tem.length() == 0) {
                error_msg += "Invalid Last Name: Empty Last Name.\n";
            } else if (!reg.UNValidator(lname_tem)) {
                error_msg += "Invalid Last Name: Only letters are allowed. No spaces.\n";
            }

            if (email_tem.length() == 0) {
                error_msg += "Invalid Email: Empty Email.\n";
            } else if (!reg.emailValidator(email_tem)) {
                error_msg += "Invalid Email\n";
            }

            if (uname_tem.length() == 0) {
                error_msg += "Invalid Username: Empty Username.\n";
            }
            else if (!reg.UNValidator(uname_tem)) {
                error_msg += "Invalid Username: Only letters are allowed. No spaces.\n";
            }

            if (pass_tem.length() == 0) {
                error_msg += "Invalid Username: Empty Username.\n";
            }

            if ((pass_tem.equals(cpass_tem)) == false) {
                error_msg += "Passwords not match.\n";
            }

            if (actype_tem == "--Select Type--") {
                error_msg += "Please select the account type.\n";
            }

            if (error_msg != "") {
                JOptionPane.showMessageDialog(null, error_msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                int id_temp = Integer.parseInt(id_tem);
                //reg.addUserInfo(id_temp, uname_tem, fname_tem, lname_tem, actype_tem, email_tem, pass_tem);
                JOptionPane.showMessageDialog(null, "Successfully Signed up ", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
