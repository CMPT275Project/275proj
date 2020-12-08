package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import java.awt.Dimension;
import java.awt.Component;

public class deviceGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel main_panel, device_pan, account_pan;
    private static JPanel sel_pan, sel_pan2;
    private static JLabel title, date, user_icon, userID, userType, userFN, userLN, userUN, userEmail;
    private static JLabel searchType_l, sID_l, sType_l, sUname_l, aID_l, aType_l, aDes_l,aAvail_l, aCond_l;
    private static JLabel rID_l, rType_l, rDes_l, eID_l, eType_l, eDes_l, eAvail_l, eCond_l, borrowID_l, borrowUname_l, bDate_l;
    private static JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    private static JTextField sID_t, sType_t, sUname_t, aID_t, aType_t, aDes_t;
    private static JTextField rID_t, rType_t, rDes_t, eID_t, eType_t, eDes_t, borrowID_t ,borrowUname_t, bDate_t;
    private static JTabbedPane tp;
    private static JTable device_table;
    private static JScrollPane scrollPane;
    private static JRadioButton search_sel, add_sel, remove_sel, edit_sel, borrow_sel, return_sel;
    private static JComboBox searchType, aAvail_t, aCond_t, eAvail_t, eCond_t;
    private static JButton search_b, add_b, remove_b, edit_b, borrow_b, return_b, logout, show_tb, rClear_b, eClear_b, bClear_b;
    public String username;
    public void setUN(String UN){this.username = UN;}
    public String getUN(){return this.username;}

    public void deviceWindow(String username, String roleType) {
        //Getting the account type from Login
        String actype = roleType;
        String UN = username;
        setUN(username);
        frame = new JFrame("Main Page - " + actype);
        frame.setSize(1000, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        title = new JLabel("Laboratory Device Inventory System");
        title.setForeground(new Color(0, 100, 200));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(300, 30, 450, 40);
        frame.add(title);

        tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setBounds(50,100,900,700);
        Font font = new Font("DIALOG", Font.CENTER_BASELINE, 20);
        tp.setFont(font);
        device_pan = new JPanel();
        device_pan.setLayout(null);
        account_pan = new JPanel();
        account_pan.setLayout(null);

        tp.addTab("Device",device_pan);
        tp.addTab("Account", account_pan);
        frame.add(tp);

        String tem_date = java.time.LocalDate.now().toString();
        date = new JLabel("Today: " + tem_date);
        date.setBounds(800,20,150,35);
        date.setFont(new Font("DIALOG", Font.BOLD, 16));
        frame.add(date);

        logout = new JButton("Log out");
        logout.setBounds(860,80,90,35);
        logout.setFont(new Font("DIALOG", Font.BOLD, 14));
        logout.addActionListener(this);
        frame.add(logout);


        JLabel sel_info = new JLabel("You have selected:");
        sel_info.setBounds(50,300,200,25);
        sel_info.setFont(new Font("DIALOG", Font.BOLD, 13));

        textField1 = new JTextField();
        textField1.setBounds(50,330,80,25);
        textField1.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField1.setEditable(false);
        textField2 = new JTextField();
        textField2.setBounds(130,330,70,25);
        textField2.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField2.setEditable(false);
        textField3 = new JTextField();
        textField3.setBounds(200,330,150,25);
        textField3.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField3.setEditable(false);
        textField4 = new JTextField();
        textField4.setBounds(350,330,80,25);
        textField4.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField4.setEditable(false);
        textField5 = new JTextField();
        textField5.setBounds(430,330,70,25);
        textField5.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField5.setEditable(false);
        textField6 = new JTextField();
        textField6.setBounds(500,330,80,25);
        textField6.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField6.setEditable(false);
        textField7 = new JTextField();
        textField7.setBounds(580,330,70,25);
        textField7.setFont(new Font("DIALOG", Font.BOLD, 10));
        textField7.setEditable(false);
        device_pan.add(sel_info);
        device_pan.add(textField1);
        device_pan.add(textField2);
        device_pan.add(textField3);
        device_pan.add(textField4);
        device_pan.add(textField5);
        device_pan.add(textField6);
        device_pan.add(textField7);

        //get table
        deviceController table = new deviceController();
        table.setRowNum();
        addJtable(table.showTable());

        JLabel opt_info = new JLabel("Please select one of the following options:");
        opt_info.setBounds(50,10,300,25);
        opt_info.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(opt_info);

        sel_pan = new JPanel();
        sel_pan.setBounds(40, 80, 620,200);
        sel_pan.setLayout(null);
        //sel_pan.setBorder(BorderFactory.createLineBorder(Color.black));
        device_pan.add(sel_pan);

        search_sel = new JRadioButton("Search");
        search_sel.setBounds(50, 40, 100,35);
        search_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(search_sel);
        add_sel = new JRadioButton("Add");
        add_sel.setBounds(150, 40, 100,35);
        add_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(add_sel);
        remove_sel = new JRadioButton("Remove");
        remove_sel.setBounds(250, 40, 100,35);
        remove_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(remove_sel);
        edit_sel = new JRadioButton("Edit");
        edit_sel.setBounds(350, 40, 100,35);
        edit_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(edit_sel);

        // CAT2
        borrow_sel = new JRadioButton("Borrow");
        borrow_sel.setBounds(500, 40, 100,35);
        borrow_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(borrow_sel);
        return_sel = new JRadioButton("Return");
        return_sel.setBounds(600, 40, 100,35);
        return_sel.setFont(new Font("DIALOG", Font.BOLD, 14));
        device_pan.add(return_sel);

        // Grouping all the radio button, so that only one can be selected
        ButtonGroup bg = new ButtonGroup();
        bg.add(search_sel);
        bg.add(add_sel);
        bg.add(remove_sel);
        bg.add(edit_sel);
        bg.add(borrow_sel);
        bg.add(return_sel);

        //Button for refresh the table
        show_tb = new JButton("Show All");
        show_tb.setBounds(700,380,100,30);
        device_pan.add(show_tb);
        show_tb.addActionListener(this);
        JTextArea show_info = new JTextArea("Please use the (Show All) button \nto refresh the table");
        show_info.setBounds(700,420,190,50);
        show_info.setBackground(new Color(224, 224, 224));
        device_pan.add(show_info);

        // Component for search
        searchType_l = new JLabel("Search By");
        searchType_l.setBounds(10,20,100,25);
        search_b = new JButton("Search");
        search_b.setBounds(10,20,100,25);
        String[] type = {"--Select Type--","Model ID", "Item Type", "Username"};
        searchType = new JComboBox(type);
        searchType.setBounds(100,20,120,25);

        search_b = new JButton("Search");
        search_b.setBounds(140,120,80,30);
        search_b.setFont(new Font("DIALOG", Font.BOLD, 13));
        search_b.addActionListener(this);

        sID_l = new JLabel("Model ID");
        sID_l.setBounds(10,70,100,25);
        sID_t = new JTextField();
        sID_t.setBounds(100,70,120,25);
        sType_l = new JLabel("Item Type");
        sType_l.setBounds(10,70,100,25);
        sType_t = new JTextField();
        sType_t.setBounds(100,70,120,25);
        sUname_l = new JLabel("Username");
        sUname_l.setBounds(10,70,100,25);
        sUname_t = new JTextField();
        sUname_t.setBounds(100,70,120,25);



        // Components for add
        aID_l = new JLabel("Model ID");
        aID_l.setBounds(10,20,100,25);
        aID_t = new JTextField();
        aID_t.setBounds(100,20,130,25);
        aType_l = new JLabel("Item Type");
        aType_l.setBounds(10,60,100,25);
        aType_t = new JTextField();
        aType_t.setBounds(100,60,130,25);
        aDes_l = new JLabel("Description");
        aDes_l.setBounds(10,100,100,25);
        aDes_t = new JTextField();
        aDes_t.setBounds(100,100,130,25);
        aAvail_l = new JLabel("Availability");
        aAvail_l.setBounds(280,20,100,25);
        String[] atype = {"yes", "No"};
        aAvail_t = new JComboBox(atype);
        aAvail_t.setBounds(380,20,100,25);
        aCond_l = new JLabel("Condition");
        aCond_l.setBounds(280,60,100,25);
        String[] ctype = {"good", "fair", "poor"};
        aCond_t = new JComboBox(ctype);
        aCond_t.setBounds(380,60,100,25);
        add_b = new JButton("Add");
        add_b.setBounds(400,140,80,30);
        add_b.setFont(new Font("DIALOG", Font.BOLD, 13));
        add_b.addActionListener(this);


        // Components for remove
        JLabel remove_info = new JLabel("Note: Please select the item from the table below.");
        remove_info.setBounds(280,20,300,25);
        rID_l = new JLabel("Model ID");
        rID_l.setBounds(10,20,100,25);
        rID_t = new JTextField();
        rID_t.setBounds(100,20,130,25);
        rID_t.setEditable(false);
        rID_t.setBorder(BorderFactory.createLineBorder(Color.black));
        rType_l = new JLabel("Item Type");
        rType_l.setBounds(10,60,100,25);
        rType_t = new JTextField();
        rType_t.setBounds(100,60,130,25);
        rType_t.setEditable(false);
        rType_t.setBorder(BorderFactory.createLineBorder(Color.black));
        rDes_l = new JLabel("Description");
        rDes_l.setBounds(10,100,100,25);
        rDes_t = new JTextField();
        rDes_t.setBounds(100,100,130,25);
        rDes_t.setEditable(false);
        rDes_t.setBorder(BorderFactory.createLineBorder(Color.black));
        remove_b = new JButton("Remove");
        remove_b.setBounds(150,150,80,30);
        remove_b.setFont(new Font("DIALOG", Font.BOLD, 12));
        remove_b.addActionListener(this);
        rClear_b = new JButton("Clear");
        rClear_b.setBounds(40,150,70,30);
        rClear_b.setFont(new Font("DIALOG", Font.BOLD, 12));
        rClear_b.addActionListener(this);



        // Components for edit
        JTextArea edit_info = new JTextArea("Note: \n1. Please select the item from the table below.\n2. Please remove and add the item \nif you would like to change the Model ID or Item Type");
        edit_info.setBounds(10,150,300,50);
        edit_info.setBackground(new Color(224, 224, 224));
        eID_l = new JLabel("Model ID");
        eID_l.setBounds(10,20,100,25);
        eID_t = new JTextField();
        eID_t.setBounds(100,20,130,25);
        eID_t.setEditable(false);
        eID_t.setBorder(BorderFactory.createLineBorder(Color.black));
        eType_l = new JLabel("Item Type");
        eType_l.setBounds(10,60,100,25);
        eType_t = new JTextField();
        eType_t.setBounds(100,60,130,25);
        eType_t.setEditable(false);
        eType_t.setBorder(BorderFactory.createLineBorder(Color.black));
        eDes_l = new JLabel("Description");
        eDes_l.setBounds(10,100,100,25);
        eDes_t = new JTextField();
        eDes_t.setBounds(100,100,130,25);
        eAvail_l = new JLabel("Availability");
        eAvail_l.setBounds(280,20,100,25);
        String[] atype1 = {"yes", "No"};
        eAvail_t = new JComboBox(atype1);
        eAvail_t.setBounds(380,20,100,25);
        eCond_l = new JLabel("Condition");
        eCond_l.setBounds(280,60,100,25);
        String[] etype1 = {"good", "fair", "poor"};
        eCond_t = new JComboBox(etype1);
        eCond_t.setBounds(380,60,100,25);
        edit_b = new JButton("Edit");
        edit_b.setBounds(400,140,80,30);
        edit_b.setFont(new Font("DIALOG", Font.BOLD, 13));
        edit_b.addActionListener(this);
        eClear_b = new JButton("Clear");
        eClear_b.setBounds(400,100,80,30);
        eClear_b.setFont(new Font("DIALOG", Font.BOLD, 12));
        eClear_b.addActionListener(this);


        // Component for Borrow
        JLabel borrow_info = new JLabel("Note: Please select the item from the table below.");
        borrow_info.setBounds(280,20,300,25);

        borrowID_l = new JLabel("Item ID");
        borrowID_l.setBounds(10,20,100,25);
        borrowID_t = new JTextField();
        borrowID_t.setBounds(100,20,130,25);
        borrowID_t.setEditable(false);
        borrowID_t.setBorder(BorderFactory.createLineBorder(Color.black));
        borrowUname_l = new JLabel("Username");
        borrowUname_l.setBounds(10,60,100,25);
        borrowUname_t = new JTextField();
        borrowUname_t.setBounds(100,60,130,25);
        borrow_b = new JButton("Confirm");
        borrow_b.setBounds(390,140,90,30);
        borrow_b.setFont(new Font("DIALOG", Font.BOLD, 13));
        borrow_b.addActionListener(this);
        bDate_l = new JLabel("Expiry Date");
        bDate_l.setBounds(10,100,100,25);
        bDate_t = new JTextField();
        bDate_t.setBounds(100,100,130,25);
        bClear_b = new JButton("Clear");
        bClear_b.setBounds(300,140,70,30);
        bClear_b.addActionListener(this);
        JLabel dateInfo = new JLabel("(dd/mm/yyyy)");
        dateInfo.setBounds(100,125,130,25);


        // Component for Return
        return_b = new JButton("Return");
        return_b.setBounds(390,140,90,30);
        return_b.setFont(new Font("DIALOG", Font.BOLD, 13));
        return_b.addActionListener(this);

        // Switching the panel content based on the option
        search_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (search_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(searchType);
                    sel_pan.add(searchType_l);
                    sel_pan.add(search_b);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });
        add_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (add_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(aID_l);
                    sel_pan.add(aID_t);
                    sel_pan.add(aType_l);
                    sel_pan.add(aType_t);
                    sel_pan.add(aDes_l);
                    sel_pan.add(aDes_t);
                    sel_pan.add(aAvail_l);
                    sel_pan.add(aAvail_t);
                    sel_pan.add(aCond_l);
                    sel_pan.add(aCond_t);
                    sel_pan.add(add_b);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });
        remove_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remove_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(remove_info);
                    sel_pan.add(rID_l);
                    sel_pan.add(rID_t);
                    sel_pan.add(rType_l);
                    sel_pan.add(rType_t);
                    sel_pan.add(rDes_l);
                    sel_pan.add(rDes_t);
                    sel_pan.add(remove_b);
                    sel_pan.add(rClear_b);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });
        edit_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (edit_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(edit_info);
                    sel_pan.add(eID_l);
                    sel_pan.add(eID_t);
                    sel_pan.add(eType_l);
                    sel_pan.add(eType_t);
                    sel_pan.add(eDes_l);
                    sel_pan.add(eDes_t);
                    sel_pan.add(eAvail_l);
                    sel_pan.add(eAvail_t);
                    sel_pan.add(eCond_l);
                    sel_pan.add(eCond_t);
                    sel_pan.add(edit_b);
                    sel_pan.add(eClear_b);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });
        borrow_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (borrow_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(borrow_info);
                    sel_pan.add(borrowID_l);
                    sel_pan.add(borrowID_t);
                    sel_pan.add(borrowUname_l);
                    sel_pan.add(borrowUname_t);
                    sel_pan.add(borrow_b);
                    sel_pan.add(bClear_b);
                    sel_pan.add(bDate_l);
                    sel_pan.add(bDate_t);
                    sel_pan.add(dateInfo);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });
        return_sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (return_sel.isSelected()) {
                    sel_pan.removeAll();
                    sel_pan.add(return_b);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });




        // JCombobox for search by type
        searchType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search_tem = searchType.getSelectedItem().toString();
                if (search_tem.equals("Model ID")) {
                    sel_pan.remove(sType_l);
                    sel_pan.remove(sType_t);
                    sel_pan.remove(sUname_l);
                    sel_pan.remove(sUname_t);
                    sel_pan.add(sID_t);
                    sel_pan.add(sID_l);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
                else if (search_tem.equals("Item Type")) {
                    sel_pan.remove(sID_l);
                    sel_pan.remove(sID_t);
                    sel_pan.remove(sUname_l);
                    sel_pan.remove(sUname_t);
                    sel_pan.add(sType_l);
                    sel_pan.add(sType_t);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
                else if (search_tem.equals("Username")) {
                    sel_pan.remove(sID_l);
                    sel_pan.remove(sID_t);
                    sel_pan.remove(sType_l);
                    sel_pan.remove(sType_t);
                    sel_pan.add(sUname_l);
                    sel_pan.add(sUname_t);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });

        // Panel for account
        user_icon = new JLabel();
        user_icon.setBounds(80,50,150,150);
        user_icon.setIcon(new ImageIcon("src/icons.png"));
        account_pan.add(user_icon);
        account_pan.validate();


        userInfo user = new userInfo();
        user.setInfo(UN);
        String temID = user.getID();
        userID = new JLabel("Accout ID: " + temID);
        userID.setBounds(100,200,300,35);
        userID.setFont(new Font("DIALOG", Font.BOLD, 16));
        String temType = user.getType();
        userType = new JLabel("Account Type: " + temType);
        userType.setBounds(100,250,300,35);
        userType.setFont(new Font("DIALOG", Font.BOLD, 16));
        String temFN = user.getFN();
        userFN = new JLabel("First Name: " + temFN);
        userFN.setBounds(100,300,300,35);
        userFN.setFont(new Font("DIALOG", Font.BOLD, 16));
        String temLN = user.getLN();
        userLN = new JLabel("Last Name:" + temLN);
        userLN.setBounds(100,350,300,35);
        userLN.setFont(new Font("DIALOG", Font.BOLD, 16));
        String temUN = user.getUserName();
        userUN = new JLabel("Username: " + temUN);
        userUN.setBounds(100,400,300,35);
        userUN.setFont(new Font("DIALOG", Font.BOLD, 16));
        String temEmail = user.getEmail();
        userEmail = new JLabel("Email: " + temEmail);
        userEmail.setBounds(100,450,300,35);
        userEmail.setFont(new Font("DIALOG", Font.BOLD, 16));

        account_pan.add(userID);
        account_pan.add(userType);
        account_pan.add(userFN);
        account_pan.add(userLN);
        account_pan.add(userUN);
        account_pan.add(userEmail);

        frame.setVisible(true);
        //frame.dispose();
    }

    public void disableTable()
    {
        this.device_pan.remove(this.scrollPane);
    }

    public void addJtable(String[][] newJTABLE)
    {
        // Panel for device
        String[] col = {"Model ID", "Type", "Description","Availability", "Reserve", "Expiry Date ","Condition"};
        //deviceController table = new deviceController();
        //table.setRowNum();
        String[][] row = newJTABLE;

        // Jtable Setup
        device_table = new JTable();
        device_table.setEnabled(true);
        device_table.setModel(new DefaultTableModel(row,col)
        {
            boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false};
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane = new JScrollPane();
        scrollPane.setBounds(50,380,620,250);
        scrollPane.setViewportView(device_table);
        device_pan.add(scrollPane);
        device_table.setPreferredScrollableViewportSize(new Dimension(100,200));
        device_table.setBounds(400,50,700,50);
        device_table.setFillsViewportHeight(true);
        device_table.setRowHeight(40);
        device_table.getColumnModel().getColumn(2).setPreferredWidth(150);//change column size
        device_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //device_table.setAutoCreateRowSorter(true); // this will cause the selection problem
        device_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // For table row selection (device page)
        device_table.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {

                        DefaultTableModel model = (DefaultTableModel)device_table.getModel();
                        int selectedRowIndex = device_table.getSelectedRow();
                        textField1.setText(String.format(model.getValueAt(selectedRowIndex,0).toString()));
                        textField2.setText(String.format(model.getValueAt(selectedRowIndex,1).toString()));
                        textField3.setText(String.format(model.getValueAt(selectedRowIndex,2).toString()));
                        textField4.setText(String.format(model.getValueAt(selectedRowIndex,3).toString()));
                        textField5.setText(String.format(model.getValueAt(selectedRowIndex,4).toString()));
                        textField6.setText(String.format(model.getValueAt(selectedRowIndex,5).toString()));
                        textField7.setText(String.format(model.getValueAt(selectedRowIndex,6).toString()));

                        rID_t.setText(String.format(model.getValueAt(selectedRowIndex,0).toString()));
                        rType_t.setText(String.format(model.getValueAt(selectedRowIndex,1).toString()));
                        rDes_t.setText(String.format(model.getValueAt(selectedRowIndex,2).toString()));

                        eID_t.setText(String.format(model.getValueAt(selectedRowIndex,0).toString()));
                        eType_t.setText(String.format(model.getValueAt(selectedRowIndex,1).toString()));
                        eDes_t.setText(String.format(model.getValueAt(selectedRowIndex,2).toString()));

                        borrowID_t.setText(String.format(model.getValueAt(selectedRowIndex,0).toString()));
                    }
                }
        );
    }

    // Action after button *************
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == show_tb) {
            deviceController device = new deviceController();
            String[][] table = device.showTable();
            disableTable();
            addJtable(table);
        }

        if(e.getSource() == search_b) {
            //search button
            String type = searchType.getSelectedItem().toString();
            deviceController device = new deviceController();
            String err = "";
            if(type == "Model ID")
            {
                String temp = sID_t.getText();
                if (device.inputValidator(temp) == false) {
                    err += "Invalid Input: Contains special characters.\n";
                    JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
                }
                String[][] table = device.searchModelID(temp);
                if (table == null && err == "") {
                    JOptionPane.showMessageDialog(null, "Model ID does not exist!", "Search", JOptionPane.ERROR_MESSAGE);
                }
                disableTable();
                addJtable(table);
            }
            else if(type == "Item Type")
            {
                String temp = sType_t.getText();
                if (device.inputValidator(temp) == false) {
                    err += "Invalid Input: Contains special characters.\n";
                    JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
                }
                String[][] table = device.searchType(temp);
                disableTable();
                addJtable(table);
            }
            else if (type == "Username")
            {
                String temp = sUname_t.getText();
                if (device.inputValidator(temp) == false) {
                    err += "Invalid Input: Contains special characters.\n";
                    JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
                }
                String[][] table = device.searchUsername(temp);
                if (table == null && err == null) {
                    JOptionPane.showMessageDialog(null, "Username does not exist!", "Search", JOptionPane.ERROR_MESSAGE);
                }
                disableTable();
                addJtable(table);
            }
        }

        if(e.getSource() == add_b) {
            //add button
            deviceController device = new deviceController();
            if (aID_t.getText().length() == 0 || aType_t.getText().length() == 0  || aDes_t.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Error: inputs can not be empty!", "Add", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (device.inputValidator(aID_t.getText()) == false || device.inputValidator(aType_t.getText()) == false || device.inputValidator(aDes_t.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid Input: Contains special characters.", "Add", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int result = device.addNewDevice(aID_t.getText(), aType_t.getText(), aDes_t.getText(), aAvail_t.getSelectedItem().toString(), "NA","NA", aCond_t.getSelectedItem().toString());
                    if (result == 2) {
                        JOptionPane.showMessageDialog(null, "Model ID already exits!", "Add", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (result  == -1 || result == 0) {
                        JOptionPane.showMessageDialog(null, "Unable to add the device!", "Add", JOptionPane.ERROR_MESSAGE);
                    }
                    else if (result == 1){
                        JOptionPane.showMessageDialog(null, "Device Added!", "Add", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }


            disableTable();
            addJtable(device.showTable());
        }

        if(e.getSource() == remove_b) {
            //remove button
            String temp = rID_t.getText();
            deviceController device = new deviceController();
            int result = device.deleteDevice(temp);
            if (result  == -1 || result == 0 ) {
                JOptionPane.showMessageDialog(null, "Unable to delete the device!", "Remove", JOptionPane.ERROR_MESSAGE);
            }
            else if (result == 1){
                JOptionPane.showMessageDialog(null, "Device Deleted!", "Remove", JOptionPane.INFORMATION_MESSAGE);
            }
            disableTable();
            addJtable(device.showTable());
        }

        if(e.getSource() == edit_b) {
            //edit button
            deviceController device = new deviceController();
            boolean result = device.edit(eID_t.getText(), eType_t.getText(), eDes_t.getText(), eAvail_t.getSelectedItem().toString(), eCond_t.getSelectedItem().toString());
            if (device.inputValidator(eDes_t.getText()) == false ) {
                JOptionPane.showMessageDialog(null, "Invalid Input: Contains special characters.", "Edit", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (result == false) {
                    JOptionPane.showMessageDialog(null, "Unable to delete the device!", "Edit", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Device Edited!", "Edit", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            disableTable();
            addJtable(device.showTable());
        }

        // this point
        if(e.getSource() == borrow_b) {
            //Borrow button
            System.out.println("Pressed borrow button");
            deviceController device = new deviceController();
            String realUN = getUN();
            String UN = borrowUname_t.getText();
            String ED = bDate_t.getText();
            String ID = borrowID_t.getText();
            String err = "";
            if(borrowID_t.equals(""))
            {
                err += "Please select a device first.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }
            else if(UN.equals(""))
            {
                err += "Invalid Username: Username is empty.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }
            else if(!device.checkUN(UN))
            {
                err += "Invalid Username: Username does not exist.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }
            else if(!UN.equals(realUN))
            {
                err += "Invalid Username: This is not the same username for login.\n" +
                        "Please use your own username to borrow device.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }
            else if(UN.equals(""))
            {
                err += "Invalid Expire Date: Date is empty.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }else
            {
                try {
                    if(device.dateValidator(ED) == -2)
                    {
                        err += "Invalid expire date setting: Date format wrong.\n";
                        JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
                    }
                    else if(device.dateValidator(ED) == -1)
                    {
                        err += "Invalid expire date setting: Date should be later than current date.\n";
                        JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if(device.checkCanBorrow(ID))
            {
                err += "Sorry this device is occupied.\n";
                JOptionPane.showMessageDialog(null, err, "Search", JOptionPane.ERROR_MESSAGE);
            }

            try {
                if(device.dateValidator(ED) == 1 && (err.equals("")))
                {
                    if(device.borrowDevice(ID, UN, ED))
                    {
                        System.out.println("BORROW SUCCESS");
                        disableTable();
                        addJtable(device.showTable());
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        if(e.getSource() == return_b) {
            //return button
            System.out.println("Pressed return button");
        }

        if(e.getSource() == rClear_b) {
            rID_t.setText("");
            rType_t.setText("");
            rDes_t.setText("");
        }

        if(e.getSource() == eClear_b) {
            eID_t.setText("");
            eType_t.setText("");
            eDes_t.setText("");
        }

        if(e.getSource() == bClear_b) {
            borrowID_t.setText("");
            borrowUname_t.setText("");
            bDate_t.setText("");
        }

        if(e.getSource() == logout) {
            //edit button
            System.out.println("Pressed logout button");
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Log out Successfully", "Log out", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}