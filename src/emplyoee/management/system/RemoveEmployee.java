package emplyoee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RemoveEmployee extends JFrame implements ActionListener {
    Choice cEmpId;
    JButton delete, back;
    JLabel lblname, lblphone, lblemail; // Unko global ya yahan access ke liye sahi rakha

    RemoveEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // 1. Employee ID Label
        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 50, 100, 30);
        add(labelempId);

        // 2. Choice Dropdown (Pehle initialize karna zaroori hai)
        cEmpId = new Choice();
        cEmpId.setBounds(200, 50, 150, 30);
        add(cEmpId);

        // Database se IDs load karna
        try {
            Conn c = new Conn();
            String query = "select * from employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3. Name Labels
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 100, 100, 30);
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(200, 100, 150, 30); // X coordinate 200 kiya taaki overlap na ho
        add(lblname);

        // 4. Phone Labels
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 150, 100, 30);
        add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(200, 150, 150, 30); // X coordinate 200 kiya
        add(lblphone);

        // 5. Email Labels
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 200, 100, 30); // Sahi alignment ke liye X=50 kiya
        add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(200, 200, 150, 30); // X coordinate 200 kiya
        add(lblemail);

        // Pehle se selected employee ka data load karne ke liye initial call
        loadEmployeeData();

        // Dropdown change listener
        cEmpId.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                loadEmployeeData();
            }
        });

        // 6. Buttons
        delete = new JButton("Delete");
        delete.setBounds(80, 300, 100, 30);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        add(delete);

        back = new JButton("Back");
        back.setBounds(220, 300, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        // 7. Image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
            Image i2 = i1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel image = new JLabel(i3);
            image.setBounds(350, 0, 600, 400);
            add(image);
        } catch (Exception e) {
            System.out.println("Image ni mili, check your icons path.");
        }

        setSize(1000, 400);
        setLocation(300, 150);
        setVisible(true);
    }

    // Ek baar data load karne ka common function banaya taaki code repeat na ho
    private void loadEmployeeData() {
        try {
            Conn c = new Conn();
            String query = "select * from employee where empId='" + cEmpId.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Sahi spelling ke saath actionPerformed method
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "delete from employee where empId = '" + cEmpId.getSelectedItem() + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Employee Information Deleted Successfully");
                setVisible(false);
                new Home(); // Make sure Home class exists
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home(); // Make sure Home class exists
        }
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}