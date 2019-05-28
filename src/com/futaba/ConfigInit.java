package com.futaba;
import java.io.*;
import javax.swing.JOptionPane;
//Created by David
public class ConfigInit {
    String url,username,password,tables,cfgLoader;
    String content;
    Boolean devMode = true;
    int warnMe = 0;
    //url default config :
    File urlFile = new File("url.acf");
    //username default config :
    File usernameFile = new File("username.acf");
    //password default config:
    File passwordFile = new File("password.acf");
    //table default config :
    File table = new File("table.acf");
    //Default config cfg file
    File DefaultConfig = new File("config.cfg");
    public ConfigInit(){
        System.out.println("=== LOAD CONFIG FILE ("+DefaultConfig.getAbsolutePath()+")");
        getFile(DefaultConfig);
        if (content != null) {
            if (content.equals("devmode=1")) {
                System.out.println("(MODE) Developer mode ON !");
                devMode = true;
            } else if (content.equals("devmode=0")){
                System.out.println("(MODE) Developer mode OFF !");
                devMode = false;
            } else{
                JOptionPane.showMessageDialog(null, "ERROR ! CORRUPTED CONFIG FILE !","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        }
        System.out.println("============== Menginisialisasi konfigurasi (ConfigInit.java) ============== ");
        getFile(urlFile);
        url = content;
        System.out.println("( OK ) URL Didapatkan : "+url+" ("+urlFile.getAbsolutePath()+")");
        getFile(usernameFile);
        username = content;
        System.out.println("( OK ) Username didapatkan : "+username+" ("+usernameFile.getAbsolutePath()+")");
        // ========== Password dengan metode manual =============
        try {
            File fle = new File("password.acf");
            FileInputStream fistr = new FileInputStream(fle);
            InputStreamReader istr = new InputStreamReader(fistr);
            BufferedReader bfr = new BufferedReader(istr);
            String pwLine = bfr.readLine();
            if (pwLine != null) {
                password = pwLine;
                System.out.println("( OK ) Password : "+password+" ("+passwordFile.getAbsolutePath()+")");
            } else{
                password = "";
                System.out.println("( WARNING ) Password kosong !!!! ("+passwordFile.getAbsolutePath()+")");
                warnMe++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            password = "";
        }
        // ========== Password dengan metode manual =============
        getFile(table);
        tables = content;
        System.out.println("( OK ) Table nasabah didapatkan : "+tables+" ("+table.getAbsolutePath()+")");
    }
    
    void getFile(File filePath){
        try {
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bfr = new BufferedReader(isr);
            String line = bfr.readLine();
            if (line != null) {
                content = line;
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}