/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.ProductDAO;
import Model.Product;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        Product nuevo = new Product();
        nuevo.setName(name);
        nuevo.setAmount(0);
        nuevo.setStatus(1);

        ProductDAO dao = new ProductDAO();
        boolean inserted = dao.agregarProd(nuevo);

        if (inserted) {
            request.setAttribute("mensaje", "Producto registrado con éxito.");
        } else {
            request.setAttribute("mensaje", "Error al registrar producto.");
        }

        response.sendRedirect("ProductController");
    }
}
