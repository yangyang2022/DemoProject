package com.yangyang.Test;

import com.yangyang.Utils.DBUtils;
import org.junit.Test;

import java.sql.Connection;

public class TestCon {
    @Test
    public void testCon() {
        Connection con = DBUtils.getConnetion();
        if(con != null){
            System.out.println("ok");
        }else {
            System.out.println("fail");
        }
    }
}
