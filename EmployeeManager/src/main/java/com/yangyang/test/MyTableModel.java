package com.yangyang.test;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

/**
 * Created by syy on 2016/6/13.
 */
public class MyTableModel extends AbstractTableModel {
    private Vector<String> coloumNames;
    private Vector<Vector<String>> rowDatas;

    public MyTableModel(){
        coloumNames = new Vector<>();
        rowDatas = new Vector<>();
        init();
    }
    private void init(){
        coloumNames.add("用户姓名");
        coloumNames.add("用户密码");
        coloumNames.add("用户昵称");

        Vector<String> ue = new Vector<>();
        ue.add("wukong1");
        ue.add("123123");
        ue.add("wukong");
        rowDatas.add(ue);
        ue = new Vector<>();
        ue.add("wukong2");
        ue.add("123123");
        ue.add("wukong");
        rowDatas.add(ue);
        ue = new Vector<>();
        ue.add("wukong3");
        ue.add("123123");
        ue.add("wukong");
        rowDatas.add(ue);
        ue = new Vector<>();
        ue.add("wukong4");
        ue.add("123123");
        ue.add("wukong");

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
    //说明每一列的名称
    @Override
    public String getColumnName(int column) {
        return coloumNames.get(column);
    }
}
