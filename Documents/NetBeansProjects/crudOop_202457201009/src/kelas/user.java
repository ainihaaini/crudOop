/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Ainiha
 */
public class user extends koneksi {
    
    private String user, userName, userEmail, userPassword, userFullName;
    private int userStatus;
    private final Connection koneksi; 
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public user() {
        koneksi = super.configDB(); //super mewakili class koneksi, method DB kelas yang mewakili
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    
    public void TambahUSer() {
        query = "INSERT INTO user VALUES(?,?,MD5(?),?,?)";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ps.setString(3, userEmail);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Tambah");
        }
    }
    
    public void UbahUser() {
        try {
            if ("".equals(userPassword)) {
                // Kalau password tidak diubah
                query = "UPDATE user SET userEmail = ?, userFullName = ?, userStatus = ? WHERE userName = ?";
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userFullName);
                ps.setInt(3, userStatus);
                ps.setString(4, userName);
            } else {
                // Kalau password diubah
                query = "UPDATE user SET userEmail = ?, userPassword = MD5(?), userFullName = ?, userStatus = ? WHERE userName = ?";
                ps = koneksi.prepareStatement(query);
                ps.setString(1, userEmail);
                ps.setString(2, userPassword);
                ps.setString(3, userFullName);
                ps.setInt(4, userStatus);
                ps.setString(5, userName);
            }

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
        }
    }
    
    public void HapusUser() {
        query = "DELETE FROM user WHERE userName = ? ";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus");
        }
    }
    
    public ResultSet TampilUser() {
        query = "SELECT * FROM user";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Di Tampilkan");
        }
        
        return  rs;
    }
    
    public void Login() {
        query = "SELECT * FROM user WHERE userName = ? AND userPassword = MD5(?)";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                sesion.setUsername(rs.getString("userName"));
                sesion.setEmail(rs.getString("userEmail"));
                sesion.setFullName(rs.getString("userFullName"));
                sesion.setStatus("Active");
            } else {
                sesion.setStatus("InActive");
            }
            JOptionPane.showMessageDialog(null, "Login Berhasil!!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Login Gagal!");
        }
    }
    
    public  void logOut() {
        sesion.setUsername("");
        sesion.setEmail("");
        sesion.setFullName("");
        sesion.setStatus("");
    }
    
}
