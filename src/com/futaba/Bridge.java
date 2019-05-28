package com.futaba;
import java.sql.*;
import javax.swing.JOptionPane;
//created by David
public class Bridge {
    ConfigInit cfInit = new ConfigInit();
    Connection cn;
    Statement smt;
    ResultSet rs;
    String url = cfInit.url;
    String uname = cfInit.username;
    String pass = cfInit.password;
    String tableName = cfInit.tables;
    final String driverPkg = "com.mysql.jdbc.Driver";
    public Bridge(){
        try{
            Class.forName(driverPkg);
            System.out.println("( GET ) Menyambungkan ke "+url);
            cn = DriverManager.getConnection(url,uname,pass);
            smt = cn.createStatement();
            System.out.println("( OK ) Koneksi sukses !");
        } catch (Exception e){
            System.out.println("( ERROR ! ) "+e+"\n( WRAP )Calling Database Setting");
            JOptionPane.showMessageDialog(null, "Cek setting database !","Database error",JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null, "Penyebab error : "+e,"Error menyambung ke database",JOptionPane.ERROR_MESSAGE);
            jumptSetting();
        }
    }
    void getResultOf(String command){
        try {
            rs = smt.executeQuery(command);
            System.out.println("( GET ) Getting result ....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void smtUpdate(String command){
        try {
            smt.executeUpdate(command);
            System.out.println("( UPDATE ) Updating database....");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void checkConnect(){
        try {
            rs = smt.executeQuery("select * from "+cfInit.tables);
            System.out.println("( OK ) Cek koneksi sukses ...");
        } catch (Exception e) {
            System.out.println("( ERROR ) "+e);
        }
    }
    void jumptSetting(){
        DatabaseSetting dbs = new DatabaseSetting();
        System.out.println("( ROUTE ) From Bridge.java 'Route' to DatabaseSetting.java ");
        dbs.fillField(url, uname, pass, tableName);
        dbs.setVisible(true);
    }
}