/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Manage.utils;

import java.security.SecureRandom;

/**
 *
 * @author thaohien
 */
public class RandomSNN {
    //AZ là độ dài phạm vi kí tự 
    //rd phát sinh mã ngẫu nhiên 
    static final String az ="0123456789abcdefghjqklmnozytuABCDEFGHIJKLMNOPQRSWZ";
    static SecureRandom rd= new SecureRandom();
    
    //str= tạo một buider trống với dung lượng capital đã cho 
    public String soNgauNhien (int n){
        StringBuilder str = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            str.append(az.charAt(rd.nextInt(az.length())));
        }
        return str.toString();
    }
}
