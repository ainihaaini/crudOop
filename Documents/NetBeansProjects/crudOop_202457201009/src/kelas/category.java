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
public class category extends koneksi{
    private String categoryName;
    private int categoryId;
    private final Connection conn;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public category() {
        conn = super.configDB(); //super mewakili class koneksi, method DB kelas yang mewakili 
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    
   public void TambahCategory(){
        query = "INSERT INTO category (categoryName) VALUES(?) ";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan: " + e.getMessage());
        }
    }
    public void UbahCategory() {
        query = "UPDATE category SET categoryName=? WHERE categoryId=? ";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryId);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah: " + e.getMessage());
        }
    }
    public void HapusCategory(){
        query = "DELETE FROM category WHERE categoryId = ? ";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus: " + e.getMessage());
        }
        
    }
    public ResultSet TampilCategory(){
        query = "SELECT * FROM category";
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e.getMessage());
        }
        return rs;
    }
    
    public ResultSet dataComboBox() {
        try {
            query = "SELECT categoryName FROM category";
            
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror : " + e.getMessage());
        }
        return rs;
    }
    
    public ResultSet konversi() {
        try {
            query = "SELECT categoryId FROM category WHERE categoryName = ?";
            
            ps = conn.prepareStatement(query);
            ps.setString(1, this.categoryName);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Eror konversi: " + e.getMessage());
        }
        return rs;  
    }
    
    public ResultSet autoId() {
        try {
            query = "SELECT categoryId AS ID FROM category ORDER BY categoryId DESC LIMIT 1";
            
            st = conn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan: " + e.getMessage());
        }
        return rs;
    }
    
    
}
