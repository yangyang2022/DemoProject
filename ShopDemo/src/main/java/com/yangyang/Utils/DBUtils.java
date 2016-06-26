package com.yangyang.Utils;

import java.sql.*;

public class DBUtils {

    public static Connection getConnetion(){
        String username = "root";
        String password = "123123";
        String url = "jdbc:mysql://127.0.0.1:3306/shop?useUnicode=true&characterEncoding=utf8";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
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
