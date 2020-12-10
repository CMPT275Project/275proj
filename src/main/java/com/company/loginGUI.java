package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel main_panel;
    private static JLabel title, user_label, pass_label;
    private static JTextField user_text;
    private static JPasswordField pass_text;
    private static JButton signin, register, fpass;
    private static JCheckBox show_pass;
    protected String AC_type;
    protected String Username;

    public void loginWindow() {

        // Initialize all the required components for Login GUI
        frame = new JFrame("Login");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

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

        pass_text = new JPasswordField(15);
        pass_text.setBounds(250,220,150,25);
        pass_text.setEchoChar('*');
        main_panel.add(pass_text);

        show_pass = new JCheckBox("Show Password");
        show_pass.setBounds(250,250,150,25);
        show_pass.addActionListener(this);
        main_panel.add(show_pass);

        signin = new JButton("Sign in");
        signin.setBounds(310,320,90,35);
        signin.setFont(new Font("DIALOG", Font.BOLD, 14));
        signin.addActionListener(this);
        main_panel.add(signin);

        register = new JButton("Register");
        register.setBounds(150,290,60,25);
        register.setFont(new Font("DIALOG", Font.BOLD, 12));
        register.setBorder(BorderFactory.createEmptyBorder());
        register.setBackground(Color.lightGray);
        register.addActionListener(this);
        main_panel.add(register);

        fpass = new JButton("Forgot Password?");
        fpass.setBounds(150,330,120,25);
        fpass.setFont(new Font("DIALOG", Font.BOLD, 12));
        fpass.setBorder(BorderFactory.createEmptyBorder());
        fpass.setBackground(Color.lightGray);
        fpass.addActionListener(this);
        main_panel.add(fpass);

        frame.setVisible(true);
        //frame.dispose();
    }
    public String getType() {
        return this.AC_type;
    }
    public void setType(String type)
    {
        this.AC_type = type;
    }

    public void setUN(String username)
    {
        this.Username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Action for showing password and hiding the password
        if(e.getSource() == show_pass){
            if(show_pass.isSelected()){
                pass_text.setEchoChar((char)0);
            }
            else{
                pass_text.setEchoChar('*');
            }
        }

        if(e.getSource() == signin){
            String error_msg = "";
            String pass = new String(pass_text.getPassword());
            String uname = user_text.getText();
            loginController login = new loginController();
            if(login.UNValidator(uname).equals("spaceWrong")) {
                error_msg += "Invalid input: Username should not contain spaces.\n";
            }
            else if(login.UNValidator(uname).equals("characterWrong")) {
                error_msg += "Invalid input: Username should not contain special characters.\n";
            }

            if(login.passwordValidator(pass).equals("lengthWrong")) {
                error_msg += "Invalid input: Password (invalid Length, password should be in 6 ~ 15 characters).\n";
            }
            else if(login.passwordValidator(pass).equals("spaceWrong")) {
                error_msg += "Invalid input: Password should not contain spaces.\n";
            }
            else if(login.passwordValidator(pass).equals("characterWrong")) {
                error_msg += "Invalid input: Password should not contain special characters.\n";
            }


            if(login.checkLogin(uname, pass) && error_msg.equals("")){
                // Enter to the main page
                frame.dispose();
                deviceGUI device_win = new deviceGUI();
                // NEED FIXes!!!!!
                this.setType(login.type);
                this.setUN(uname);
                device_win.deviceWindow(uname, this.AC_type);
            }
            else{
                error_msg += "Incorrect Username or Password!\n";
                JOptionPane.showMessageDialog(null, error_msg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == register){
            registerGUI reg_win = new registerGUI();
            reg_win.regWindow();
        }

        if(e.getSource() == fpass){
            forgotPassGUI fpass_win = new forgotPassGUI();
            fpass_win.fpassWindow();
        }

    }
}