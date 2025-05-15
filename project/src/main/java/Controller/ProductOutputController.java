/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import DAO.ProductDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 *
 * @author saray
 */
@WebServlet(name = "ProductOutputController", urlPatterns = {"/ProductOutputController"})
public class ProductOutputController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduct"));

        ProductDAO dao = new ProductDAO();
        dao.updateStatus(id, 0); // cambiar estatus a inactivo

        // Recargar datos y mostrar en Dashboard
        List<Product> products = dao.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("Views/Dashboard.jsp").forward(request, response);
    }
}
