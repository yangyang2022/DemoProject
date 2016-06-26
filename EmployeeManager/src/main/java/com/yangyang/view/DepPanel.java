package com.yangyang.view;

import com.yangyang.Utils.EmpUtil;
import com.yangyang.model.Dep;
import com.yangyang.model.EmpException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepPanel extends JPanel {
    private JPanel jp1,jp2;
    private JLabel jl1;
    private JTable jt;
    private JScrollPane jsp;
    private JButton jb1,jb2,jb3;

    private DepTableMoDel dm;
    private ManagerFrame jf;

    private AddDialog ad;
    private UpdateDialog ud;

    public DepTableMoDel getDm() {
        return dm;
    }

    public JTable getJt() {
        return jt;
    }

    //jpanel 默认是 flowlayout
    public DepPanel(ManagerFrame jf){
        this.jf = jf;
        this.setLayout(new BorderLayout());
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel("部门管理界面");

        jb1 = new JButton("添加部门");
        jb2 = new JButton("删除部门");
        jb3 = new JButton("修改部门");

        jb1.addActionListener(new DepClick());
        jb2.addActionListener(new DepClick());
        jb3.addActionListener(new DepClick());

        jp1.add(jl1,BorderLayout.NORTH);
        jp2.add(jb1);jp2.add(jb2);jp2.add(jb3);
        this.add(jp1);

        dm = new DepTableMoDel();
        jt = new JTable(dm);
        jsp = new JScrollPane(jt);

        this.add(jsp);
        this.add(jp2,BorderLayout.SOUTH);
    }
    private void showAddDialog(){
        ad = new AddDialog();
        ad.setVisible(true);
    }
    private int getSelectedId(){
        int row = jt.getSelectedRow();
        if(row < 0 ) {
            EmpUtil.showErrorMsg(null,"必须选中一个用户");
            return -1;
        }
        String id = dm.getRowDatas().get(row).get(0);
        return Integer.parseInt(id);
    }
    private void deleteUser(){
        int id = getSelectedId();

        if(id < 0 ) return;
        try {
            jf.getEmp().deleteCombox(dm.getDepDao().load(id));
            dm.getDepDao().delete(id);
        } catch (EmpException e) {
            EmpUtil.showErrorMsg(jp1,e.getMessage());
        }
    }
    private void showUpdateDialog(){
        int id = getSelectedId();
        if(id < 0 ) return;
        Dep dep = dm.getDepDao().load(id);
        ud = new UpdateDialog(dep);
        ud.setVisible(true);
    }
    private class DepClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jb1){
                // add
                showAddDialog();
            }else if(e.getSource() == jb2){
                //delete
                deleteUser();

            }else if(e.getSource() == jb3){
                //update
                showUpdateDialog();
            }
            refreshTable();
        }
    }
    private void refreshEmp(){
        jf.getEmp().getEm().initData();
        jf.getEmp().getJt().updateUI();
        //jf.getEmp().updateCombox();

    }
    public void refreshTable(){
        dm.initData();
        jt.updateUI();//刷新表格
        refreshEmp();
    }
    private class UpdateDialog extends JDialog{
        private JPanel jp;
        private JLabel jl;
        private JTextField jtf;
        private JButton jb,jb2;
        private Dep tempDep;

        private void reset(){
            jtf.setText("");
            this.setVisible(false);
        }
        public UpdateDialog(Dep tempDep){

            this.setSize(450,100);
            this.setLocation(jf.getX()+100,jf.getY()+50);
            this.setTitle("更新部门");
            this.setModal(true);
            this.tempDep = tempDep;

            jp = new JPanel();
            jl = new JLabel("部门名称");
            jtf = new JTextField(tempDep.getDepName(),20);
            jtf.setSelectionStart(0);
            jtf.setSelectionEnd(tempDep.getDepName().length());

            jb = new JButton("更新");
            jb2 = new JButton("重置");

            jb.addActionListener(new UpdataClick());
            jb2.addActionListener(new UpdataClick());

            jp.add(jl);jp.add(jtf);jp.add(jb);jp.add(jb2);
            this.add(jp);
        }
        private class UpdataClick implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jb){
                    String name = jtf.getText();
                    if(EmpUtil.checkIsEmpty(name)) {
                        EmpUtil.showErrorMsg(null,"部门名称不能为空!");
                        return;
                    }
                    Dep dep = new Dep(tempDep.getId(),name);
                    dm.getDepDao().update(dep);
                    reset();
                }else if(e.getSource() == jb2){
                    jtf.setText(tempDep.getDepName());
                }
            }
        }
    }
    private class AddDialog extends JDialog{
        private JPanel jp;
        private JLabel jl;
        private JTextField jtf;
        private JButton jb;

        private void reset(){
            jtf.setText("");
            this.setVisible(false);
        }
        public AddDialog(){

            this.setSize(400,100);
            this.setLocation(jf.getX()+100,jf.getY()+50);
            this.setTitle("添加部门");
            this.setModal(true);

            jp = new JPanel();
            jl = new JLabel("部门名称");
            jtf = new JTextField(20);
            jb = new JButton("添加");
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == jb){
                        String name = jtf.getText();
                        if(EmpUtil.checkIsEmpty(name)) {
                            EmpUtil.showErrorMsg(null,"部门名称不能为空!");
                            return;
                        }
                        Dep dep = new Dep(name);
                        int id = dm.getDepDao().add(dep);
                        dep.setId(id+1);
                        //刷新员工表的combox数据
                        jf.getEmp().updateCombox(dep);
                        //refreshTable();
                        reset();
                    }
                }
            });
            jp.add(jl);jp.add(jtf);jp.add(jb);
            this.add(jp);
        }
    }
}
