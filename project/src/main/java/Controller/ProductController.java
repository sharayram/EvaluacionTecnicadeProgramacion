/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.ProductDAO;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.Conexion;

@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {

    // Método para obtener todos los productos
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

    // Método GET que carga la vista con los datos
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            List<Product> products = obtenerTodos();
            request.setAttribute("products", products);

        HttpSession session = request.getSession();
        Model.User usuario = (Model.User) session.getAttribute("usuario");

        if (usuario != null && usuario.getIdRol() == 1) {
            request.getRequestDispatcher("Views/Dashboard.jsp").forward(request, response);
        } else if (usuario != null && usuario.getIdRol() == 2) {
            request.getRequestDispatcher("Views/Inventario.jsp").forward(request, response);
        } else {
            response.sendRedirect("Views/login.jsp");
        }
    }
}
