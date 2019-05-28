package com.futaba;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author David
 */

public class DataBridge {
    
    boolean connectedTrue = false;
    
//    ========== KONFIGURASI DATABASE ==========
    
    String url;
    String username;
    String password;
    String mainDb;
    final String configName = "config.xml"; //Penamaan config menggunkan final utk menghindari unsynchronized file name antara write dgn config
    
//    ========== INISIALISASI DATABASE ==========
    
    Connection cn;
    Statement smt;
    ResultSet rs;
    
    public void getConnect(){
        try {
            ConfigFileManager cflm = new ConfigFileManager();
            cflm.loadConfig();
            System.out.println(cflm.url+cflm.username);
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://"+cflm.url,cflm.username,cflm.password);
            smt = cn.createStatement();
            System.out.println("Koneksi sukses");
            connectedTrue = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getResult(String command){
        try {
            getConnect();
            if (connectedTrue) {
                rs = smt.executeQuery(command);
            } else{
                JOptionPane.showMessageDialog(null, "[DataBridge.class] getConnect Fail to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setUpdate(String command){
        try {
            getConnect();
            if (connectedTrue) {
                smt.executeUpdate(command);
            } else{
                JOptionPane.showMessageDialog(null, "[DataBridge.class] getConnect Fail to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}