package com.company;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class MainFrame extends JFrame {
    JButton buttonAdd = new JButton("Add New device");
    JButton buttonSearch = new JButton("Search device");
    JButton buttonSort = new JButton("Sort device by Name");

    JList<Device> listD = new JList<>();
    deviceList<Device> listModel;
    java.util.List<Device> Ds = new ArrayList<>();

    public MainFrame() {
        super("Electronic component inquiry page ");

        initComponents();

        setSize(700, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

        void initComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));

        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addDevice();
            }
        });

        buttonSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sortDs();
            }
        });

        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                searchDs();
            }
        });

        panelButton.add(buttonAdd);
        panelButton.add(buttonSearch);
        panelButton.add(buttonSort);

        add(panelButton);

        listD.setPreferredSize(new Dimension(500, 360));

        listModel = new deviceList<Device>(Ds);
        listD.setModel(listModel);

        listModel.addElement(new Device("10 Ohm Resistor"));

        add(listD);
    }

    private void addDevice() {
        String dName = JOptionPane.showInputDialog(this, "Enter the device name");
        if (dName != null) {
            listModel.addElement(new Device(dName));
        }
    }

    private void sortDs() {
        Collections.sort(Ds);
        listModel.fireDataChanged();
    }

    private void searchDs() {
        String dName = JOptionPane.showInputDialog(this, "Enter the device name to search for:");

        if (dName == null) {
            return;
        }

        Collections.sort(Ds);

        int foundIndex = Collections.binarySearch(Ds, new Device(dName));

        if (foundIndex >= 0) {
            listD.setSelectedIndex(foundIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Could not find the device " + dName);
        }
    }
}