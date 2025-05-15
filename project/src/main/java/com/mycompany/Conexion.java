package com.mycompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=inventario;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "usuario_app";
    private static final String PASSWORD = "Password123";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC de SQL Server.", e);
        }
    }
}
