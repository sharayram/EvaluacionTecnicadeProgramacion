/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Product;
import com.mycompany.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Listar todos los productos (activos o inactivos)
    public List<Product> obtenerTodos() {
    List<Product> products = new ArrayList<>();
    String sql = "SELECT * FROM products";

    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Product p = new Product();
            p.setIdProduct(rs.getInt("idProduct"));
            p.setName(rs.getString("name"));
            p.setAmount(rs.getInt("amount"));
            p.setStatus(rs.getInt("status"));
            products.add(p);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return products;
    
}
    // Agregar producto
    public boolean agregarProd(Product product) {
    String sql = "INSERT INTO products (name, amount, status) VALUES (?, ?, ?)";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, product.getName());
        stmt.setInt(2, product.getAmount());
        stmt.setInt(3, product.getStatus());

        int filas = stmt.executeUpdate();
        return filas > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    // Obtener producto por ID
    public Product getId(int idProduct) {
        Product p = null;
        String sql = "SELECT * FROM products WHERE idProduct = ?";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduct);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            p = new Product();
            p.setIdProduct(rs.getInt("idProduct"));
            p.setName(rs.getString("name"));
            p.setAmount(rs.getInt("amount"));
            p.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    // Actualizar cantidad
    public boolean updateAmount(int idProduct, int newAmount) {
        String sql = "UPDATE products SET amount = ? WHERE idProduct = ?";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newAmount);
            stmt.setInt(2, idProduct);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Obtener activos
    public List<Product> getAssets() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE status = 1";

        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
             Product p = new Product();
             p.setIdProduct(rs.getInt("idProduct"));
             p.setName(rs.getString("name"));
             p.setAmount(rs.getInt("amount"));
             p.setStatus(rs.getInt("status"));
              products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return products;
    }
    
    //Actualizar estatus de producto
        public boolean updateStatus(int idProduct, int newStatus) {
        String sql = "UPDATE products SET status = ? WHERE idProduct = ?";
        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newStatus);
            stmt.setInt(2, idProduct);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = Conexion.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            Product p = new Product();
            p.setIdProduct(rs.getInt("idProduct"));
            p.setName(rs.getString("name"));
            p.setAmount(rs.getInt("amount"));
            p.setStatus(rs.getInt("status"));
            products.add(p);
        }
        } catch (SQLException e) {
         e.printStackTrace();
        }
        return products;
    }
}

