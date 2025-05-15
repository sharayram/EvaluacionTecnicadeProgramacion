/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Movement;
import com.mycompany.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author saray
 */
public class MovementDAO {
    public void insert(Movement m) {
        String sql = "INSERT INTO movements (idProduct, user, type, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getIdProduct());
            stmt.setString(2, m.getUser());
            stmt.setString(3, m.getType());
            stmt.setInt(4, m.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Movement> getAll(String filterType) {
        List<Movement> list = new ArrayList<>();
        String sql = "INSERT INTO movements (idProduct, username, type, quantity) VALUES (?, ?, ?, ?)";

        if (filterType != null && !filterType.equals("todos")) {
            sql += " WHERE m.type = ?";
        }

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (filterType != null && !filterType.equals("todos")) {
                stmt.setString(1, filterType);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Movement m = new Movement();
                m.setIdMovement(rs.getInt("idMovement"));
                m.setIdProduct(rs.getInt("idProduct"));
                m.setProductName(rs.getString("name"));
                m.setUser(rs.getString("user"));
                m.setType(rs.getString("type"));
                m.setQuantity(rs.getInt("quantity"));
                m.setTimestamp(rs.getTimestamp("timestamp"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
