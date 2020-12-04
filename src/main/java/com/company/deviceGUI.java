package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class deviceGUI extends Component implements ActionListener {
    private static JFrame frame;
    private static JPanel main_panel, device_pan, rental_pan, account_pan;
    private static JLabel title, user_label, pass_label;
    private static JTabbedPane tp;
    private static JTable device_table;
    private static JScrollPane scroll_pane;

    public void deviceWindow() {
        //Getting the account type from Login
        loginGUI temp = new loginGUI();
        String actype = temp.getType();

        frame = new JFrame("Main Page - " + actype);
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        main_panel = new JPanel();
        main_panel.setLayout(null);
        //frame.add(main_panel);

        title = new JLabel("Main");
        title.setForeground(new Color(0, 128, 255));
        title.setFont(new Font("DIALOG", Font.BOLD, 24));
        title.setBounds(120, 50, 350, 40);
        //main_panel.add(title);
        frame.add(title);

        tp = new JTabbedPane(JTabbedPane.TOP);
        tp.setBounds(50,100,900,600);
        Font font = new Font("DIALOG", Font.CENTER_BASELINE, 20);
        tp.setFont(font);
        device_pan = new JPanel();
        device_pan.setLayout(null);
        rental_pan = new JPanel();
        rental_pan.setLayout(null);
        account_pan = new JPanel();
        account_pan.setLayout(null);
        
        tp.addTab("a",device_pan);
        tp.addTab("b", rental_pan);
        tp.addTab("c", account_pan);
       
        
        frame.add(tp);

        // Panel for device
        user_label = new JLabel("hsssi");
        user_label.setFont(new Font("DIALOG", Font.BOLD, 15));
        user_label.setBounds(50, 50, 350, 40);
        //device_pan.add(user_label);


        String[] col = {"Name", "ID", "email","Name", "ID", "email","Name", "ID", "email"};
        String[][] row = {{"hifref", "001", "a@gmail.com","hifref", "001", "a@gmail.com","hifref", "001", "a@gmail.com"} , 
        				  {"jjdw", "002", "ppp","jjdw", "002", "ppp","jjdw", "002", "ppp"}, 
        				  {"jjdwdw", "002", "ppp","jjdwdw", "002", "ppp","jjdwdw", "002", "ppp"},
        				  {"jjdw", "002", "ppp","jjdw", "002", "ppp","jjdw", "002", "ppp"},
        				  {"jjdw", "002", "ppp","jjdw", "002", "ppp","jjdw", "002", "ppp"},
        				  {"ferwgr", "002", "ppp","jjdwedw", "002", "ppp","jjdwedw", "002", "ppp"},
        				  {"ferwgr", "002", "ppp","jjdwedw", "002", "ppp","jjdwedw", "002", "ppp"},
        				  {"ferwgr", "002", "ppp","jjdwedw", "002", "ppp","jjdwedw", "002", "ppp"}};


        // not working
//        DefaultTableModel model = new DefaultTableModel();
//        device_table = new JTable(row,col);
        device_table = new JTable();
        device_table.setEnabled(false);
        device_table.setModel(new DefaultTableModel(row,col) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
        
        JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50,100,700,300);
		scrollPane.setViewportView(device_table);
		device_pan.add(scrollPane);
        //scroll_pane = new JScrollPane(device_pan);
        //scroll_pane.setVisible(true);


        device_table.setPreferredScrollableViewportSize(new Dimension(100,200));
        device_table.setBounds(400,50,450,50);
        device_table.setFillsViewportHeight(true);
        device_table.setRowHeight(72);
        device_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        device_table.setAutoCreateRowSorter(true);

        //frame.add(device_table);


        // Panel for rental

        // Panel for account



        frame.setVisible(true);
        //frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
