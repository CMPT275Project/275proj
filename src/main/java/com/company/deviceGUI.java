package com.company;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Date;
import java.util.List;

public class deviceGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel main_panel, device_pan, rental_pan, account_pan;
    private static JPanel sel_pan;
    private static JLabel title, date;
    private static JLabel searchType_l, sID_l, sType_l, aID_l, aType_l, aDes_l,aAvail_l, aCond_l;
    private static JLabel rID_l, rType_l, rDes_l, eID_l, eType_l, eDes_l, eAvail_l, eCond_l;
    private static JTextField textField1, textField2, textField3, textField4, textField5, textField6, textField7;
    private static JTextField sID_t, sType_t, aID_t, aType_t, aDes_t;
    private static JTextField rID_t, rType_t, rDes_t, eID_t, eType_t, eDes_t;
    private static JTabbedPane tp;
    private static JTable device_table;
    private static JScrollPane scrollPane;
    private static JRadioButton search_sel, add_sel, remove_sel, edit_sel;
    private static JComboBox searchType, aAvail_t, aCond_t, eAvail_t, eCond_t;
    private static JButton search_b, add_b, remove_b, edit_b, logout;


    public void deviceWindow() {
        //Getting the account type from Login
        loginGUI temp = new loginGUI();
        String actype = temp.getType();

        frame = new JFrame("Main Page - " + actype);
        frame.setSize(1000, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        title = new JLabel("Laboratory Inventory System");
        title.setForeground(new Color(0, 100, 200));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(300, 30, 350, 40);
        frame.add(title);

        tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setBounds(50,100,900,700);
        Font font = new Font("DIALOG", Font.CENTER_BASELINE, 20);
        tp.setFont(font);
        device_pan = new JPanel();
        device_pan.setLayout(null);
        rental_pan = new JPanel();
        rental_pan.setLayout(null);
        account_pan = new JPanel();
        account_pan.setLayout(null);

        tp.addTab("Device",device_pan);
        tp.addTab("Rental", rental_pan);
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


        // Panel for device

        String[] col = {"Model ID", "Type", "Description","Availability", "Reserve", "Expiry Date ","Condition"};
        String[][] row = {{"r001", "resistor", "100 ohms","yes", "NA", "NA","good"} ,
                {"r001", "resistor", "100 ohms","yes", "NA", "NA","good"},
                {"r003", "resistor", "100 ohms","yes", "NA", "NA","good"},
                {"r005", "resistor", "500 ohms","No", "chinhow", "12/8/2020","good"},
                {"r006", "resistor", "500 ohms","yes", "NA", "NA","fair"},
                {"b001", "Bread Board", "Brand A","yes", "NA", "NA","good"},
                {"b002", "Bread Board", "Brand B (Broken)","No", "NA", "NA","poor"}};


        device_table = new JTable();
        device_table.setEnabled(true);
        device_table.setModel(new DefaultTableModel(row,col) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                // need check!!!
                return columnEditables[column];
            }
        });
        //int[] selection = device_table.getSelectedRows();
        //for (int i = 0; i < selection.length; i++) {
        //selection[i] = device_table.convertRowIndexToModel(selection[i]);
        //}
        //device_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JLabel sel_info = new JLabel("You have selected:");
        sel_info.setBounds(50,300,200,25);
        sel_info.setFont(new Font("DIALOG", Font.BOLD, 13));

        textField1 = new JTextField();
        textField1.setBounds(50,330,80,25);
        textField1.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField1.setEditable(false);
        textField2 = new JTextField();
        textField2.setBounds(130,330,70,25);
        textField2.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField2.setEditable(false);
        textField3 = new JTextField();
        textField3.setBounds(200,330,150,25);
        textField3.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField3.setEditable(false);
        textField4 = new JTextField();
        textField4.setBounds(350,330,80,25);
        textField4.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField4.setEditable(false);
        textField5 = new JTextField();
        textField5.setBounds(430,330,70,25);
        textField5.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField5.setEditable(false);
        textField6 = new JTextField();
        textField6.setBounds(500,330,80,25);
        textField6.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField6.setEditable(false);
        textField7 = new JTextField();
        textField7.setBounds(580,330,70,25);
        textField7.setFont(new Font("DIALOG", Font.BOLD, 12));
        textField7.setEditable(false);
        device_pan.add(sel_info);
        device_pan.add(textField1);
        device_pan.add(textField2);
        device_pan.add(textField3);
        device_pan.add(textField4);
        device_pan.add(textField5);
        device_pan.add(textField6);
        device_pan.add(textField7);

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

        // for row selection
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
                    }
                }
        );

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

        // Grouping all the radio button, so that only one can be selected
        ButtonGroup bg = new ButtonGroup();
        bg.add(search_sel);
        bg.add(add_sel);
        bg.add(remove_sel);
        bg.add(edit_sel);

        // Component for search
        searchType_l = new JLabel("Search By");
        searchType_l.setBounds(10,20,100,25);
        search_b = new JButton("Search");
        search_b.setBounds(10,20,100,25);
        String[] type = {"--Select Type--","Model ID", "Item Type"};
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
        sType_l.setBounds(10,71,100,25);
        sType_t = new JTextField();
        sType_t.setBounds(100,70,120,25);


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


        // Components for edit
        JTextArea edit_info = new JTextArea("Note: Please remove and add the item \nif you would like to change the Model ID or Item Type");
        edit_info.setBounds(10,150,300,35);
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
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });


        searchType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search_tem = searchType.getSelectedItem().toString();
                if (search_tem.equals("Model ID")) {
                    sel_pan.remove(sType_l);
                    sel_pan.remove(sType_t);
                    sel_pan.add(sID_t);
                    sel_pan.add(sID_l);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                } else if (search_tem.equals("Item Type")) {
                    sel_pan.remove(sID_l);
                    sel_pan.remove(sID_t);
                    sel_pan.add(sType_l);
                    sel_pan.add(sType_t);
                    sel_pan.revalidate();
                    sel_pan.repaint();
                }
            }
        });

        // Panel for rental
        JLabel tem1 = new JLabel("rental panel!");
        tem1.setBounds(20,20,100,25);
        rental_pan.add(tem1);

        // Panel for account
        JLabel tem2 = new JLabel("Account panel!");
        tem2.setBounds(50,50,100,25);
        account_pan.add(tem2);


        frame.setVisible(true);
        //frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search_b) {
            //search button
            System.out.println("Pressed search button");
        }

        if(e.getSource() == add_b) {
            //add button
            System.out.println("Pressed add button");
        }

        if(e.getSource() == remove_b) {
            //remove button
            System.out.println("Pressed remove button");
        }

        if(e.getSource() == edit_b) {
            //edit button
            System.out.println("Pressed edit button");
        }

        if(e.getSource() == logout) {
            //edit button
            System.out.println("Pressed logout button");
            frame.dispose();
            JOptionPane.showMessageDialog(null, "Log out Successfully", "Log out", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}