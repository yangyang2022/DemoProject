package com.yangyang.web.demo;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Demo extends JFrame {

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
    private void showError(){
        JOptionPane.showMessageDialog(null,"输入信息不合法!!");
    }
    private void addTestUser(){
        Arrays.asList("习近平1 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "习近平2 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "习近平3 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "习近平4 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                "习近平5 男 66 电子一班 20110803 1891891189 145@qq.com 88"
                //"习近平6 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                //"习近平7 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                //"习近平8 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                //"习近平9 男 66 电子一班 20110803 1891891189 145@qq.com 88",
                //"习近平10 男 66 电子一班 20110803 1891891189 145@qq.com 88"
        ).forEach(e->maps.put(e.split(" ")[0],e));
    }
    public Demo(){
        this.setSize(420,300);
        this.setLocation(400,400);
        message_warning = new JLabel("信息格式(空格分开): 姓名 性别 年龄 班级 学号 电话 邮箱 成绩");
        message_label = new JLabel("输入信息: ");
        message_textField = new JTextField(28);
        search_button = new JButton("按姓名查询");
        all_users_button = new JButton("所有用户");
        test_users_button = new JButton("生成 10 个测试用户");

        search_text = new JTextField(10);
        add_msg = new JButton("添加信息");
        add_msg.addActionListener((e)-> {

            String msg = message_textField.getText();
            String name = msg.split(" ")[0];
            int length = msg.split(" ").length;
            if(length != 8 ) {
                System.out.println("length: "+length);showError();return;}
            addMsg(name,msg);
            System.out.println("map.size: "+maps.size());
        });
        show_msg = new JLabel("");
        search_button.addActionListener((e)->show_msg.setText(getMsg(search_text.getText())));
        all_users_button.addActionListener((e)->allUser());
        test_users_button.addActionListener(e->addTestUser());

        this.setLayout(new FlowLayout());
        this.add(message_warning);this.add(message_label);
        this.add(message_textField);this.add(add_msg);
        this.add(test_users_button);this.add(all_users_button);
        this.add(search_button);this.add(search_text);
        this.add(show_msg);

        this.setTitle("信息输入");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        new Demo();
    }
}
