package com.futaba;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author David
 */
public class getConfigFile {
    File fl;
    FileInputStream fis;
    InputStreamReader isr;
    FileNameExtensionFilter fex = new FileNameExtensionFilter("ATM File Config (.acf)", "acf");
    BufferedReader bfr;
    String line;
    public getConfigFile(){
        try {
            JFileChooser jfs = new JFileChooser();
//            jfs.addChoosableFileFilter(new FileNameExtensionFilter("*ATM Config File (.acf)", "acf"));
            jfs.setMultiSelectionEnabled(false);
            jfs.setFileFilter(fex);
            jfs.showOpenDialog(null);
            fl = jfs.getSelectedFile();
            fis = new FileInputStream(fl);
            isr = new InputStreamReader(fis);
            bfr = new BufferedReader(isr);
            line = bfr.readLine();
        } catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Gagal load file ! Msg : "+e);
        }
    }
    
    void closeReader(String reason){
        try {
            bfr.close();
            System.out.println("Buffered closed ! for "+reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}