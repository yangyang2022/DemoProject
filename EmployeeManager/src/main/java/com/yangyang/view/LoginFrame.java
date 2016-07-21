package com.yangyang.view;

import com.yangyang.Utils.EmpUtil;
import com.yangyang.model.UserDao;
import com.yangyang.model.EmpException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginFrame extends JFrame{
    private JPanel jp1,jp2,jp3;
    private JLabel jl1,jl2;
    private JTextField jtf;
    private JPasswordField jpf;
    private JButton jb;

    private UserDao ud;

    public LoginFrame(){
        this.setSize(360,160);
        this.setLocation(400,200);
        this.setTitle("员工管理系统登录");
        this.setLayout(new GridLayout(3,1));

        jp1 = new JPanel();jp2 = new JPanel();jp3 = new JPanel();

        jl1 = new JLabel("用户姓名");
        jl2 = new JLabel("用户密码");
        jtf = new JTextField(20);
        jpf = new JPasswordField(20);
        jb = new JButton("登录");

        jb.addActionListener(new LoginClick());
        jpf.addKeyListener(new LoginKeyPress());

        jp1.add(jl1);jp1.add(jtf);
        jp2.add(jl2);jp2.add(jpf);
        jp3.add(jb);

        this.add(jp1);this.add(jp2);this.add(jp3);

        ud = new UserDao();

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void close(){
        this.setVisible(false);
    }
    private void login(){
        String username = jtf.getText();
        String password = new String(jpf.getPassword());
        try {
            ud.login(username,password);
            new ManagerFrame(username);
            close();
        } catch (EmpException e1) {
            EmpUtil.showErrorMsg(jp1,e1.getMessage());
        }
    }
    private class LoginKeyPress extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                login();
            }
        }
    }
    private class LoginClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jb){
                //login
                login();
            }
        }
    }
    public static void main(String[] args) {
        new LoginFrame();
    }
}
