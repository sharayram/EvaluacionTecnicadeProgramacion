/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import DAO.MovementDAO;
import Model.Movement;

import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saray
 */
@WebServlet(name = "ProductInputController", urlPatterns = {"/ProductInputController"})
public class ProductInputController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idProduct"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        ProductDAO dao = new ProductDAO();
        Product product = dao.getId(id);

        if (product != null) {
            int cantidadActual = product.getAmount();

            if (amount < 0) {
                request.setAttribute("mensajeEntrada", "❌ No se puede disminuir el inventario.");
            } else {
                int newAmount = cantidadActual + amount;
                dao.updateAmount(id, newAmount);
                 //REegistrar movimiento
                Movement mov = new Movement();
                mov.setIdProduct(id);
                mov.setUser("admin"); // en el futuro usa el usuario de sesión
                mov.setType("entrada");
                mov.setQuantity(amount);
                new MovementDAO().insert(mov);
                
                request.setAttribute("mensajeEntrada", "✅ Inventario actualizado correctamente.");
            }
        }

        // Actualiza la lista de productos y regresa al Dashboard
        request.setAttribute("products", dao.getAssets());
        request.getRequestDispatcher("Views/Dashboard.jsp").forward(request, response);
    }
}
