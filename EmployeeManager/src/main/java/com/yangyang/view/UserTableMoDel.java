package com.yangyang.view;

import com.yangyang.dao.UserDao;
import com.yangyang.model.User;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class UserTableMoDel extends AbstractTableModel {
    private UserDao userDao;
    private Vector<String> coloumNames;
    private Vector<Vector<String>> rowDatas;

    public UserDao getUserDao() {
        return userDao;
    }

    public Vector<Vector<String>> getRowDatas() {
        return rowDatas;
    }

    public UserTableMoDel(){
        userDao = new UserDao();
        initData();
    }
    public void initData(){

        coloumNames = new Vector<>();
        rowDatas = new Vector<>();
        coloumNames.add("用户姓名");
        coloumNames.add("用户昵称");
        coloumNames.add("用户密码");

        List<User> users = userDao.list();

        Vector<String> ue ;
        for(User user : users){
            ue = new Vector<>();
            ue.add(user.getUsername());
            ue.add(user.getNickname());
            ue.add(user.getPassword());
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
