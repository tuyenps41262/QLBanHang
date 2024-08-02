package com.Manage.utils;

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package util;
//
//import java.util.Properties;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author thaohien
// */
//public class xEmail {
//    public void sendEmail(String emailtoAddress, String textMassage){
//    try {
//        Properties p = new Properties();
//        p.put("mail.smtp.auth", "true");
//        p.put("mail.smtp.starttls.enable", "true");
//        p.put("mail.smtp.host", "smtp.gmail.com");
//        p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//        p.put("mail.smtp.port", "587");
//    
//        String user= "hiencttps39942@gmail.com";
//        String pass="12334567";
//        
//        Session s =Session.getInstance(p,
//                new Authenticator(){
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication(){
//                        return new PasswordAuthentication(user,pass); }
//                });
//        Message msg = new MimeMessage(s);
//        
//            msg.setFrom(new InternetAddress(user));
//            
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailtoAddress));
//            msg.setSubject("Thông tin mật khẩu");
//            msg.setContent("Mật khẩu đăng nhập hệ thống là : "+  textMassage, "text/html:charset=utf-8");
//            
//            Transport.send(msg);
//            msgBox.Tbao(null, "Gửi thành công đến mail" + emailtoAddress);
//            
//            
//        } catch (Exception e) {
//           msgBox.Tbao(null, String.valueOf(e));
//           System.out.println(e);
//        }
//                
//    }
//}
