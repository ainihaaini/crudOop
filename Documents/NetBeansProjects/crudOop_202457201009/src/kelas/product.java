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
public class product extends koneksi{
    
    private String product, productName, productDescription;
    private int productId, productCategory, productPrice;
    private final Connection koneksi; 
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public product() {
        koneksi = super.configDB(); //super mewakili class koneksi, method DB kelas yang mewakili
        
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
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

    
    
    public void TambahProduct() {
        query = "INSERT INTO product (productName, categoryId, productDescription, productPrice) VALUES(?,?,?,?) ";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, productCategory);
            ps.setString(3, productDescription);
            ps.setInt(4, productPrice);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Tambah");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di tambah : " + e.getMessage());
        }
    }
    
    public void UbahProduct() {
        query = "UPDATE product SET productName = ?, productCategory = ?, productDescription = ?, "
                + "productPrice = ?, WHERE productId = ? ";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, productCategory);
            ps.setString(3, productDescription);
            ps.setInt(4, productPrice);
            ps.setInt(5, productId);

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Ubah");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Ubah");
        }
    }
    
    public void HapusProduct() {
        query = "DELETE FROM product WHERE productId = ?";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil di Hapus");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal di Hapus");
        }
    }
    
    public ResultSet TampilProduct() {
        query = "SELECT p.productId AS ID, p.productName AS Nama, c.categoryName AS Kategori, "
        + "p.productDescription AS Deskripsi, p.productPrice AS Harga " +
        "FROM product p JOIN category c ON p.productCategory = c.categoryId";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Tampilkan");
        }
        
        return  rs;
    }
    
    public ResultSet autoId() {
        try {
            query = "SELECT productId AS ID FROM product ORDER BY productId DESC LIMIT 1";
            
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
            
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "EROR : " + sQLException.getMessage());
        }
        return rs;
    }
    
    
    
}
