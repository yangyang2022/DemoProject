package com.yangyang.Utils;

import java.sql.*;
import java.util.Properties;

public class DBUtils {

    public static final String InserInto = " insert into ";
    public static final String UpdateSet = " update ";
    public static final String DeleteFrom = " delete from ";
    public static final String SelectAll = " select * from  ";
    public static final String SelectCount = " select count(*) from  ";

    public static final String OrderByPostDateAsc = " order by post_date asc ";
    public static final String OrderByPostDateDesc = " order by post_date desc ";
    public static final String Limit = " limit ?,? ";

    public static Connection getConnetion(){
        Properties prop = PropUtils.getProperties();
        String username = (String) prop.get("jdbc.username");
        String password = (String) prop.get("jdbc.password");
        String url = (String) prop.get("jdbc.url");
        String jdbcDriver = (String) prop.get("jdbc.driverClassName");
        //String username = "root";
        //String password = "123123";
        //String url = "jdbc:mysql://127.0.0.1:3306/msg?useUnicode=true&characterEncoding=utf8";
        Connection con = null;
        try {
            Class.forName(jdbcDriver);
            con = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection con){
        try {
            if(con != null ) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement ps){
        try {
            if(ps != null ) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs){
        try {
            if(rs != null ) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
