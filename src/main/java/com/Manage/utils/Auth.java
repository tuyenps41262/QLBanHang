
package com.Manage.utils;
import com.Manage.entity.TaiKhoan;
public class Auth {
      // Thuộc tính tĩnh dùng để lưu trữ thông tin về người dùng đang đăng nhập
    public static TaiKhoan user= null;
//     * Phương thức dùng để xóa thông tin về người dùng đang đăng nhập
//     * (thiết lập giá trị của `user` thành `null`)
    public static void clear(){
        Auth.user= null;
    }
//    Phương thức kiểm tra xem người dùng đã đăng nhập chưa
//     * (trả về `true` nếu `user` không bằng `null`, nghĩa là đã đăng nhập)
//     * @return boolean
    public static boolean isLogin(){
       return Auth.user !=null;
    }
//    Phương thức kiểm tra xem người dùng đã đăng nhập và có vai trò là "Admin" chưa
//     * (trả về `true` nếu `isLogin()` là `true` và vai trò của người dùng là "Admin")
//     * @return boolean
    public static boolean isAdmin(){
        return Auth.isLogin() && user.getVaitro().equals("Admin");
    }
//     Phương thức kiểm tra xem người dùng đã đăng nhập và có vai trò là "User" chưa
//     * (trả về `true` nếu `isLogin()` là `true` và vai trò của người dùng là "User")
//     * @return boolean
     public static boolean isUser(){
        return Auth.isLogin() && user.getVaitro().equals("User");
    }
//      Phương thức kiểm tra xem người dùng đã đăng nhập và có vai trò là "Customer" chưa
//     * (trả về `true` nếu `isLogin()` là `true` và vai trò của người dùng là "Customer")
//     * @return boolean
      public static boolean isCustomer(){
        return Auth.isLogin() && user.getVaitro().equals("Customer");
    }
}