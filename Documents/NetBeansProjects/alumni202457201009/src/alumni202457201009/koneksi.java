/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alumni202457201009;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author Ainiha
 */
public class koneksi {
    // Mendeklarasikan variabel koneksi sebagai static agar bisa diakses dari mana saja di dalam class
     private static Connection mysqlconfig;
    
    // Method static untuk membuka koneksi ke database MYSQL
    public static Connection konek() {
        
        try {
            String url = "jdbc:mysql://localhost:3307/alumni_202457201009";
            String user = "root";
            String pass = "";
            
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
//            JOptionPane.showMessageDialog(null, "Tidak dapat terkoneksi");
            return mysqlconfig;
        } catch (SQLException sQLException) {
            
            JOptionPane.showMessageDialog(null, "Tidak dapat terkoneksi");
            return null;
            
        }
            
    }

    
}
