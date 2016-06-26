package com.yangyang.web.demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Demo2 extends JFrame {

    private JLabel message_label;
    private JLabel message_warning;
    private JTextField message_textField;
    private static Map<String,String> maps = new HashMap<>();;
    private JButton search_button;
    private JTextField search_text;
    private JLabel show_msg;
    private JButton add_msg;
    private JButton all_users_button;
    private JButton test_users_button;
    private DefaultListModel listModel;

    private JList user_list;
    private JScrollPane jsp;

    private boolean checkEmpty(String str){
        return str == null || "".equals(str.trim());
    }
    private void addMsg(String name,String msg){
        if(checkEmpty(msg)) {
            JOptionPane.showMessageDialog(null,"请输入信息!");
            return;
        }
        if(maps.get(name) != null){
            JOptionPane.showMessageDialog(null,"用户已经存在! 请重新输入");
            return;
        }
        JOptionPane.showMessageDialog(null,"用户添加成功!");
        maps.put(name,msg);
    }
    private String getMsg(String name){
        if(checkEmpty(name)) {
            JOptionPane.showMessageDialog(null,"请输入合法姓名");
            return "";
        }
        if(maps.get(name) == null){
            JOptionPane.showMessageDialog(null,"用户不存在!");
            return name+" 用户不存在";
        }
        return maps.get(name);
    }
    private void allUser(){
        show_msg.setText("");
        maps.forEach((k,v)->show_msg.setText(show_msg.getText()+"\r\n"+k+"   \r\n"));
    }
    private void showError(String msg){
        JOptionPane.showMessageDialog(null,msg);
    }
    private void addTestUser(){
        List<String> datas = Arrays.asList("Demo1 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo2 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo3 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo4 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo5 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo6 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo7 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo8 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo9 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "Demo10 男 66 电子一班 20110803 1891891189 145@qq.com 88"
        );//.forEach(e->maps.put(e.split(" ")[0],e));
        for(String e:datas){
            maps.put(e.split(" ")[0],e);
        }
    }
    private void showUsers(String username){
        listModel.removeAllElements();
        if(username == null || "".equals(username)){
            for(Map.Entry<String,String> entry:maps.entrySet()){
                listModel.addElement(entry.getKey()+": "+entry.getValue());
            }
        }else{
            if(maps.get(username) == null){
                showError("用户名不存在!!");
                return;
            }
            listModel.addElement(username+": "+maps.get(username));
        }
    }
    public Demo2(){
        this.setSize(500,300);
        this.setLocation(400,400);
        message_warning = new JLabel("信息格式(空格分开): 姓名 性别 年龄 班级 学号 电话 邮箱 成绩");
        message_label = new JLabel("输入信息: ");
        message_textField = new JTextField(28);
        search_button = new JButton("按姓名查询");
        all_users_button = new JButton("所有用户");
        test_users_button = new JButton("生成 10 个测试用户");
        listModel = new DefaultListModel<>();
        listModel.addElement("所有用户");

        user_list = new JList(listModel);
        user_list.setFixedCellWidth(450);
        jsp = new JScrollPane(user_list);

        search_text = new JTextField(10);
        add_msg = new JButton("添加信息");
        add_msg.addActionListener((e)-> {

            String msg = message_textField.getText();
            String name = msg.split(" ")[0];
            int length = msg.split(" ").length;
            if(length != 8 ) {
                System.out.println("length: "+length);showError("输入信息不完整 , 各字段以一个空格分开");return;}
            addMsg(name,msg);
            System.out.println("map.size: "+maps.size());
        });
        show_msg = new JLabel("");
        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUsers(search_text.getText());
            }
        });
        all_users_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUsers("");
            }
        });
        test_users_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTestUser();
            }
        });

        this.setLayout(new FlowLayout());
        this.add(message_warning);this.add(message_label);
        this.add(message_textField);this.add(add_msg);
        this.add(test_users_button);this.add(all_users_button);
        this.add(search_button);this.add(search_text);
        //this.add(show_msg);
        this.add(jsp);

        this.setTitle("信息输入");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Demo2();
    }
}
