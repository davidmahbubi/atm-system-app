package com.futaba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ConfigFileManager extends DataBridge{
    
    String url,username,pass,master;
    
    public void writeConfig(String url, String username, String password, String mainDb){
        try {
            Properties write = new Properties();
            write.setProperty("URL", url);
            write.setProperty("USERNAME", username);
            write.setProperty("PASSWORD", password);
            write.setProperty("MAIN_DB", mainDb);
            write.storeToXML(new FileOutputStream(new File(configName)), ""); 
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void showsg(){
        JOptionPane.showMessageDialog(null, "Kng");
    }
    
    public void loadConfig(){
        File fl = new File(configName);
        try {
            Properties load = new Properties();
            load.loadFromXML(new FileInputStream(fl));
            this.url = load.getProperty("URL");
            this.username = load.getProperty("USERNAME");
            this.pass = load.getProperty("PASSWORD");
            this.master = load.getProperty("MAIN_DB");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "File konfigurasi tidak ditemukan !\nMasuk database setting !","File config tidak ada",JOptionPane.ERROR_MESSAGE);
            new DBSettings().setVisible(true);
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
