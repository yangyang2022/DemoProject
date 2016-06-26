package com.yangyang.view;

import com.yangyang.Utils.EmpUtil;
import com.yangyang.model.User;
import com.yangyang.model.EmpException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel {
    private JPanel jp1,jp2;
    private JLabel jl1;
    private JTable jt;
    private JButton jb1,jb2,jb3;
    private JScrollPane jsp;
    private  AddDialog ad;
    private UpdateDialog ud;

    private UserTableMoDel um;
    private ManagerFrame jf;

    public UserPanel(ManagerFrame jf){
        this.jf = jf;
        this.setLayout(new BorderLayout());
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel("用户管理界面");
        jb1 = new JButton("添加用户");
        jb2 = new JButton("删除用户");
        jb3 = new JButton("修改用户");

        jb1.addActionListener(new UserManagerClick());
        jb2.addActionListener(new UserManagerClick());
        jb3.addActionListener(new UserManagerClick());

        jp1.add(jl1);
        jp2.add(jb1);jp2.add(jb2);jp2.add(jb3);

        um = new UserTableMoDel();
        jt = new JTable(um);
        jsp = new JScrollPane(jt);
        this.add(jsp);

        ad = new AddDialog();

        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);

    }

    private void freshTable(){
        //um = new UserTableMoDel();
        //jt.setModel(um);
        um.initData();
        jt.updateUI();
    }
    private void deleteUser(){
        int row = jt.getSelectedRow();
        if(row < 0 ){
            EmpUtil.showErrorMsg(null,"必须选中要删除的一行");
            return;
        }
        String username = um.getRowDatas().get(row).get(0);
        um.getUserDao().delete(username);
        freshTable();
    }
    private void updateUser(){
        int row = jt.getSelectedRow();
        if(row < 0 ){
            EmpUtil.showErrorMsg(null,"必须选中要更新的用户");
            return;
        }
        String username = um.getRowDatas().get(row).get(0);
        User u = um.getUserDao().load(username);
        ud = new UpdateDialog(u);
        ud.setVisible(true);
    }
    private class UserManagerClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jb1 ) {
                //add
                ad.setVisible(true);
            }else if(e.getSource() == jb2 ) {
                //delete
                deleteUser();
            }else if(e.getSource() == jb3){
                //update
                updateUser();
            }
        }
    }
    private class UpdateDialog extends JDialog{
        private JLabel jl1,jl2,jl3;
        private JButton jb1,jb2;
        private JPanel jp1,jp2,jp3,jp4;
        private JTextField jtf1;
        private JPasswordField jps;
        private User user;

        public UpdateDialog(User user){

            this.setSize(400,180);
            this.setLocation(460,200);
            this.setTitle("正在更新 [ "+user.getUsername()+" ] 信息");
            this.setLayout(new GridLayout(4,1));

            this.setModal(true);//设置模态对话框
            this.setResizable(false);

            this.user = user;

            jp1 = new JPanel();jp2 = new JPanel();jp3 = new JPanel();jp4 = new JPanel();
            jl1 = new JLabel("用户姓名: [ "+user.getUsername()+" ]");jl2 = new JLabel("用户密码");jl3 = new JLabel("用户昵称");
            jtf1 = new JTextField(user.getNickname(),20);
            jps = new JPasswordField(user.getPassword(),20);

            jb1 = new JButton("更新用户");
            jb2 = new JButton("重置数据");
            jb1.addActionListener(new UpdateDialogClick());
            jb2.addActionListener(new UpdateDialogClick());

            jp1.add(jl1);
            jp2.add(jl2);jp2.add(jps);
            jp3.add(jl3);jp3.add(jtf1);
            jp4.add(jb1);jp4.add(jb2);

            this.add(jp1);this.add(jp2);
            this.add(jp3);this.add(jp4);
        }
        private class UpdateDialogClick implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(e.getSource() == jb1){
                        //add
                        String username = user.getUsername();
                        String password = new String(jps.getPassword());
                        if(EmpUtil.checkIsEmpty(password)){
                            EmpUtil.showErrorMsg(null,"密码不能为空!");
                            return;
                        }
                        String nickname = jtf1.getText();

                        User user = new User(username,password,nickname);
                        um.getUserDao().update(user);
                        ud.setVisible(false);//添加成功后 消失
                        reset();
                        freshTable();//刷新table
                    }else if(e.getSource() == jb2 ){
                        //reset
                        reset();
                    }
                } catch (EmpException e1) {
                    EmpUtil.showErrorMsg(null,e1.getMessage());
                }
            }
        }
        private void reset(){
            jtf1.setText(user.getNickname());
            jps.setText(user.getPassword());
        }
    }
    private class AddDialog extends JDialog{
        private JLabel jl1,jl2,jl3;
        private JButton jb1,jb2;
        private JPanel jp1,jp2,jp3,jp4;
        private JTextField jtf1,jtf2;
        private JPasswordField jps;

        public AddDialog(){

            this.setSize(400,180);
            this.setLocation(460,200);
            this.setTitle("添加用户信息");
            this.setLayout(new GridLayout(4,1));

            this.setModal(true);//设置模态对话框
            this.setResizable(false);

            jp1 = new JPanel();jp2 = new JPanel();jp3 = new JPanel();jp4 = new JPanel();
            jl1 = new JLabel("用户姓名");jl2 = new JLabel("用户密码");jl3 = new JLabel("用户昵称");
            jtf1 = new JTextField(20);
            jps = new JPasswordField(20);
            jtf2 = new JTextField(20);

            jb1 = new JButton("添加用户");
            jb2 = new JButton("重置数据");
            jb1.addActionListener(new AddDialogClick());
            jb2.addActionListener(new AddDialogClick());

            jp1.add(jl1);jp1.add(jtf1);
            jp2.add(jl2);jp2.add(jps);
            jp3.add(jl3);jp3.add(jtf2);
            jp4.add(jb1);jp4.add(jb2);

            this.add(jp1);this.add(jp2);
            this.add(jp3);this.add(jp4);
        }
        private class AddDialogClick implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(e.getSource() == jb1){
                        //add
                        String username = jtf1.getText();
                        if(EmpUtil.checkIsEmpty(username)){
                            EmpUtil.showErrorMsg(null,"用户名不能为空!");
                            return;
                        }
                        String password = new String(jps.getPassword());
                        if(EmpUtil.checkIsEmpty(password)){
                            EmpUtil.showErrorMsg(null,"密码不能为空!");
                            return;
                        }
                        String nickname = jtf2.getText();
                        User user = new User(username,password,nickname);
                        um.getUserDao().add(user);
                        ad.setVisible(false);//添加成功后 消失
                        reset();
                        freshTable();//刷新table
                    }else if(e.getSource() == jb2 ){
                        //reset
                        reset();
                    }
                } catch (EmpException e1) {
                    EmpUtil.showErrorMsg(null,e1.getMessage());
                }
            }
        }
        private void reset(){
            jtf1.setText("");
            jps.setText("");
            jtf2.setText("");
        }
    }
}
