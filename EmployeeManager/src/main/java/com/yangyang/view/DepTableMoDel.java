package com.yangyang.view;

import com.yangyang.dao.DepDao;
import com.yangyang.model.Dep;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class DepTableMoDel extends AbstractTableModel {
    private DepDao depDao;
    private Vector<String> coloumNames;
    private Vector<Vector<String>> rowDatas;

    public DepDao getDepDao() {
        return depDao;
    }

    public Vector<Vector<String>> getRowDatas() {
        return rowDatas;
    }

    public DepTableMoDel(){
        depDao = new DepDao();
        initData();
    }
    public void initData(){

        coloumNames = new Vector<>();
        rowDatas = new Vector<>();
        coloumNames.add("部门标识");
        coloumNames.add("部门名称");
        coloumNames.add("部门人数");

        List<Dep> users = depDao.list();

        Vector<String> ue ;
        for(Dep dep : users){
            ue = new Vector<>();
            ue.add(dep.getId()+"");
            ue.add(dep.getDepName());
            ue.add(depDao.getDepEmpNums(dep.getId())+"");
            rowDatas.add(ue);
        }
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
