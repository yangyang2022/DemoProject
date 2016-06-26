package com.yangyang.view;

import com.yangyang.Utils.EmpUtil;
import com.yangyang.model.Dep;
import com.yangyang.model.Emp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EmpPanel extends JPanel {
    private JPanel jp1,jp2;
    private JLabel jl1;
    private JTable jt;
    private JScrollPane jsp;
    private JButton jb1,jb2,jb3;

    private EmpTableMoDel em;
    private ManagerFrame jf;
    private AddDialog ad;
    private UpdateDialog ud;

    //add serach
    private JComboBox jcb_s;
    private DefaultComboBoxModel jcbm_s;
    private JLabel jl1_s,jl2_s;
    private JTextField jtf_s;
    private JButton jb_s;

    public JTable getJt() {
        return jt;
    }

    public EmpTableMoDel getEm() {
        return em;
    }

    public Vector<Dep> initEmps(){
        em = new EmpTableMoDel();
        Dep defaultEep = new Dep(0,"全部数据");
        java.util.List<Dep> deps = em.getDepDao().list();
        deps.add(0,defaultEep);

        Vector<Dep> allDeps = new Vector<>(deps);
        return allDeps;
    }
    private void initCombox(){
        for(Dep dep : initEmps()){
            jcbm_s.addElement(dep);
        }
        //jcbm_s = new DefaultComboBoxModel(initEmps());
        jcb_s = new JComboBox(jcbm_s);
    }
    public void updateCombox(Dep dep){
        jcbm_s.addElement(dep);
        jcb_s.setModel(jcbm_s);
        jcb_s.invalidate();
        jcb_s.validate();
        jcb_s.updateUI();
    }
    public void deleteCombox(Dep dep){
        jcbm_s.removeElement(dep);
        jcb_s.setModel(jcbm_s);
        jcb_s.invalidate();
        jcb_s.validate();
        jcb_s.updateUI();
    }
    public EmpPanel(ManagerFrame jf){
        this.jf = jf;
        this.setLayout(new BorderLayout());
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel("员工管理界面");
        jl1_s= new JLabel("筛选部门");
        jl2_s = new JLabel("输入筛选姓名");
        jtf_s = new JTextField(10);

        jb_s = new JButton("筛选");
        jb_s.addActionListener(new EmpClick());

        jb1 = new JButton("添加员工");
        jb2 = new JButton("删除员工");
        jb3 = new JButton("修改员工");

        jb1.addActionListener(new EmpClick());
        jb2.addActionListener(new EmpClick());
        jb3.addActionListener(new EmpClick());

        //jp1.add(jl1,BorderLayout.NORTH);
        jcb_s = new JComboBox();
        jcbm_s = new DefaultComboBoxModel();
        initCombox();
        jcb_s.setModel(jcbm_s);

        jt = new JTable(em);
        jsp = new JScrollPane(jt);

        jp1.add(jl1);jp1.add(jl1_s);jp1.add(jcb_s);
        jp1.add(jl2_s);jp1.add(jtf_s);jp1.add(jb_s);

        jp2.add(jb1);jp2.add(jb2);jp2.add(jb3);

        this.add(jsp);
        this.add(jp1,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
    }
    private void showAddDialog(){
        ad = new AddDialog();
        ad.setVisible(true);
    }
    private Emp getSelectEmp(){
        int row = jt.getSelectedRow();
        if(row < 0 ) {
            EmpUtil.showErrorMsg(jp1,"必须选择要更新的用户");
            return null;
        }
        int id = Integer.parseInt(em.getRowDatas().get(row).get(0));
        Emp emp = em.getEmpDao().load(id);
        return emp;
    }
    private void showUpdateDialog(){
        Emp emp = getSelectEmp();
        if(emp != null){
            ud = new UpdateDialog(emp);
            ud.setVisible(true);
        }
    }
    private void refreshDep(){
        jf.getDep().getDm().initData();
        jf.getDep().getJt().updateUI();
    }
    public void refreshTable(){
        em.initData();
        jt.updateUI();
        refreshDep();
    }
    private void filterEmp(){
        int depId = ((Dep)jcb_s.getSelectedItem()).getId();
        String name = jtf_s.getText();
        //System.out.println(depId+" : "+name);
        em.initData(depId,name);
        jt.updateUI();
    }
    private void deleteEmp(){

        int confirm = JOptionPane.showConfirmDialog(jp1,"确定要删除这个员工吗? ");
        if(confirm == JOptionPane.YES_OPTION){
            int row = jt.getSelectedRow();
            if (row < 0 ) {
                EmpUtil.showErrorMsg(jp1,"必选选中一个删除用户!");
                return;
            }
            String id = em.getRowDatas().get(row).get(0);

            em.getEmpDao().delete(Integer.parseInt(id));
            refreshTable();
        }
    }
    private class EmpClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == jb1 ) {
                //add
                showAddDialog();
            }else if(e.getSource() == jb2 ) {
                //delete
                deleteEmp();
            }else if(e.getSource() == jb3 ) {
                //update
                showUpdateDialog();
            }else if(e.getSource() == jb_s){
                //serach by name
                filterEmp();
            }
        }
    }
    private class AddDialog extends JDialog{
        private JPanel jp1,jp2,jp3,jp4,jp5;
        private JLabel jl1,jl2,jl3,jl4;
        private JTextField jtf1,jtf2;
        private JComboBox jcb;
        private DefaultComboBoxModel dcm;
        private JRadioButton jr1,jr2;
        private ButtonGroup bg;
        private JButton jb1,jb2;

        private void initCombox(){
            dcm = new DefaultComboBoxModel();
            java.util.List<Dep> deps = em.getDepDao().list();
            Vector<Dep> allDeps = new Vector<>(deps);
            jcb = new JComboBox(allDeps);
        }
        private void initRiaoButton(){
            jr1 = new JRadioButton("男",true);
            jr2 = new JRadioButton("女");
            bg = new ButtonGroup();
            bg.add(jr1);bg.add(jr2);
        }
        public AddDialog(){
            this.setSize(450,400);
            this.setLocation(jf.getX()+50,jf.getY()+50);
            this.setModal(true);
            this.setTitle("添加员工");

            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4 = new JPanel();
            jp5 = new JPanel();

            jl1 = new JLabel("姓名:");
            jl2 = new JLabel("性别:");
            jl3 = new JLabel("薪水:");
            jl4 = new JLabel("所在部门:");
            jtf1 = new JTextField(20);
            jtf2 = new JTextField(20);

            jb1 = new JButton("添加员工");
            jb2 = new JButton("重置数据");
            
            jb1.addActionListener(new AddClick());
            jb2.addActionListener(new AddClick());
            
            initCombox();
            initRiaoButton();

            this.setLayout(new GridLayout(5,1));

            jp1.add(jl1);jp1.add(jtf1);             //name
            jp2.add(jl2);jp2.add(jr1);jp2.add(jr2); //sex
            jp3.add(jl3);jp3.add(jtf2);
            jp4.add(jl4);jp4.add(jcb);
            jp5.add(jb1);jp5.add(jb2);

            this.add(jp1);this.add(jp2);
            this.add(jp3);this.add(jp4);
            this.add(jp5);
        }
        private void reset(){
            jtf1.setText("");
            jtf2.setText("");
            jr1.setSelected(true);
            jcb.setSelectedIndex(0);
        }
        private void addEmp(){
            String name = jtf1.getText();
            if(EmpUtil.checkIsEmpty(name)){
                EmpUtil.showErrorMsg(jp1,"输入的姓名不能为空!");
                return;
            }

            String sex = "男";
            if(jr2.isSelected()) sex = "女";

            String salary = jtf2.getText();
            if(!salary.matches("\\d+\\.?\\d+")){
                EmpUtil.showErrorMsg(null,"输入的 薪水 必须为数字");
                return;
            }
            //double salary = 0;
            //try {
            //    salary = Double.valueOf(jtf2.getText());
            //} catch (NumberFormatException e) {
            //    showErrorMsg(null,"输入的必须为数字");
            //    return;
            //}
            int depId = ((Dep)jcb.getSelectedItem()).getId();
            Emp emp = new Emp(name,sex,Integer.parseInt(salary));
            em.getEmpDao().add(emp,depId);
            reset();
            this.setVisible(false);
        }
        private class AddClick implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jb1){
                    //add
                    addEmp();
                }else if(e.getSource() == jb2){
                    //reset
                    reset();
                }
                refreshTable();
            }
        }
    }
    private class UpdateDialog extends JDialog{
        private JPanel jp1,jp2,jp3,jp4,jp5;
        private JLabel jl1,jl2,jl3,jl4;
        private JTextField jtf1,jtf2;
        private JComboBox jcb;
        private DefaultComboBoxModel dcm;
        private JRadioButton jr1,jr2;
        private ButtonGroup bg;
        private JButton jb1,jb2;

        private Emp emp;

        private void initCombox(){
            dcm = new DefaultComboBoxModel();
            java.util.List<Dep> deps = em.getDepDao().list();
            Vector<Dep> allDeps = new Vector<>(deps);
            jcb = new JComboBox(allDeps);
            setJcbSelected();
        }
        private void initRiaoButton(){
            jr1 = new JRadioButton("男",true);
            jr2 = new JRadioButton("女");
            bg = new ButtonGroup();
            bg.add(jr1);bg.add(jr2);
        }
        public UpdateDialog(Emp emp){
            this.setSize(450,400);
            this.setLocation(jf.getX()+50,jf.getY()+50);
            this.setModal(true);
            this.setTitle("更新员工 [ "+emp.getUsername()+" ]");
            this.emp = emp;
            jp1 = new JPanel();
            jp2 = new JPanel();
            jp3 = new JPanel();
            jp4 = new JPanel();
            jp5 = new JPanel();

            jl1 = new JLabel("姓名:");
            jl2 = new JLabel("性别:");
            jl3 = new JLabel("薪水:");
            jl4 = new JLabel("所在部门:");
            jtf1 = new JTextField(emp.getUsername(),20);
            jtf2 = new JTextField(emp.getSalary()+"",20);

            jb1 = new JButton("更新员工");
            jb2 = new JButton("重置数据");

            jb1.addActionListener(new UpdateClick());
            jb2.addActionListener(new UpdateClick());

            initCombox();
            initRiaoButton();

            this.setLayout(new GridLayout(5,1));

            jp1.add(jl1);jp1.add(jtf1);             //name
            jp2.add(jl2);jp2.add(jr1);jp2.add(jr2); //sex
            jp3.add(jl3);jp3.add(jtf2);
            jp4.add(jl4);jp4.add(jcb);
            jp5.add(jb1);jp5.add(jb2);

            this.add(jp1);this.add(jp2);
            this.add(jp3);this.add(jp4);
            this.add(jp5);
        }
        private void setJcbSelected(){
            jcb.setSelectedItem(em.getDepDao().load(emp.getDepId()));
        }
        private void reset(){
            jtf1.setText(emp.getUsername());
            jtf2.setText(emp.getSalary()+"");

            if(emp.getSex().equals("男")){
                jr1.setSelected(true);
            }else {
                jr2.setSelected(true);
            }
            setJcbSelected();
            //jcb.setSelectedIndex(0);
        }
        private int getSelectJcbId(){
            Dep dep = (Dep)jcb.getSelectedItem();
            return dep.getId();
        }
        private void updateEmp(){
            int depId = 0;
            try {
                depId = getSelectJcbId();
            } catch (Exception e) {
                EmpUtil.showErrorMsg(jp1,"必须选中一个更新的用户");
                return;
            }
            emp.setUsername(jtf1.getText());
            String sex = "男";
            if(jr2.isSelected()) sex = "女";
            emp.setSex(sex);
            String salary = jtf2.getText();
            if(!salary.matches("\\d+\\.?\\d+")){
                EmpUtil.showErrorMsg(jp1,"输入的 薪水 不合法!,薪水必须为数字!");
                return;
            }
            emp.setSalary(Double.valueOf(jtf2.getText()));
            emp.setDepId(depId);
            em.getEmpDao().update(emp,depId);
            this.setVisible(false);
        }
        private class UpdateClick implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == jb1){
                    //add
                    updateEmp();
                }else if(e.getSource() == jb2){
                    //reset
                    reset();
                }
                refreshTable();
            }
        }
    }
}
