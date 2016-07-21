package com.yangyang.view;

import com.yangyang.model.DepDao;
import com.yangyang.model.EmpDao;
import com.yangyang.model.Emp;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class EmpTableMoDel extends AbstractTableModel {
    private EmpDao empDao;
    private Vector<Vector<String>> rowDatas;
    private DepDao depDao;

    private Vector<String> coloumNames;

    public EmpDao getEmpDao() {
        return empDao;
    }
    public DepDao getDepDao(){return depDao;}

    public Vector<Vector<String>> getRowDatas() {
        return rowDatas;
    }

    public EmpTableMoDel(){
        empDao = new EmpDao();
        depDao = new DepDao();
        initData();
    }
    public void initData(int depId,String name) {
        coloumNames = new Vector<>();
        rowDatas = new Vector<>();
        coloumNames.add("标识");
        coloumNames.add("姓名");
        coloumNames.add("性别");
        coloumNames.add("薪水");
        coloumNames.add("所在部门");

        List<Emp> users = empDao.list(depId, name);
        if(users == null || users.size() == 0 ) return;
        Vector<String> ue;
        for (Emp emp : users) {
            ue = new Vector<>();
            ue.add(emp.getId() + "");
            ue.add(emp.getUsername());
            ue.add(emp.getSex());
            ue.add(emp.getSalary() + "");
            try {
                ue.add(depDao.load(emp.getDepId()).getDepName());//部门名称
            } catch (Exception e) {
                ue.add("所在部门未知");
            }
            rowDatas.add(ue);
        }
    }
    public void initData(){
        initData(0,"");
    }

    @Override
    public int getRowCount() {
        return rowDatas.size();
    }

    @Override
    public int getColumnCount() {
        return coloumNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowDatas.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return coloumNames.get(column);
    }
}
