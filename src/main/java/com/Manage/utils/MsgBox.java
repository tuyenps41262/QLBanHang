
package com.Manage.utils;

import java.awt.Component;
import javax.swing.JOptionPane;


public class MsgBox {
    public static void alert(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message,"Hệ thống quản lý bán hàng",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static boolean confirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message,"Hệ thống quản lý bán hàng",JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return result ==JOptionPane.YES_NO_OPTION;
    }
    
    public static String prompt(Component parentComponent,String message){
        return JOptionPane.showInputDialog(parentComponent,message,"Hệ thống quản lý bán hàng",JOptionPane.INFORMATION_MESSAGE);
    }
}
