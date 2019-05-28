package com.futaba;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class UpdateChecker {
    URL updateLink;
    public UpdateChecker(){
        try {
            final String url = "https://google.com";
            System.out.println("( CONNECT ) Mengkoneksikan ke server update");
            updateLink = new URL(url);
            URLConnection connection = updateLink.openConnection();
            connection.connect();
            int opt = JOptionPane.showConfirmDialog(null, "Update tersedia, update sekarang ?","Update tersedia",JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Menuju ke browser...");
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else{
                System.out.println("Not supported");
            }
        } catch (Exception e) {
            System.out.println("( ERROR ) Tidak dapat terhubung ke server update");
            JOptionPane.showMessageDialog(null, "Update belum tersedia ! coba lain kali","Update belum ada",JOptionPane.ERROR_MESSAGE);
        }
    }
}
