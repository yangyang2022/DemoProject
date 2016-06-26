package com.yangyang.Utils;

import javax.swing.*;
import java.awt.*;

public class EmpUtil {
    public static void showErrorMsg(Component parent,String msg){
        JOptionPane.showMessageDialog(parent,msg,"发现错误",JOptionPane.ERROR_MESSAGE);
    }
    public static boolean checkIsEmpty(String str){
        return str == null || "".equals(str.trim());
    }
}
