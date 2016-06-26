package com.yangyang.test;

import javax.swing.*;

public class TestTable extends JFrame {
    private JTable jt;
    private JScrollPane jsp;
    public TestTable(){
        this.setSize(500,400);
        this.setLocation(400,150);
        jt = new JTable(new MyTableModel());
        jsp = new JScrollPane(jt);
        this.add(jsp);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new TestTable();
    }
}
