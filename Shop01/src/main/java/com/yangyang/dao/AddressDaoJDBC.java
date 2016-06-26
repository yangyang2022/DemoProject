package com.yangyang.dao;

import com.yangyang.Utils.DBUtils;
import com.yangyang.model.Address;
import com.yangyang.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoJDBC implements IAddressDao{
    private static final String table_name = "t_address";
    @Override
    public void add(Address address, int user_id) {
        Connection con = null;
        PreparedStatement ps = null;
        //name phone post_date user_id
        String sql = DBUtils.InserInto+table_name+" values(null,?,?,?,?) ";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);

            ps.setString(1,address.getName());
            ps.setString(2,address.getPhone());
            ps.setString(3,address.getPostcode());
            ps.setInt(4,user_id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Address load(int id) {
        return null;
    }

    private Address rs2Address(ResultSet rs) throws SQLException {
        Address address = new Address();
        User user = new User();
        address.setId(rs.getInt("a_id"));
        address.setName(rs.getString("name"));
        address.setPhone(rs.getString("phone"));
        address.setPostcode(rs.getString("postcode"));
        user.setId(rs.getInt("u_id"));
        user.setNickname(rs.getString("nickname"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setType(rs.getInt("type"));
        address.setUser(user);
        return address;
    }
    @Override
    public List<Address> list(int user_id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<Address> addresses = new ArrayList<>();

        String sql = "SELECT *,t1.id 'a_id',t2.id  'u_id' FROM t_address t1 LEFT JOIN t_user t2 ON(t1.user_id = t2.id) WHERE user_id=?";

        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,user_id);
            rs = ps.executeQuery();

            while (rs.next()){
                addresses.add(rs2Address(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return addresses;
    }
}
