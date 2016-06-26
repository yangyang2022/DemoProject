package com.yangyang.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerFrame extends JFrame {
    private JMenuBar jmb;
    private JMenu jm1,jm2,jm3,jm4;
    private JMenuItem jmi1,jmi2,jmi3,jmi4;

    private JPanel jp1;
    private UserPanel up;
    private DepPanel dep;
    private EmpPanel emp;

    public DepPanel getDep() {
        return dep;
    }

    public EmpPanel getEmp() {
        return emp;
    }
    public ManagerFrame(String username){
        this.setSize(560,400);
        this.setLocation(400,150);
        this.setTitle("欢迎使用雇员管理系统,当前管理者 [ "+username+" ] ");
        jmb = new JMenuBar();
        jm1 = new JMenu("部门管理");
        jm2 = new JMenu("员工管理");
        jm3 = new JMenu("用户管理");
        jm4 = new JMenu("系统管理");

        jmi1 = new JMenuItem("部门信息管理");
        jmi2 = new JMenuItem("员工信息管理");
        jmi3 = new JMenuItem("用户信息管理");
        jmi4 = new JMenuItem("退出系统");

        jmi1.addActionListener(new MenuClick());
        jmi2.addActionListener(new MenuClick());
        jmi3.addActionListener(new MenuClick());
        jmi4.addActionListener(new MenuClick());

        jm1.add(jmi1);jm2.add(jmi2);jm3.add(jmi3);jm4.add(jmi4);
        jmb.add(jm1);jmb.add(jm2);jmb.add(jm3);jmb.add(jm4);

        this.add(jmb, BorderLayout.NORTH);

        jp1 = new JPanel(new BorderLayout());
        up = new UserPanel(this);
        dep = new DepPanel(this);
        emp = new EmpPanel(this);

        this.add(jp1);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void close(){
        this.setVisible(false);
    }
    private class MenuClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            jp1.removeAll();
            if(e.getSource() == jmi1 ){
                //部门管理
                jp1.add(dep);
            }else if(e.getSource() == jmi2){
                //员工管理
                jp1.add(emp);
            }else if(e.getSource() == jmi3){
                //用户管理
                jp1.add(up);
            }else if(e.getSource() == jmi4){
                //close
                close();
                new LoginFrame();
            }
            jp1.updateUI();
        }
    }
}
