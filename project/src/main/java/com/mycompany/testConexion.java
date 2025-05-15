package com.mycompany;

import java.sql.Connection;
import java.sql.SQLException;

public class testConexion {
    public static void main(String[] args) {
        try {
            Connection conn = Conexion.getConnection();
            if (conn != null) {
                System.out.println("✅ Conexion exitosa a la base de datos.");
                conn.close();
            } else {
                System.out.println("❌ No se pudo establecer la conexion.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al conectar:");
            e.printStackTrace();
        }
    }
}
