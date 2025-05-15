/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.mycompany.Conexion;
import Model.User;
import java.sql.*;

/**
 *
 * @author saray
 */
public class UserDAO {
    public boolean registrarUsuario(User usuario) {
        String sql = "INSERT INTO users (name, mail, password, idRol, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getName());
            stmt.setString(2, usuario.getMail());
            stmt.setString(3, usuario.getPassword());
            stmt.setInt(4, usuario.getIdRol());
            stmt.setInt(5, usuario.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public User validarLogin(String mail, String password) {
    String sql = "SELECT * FROM users WHERE mail = ? AND password = ? AND status = 1";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, mail);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setMail(rs.getString("mail"));
            user.setPassword(rs.getString("password"));
            user.setIdRol(rs.getInt("idRol"));
            user.setStatus(rs.getInt("status"));
            return user;
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return null;
    }
}
