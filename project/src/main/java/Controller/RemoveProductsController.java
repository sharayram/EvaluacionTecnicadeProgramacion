/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Product;
import DAO.MovementDAO;
import Model.Movement;
        
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
@WebServlet(name = "RemoveProductsController", urlPatterns = {"/RemoveProductsController"})
public class RemoveProductsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("idProduct"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        ProductDAO dao = new ProductDAO();
        Product product = dao.getId(id);

        if (product != null && product.getStatus() == 1) { // Solo si está activo
            int currentAmount = product.getAmount();

            if (amount > currentAmount) {
                request.setAttribute("mensajeSalida", "❌ No se puede sacar más de lo que hay en inventario.");
            } else {
                int newAmount = currentAmount - amount;
                dao.updateAmount(id, newAmount);
                
                 // Registro del movimiento de salida
                Movement mov = new Movement();
                mov.setIdProduct(id);
                mov.setType("salida");
                mov.setQuantity(amount);
                mov.setUser("admin");

                MovementDAO movDao = new MovementDAO();
                movDao.insert(mov);
                
                request.setAttribute("mensajeSalida", "✅ Inventario actualizado correctamente.");
            }
        } else {
            request.setAttribute("mensajeSalida", "❌ Producto no encontrado o inactivo.");
        }

        request.setAttribute("products", dao.getAll()); // Muestra activos e inactivos
        request.getRequestDispatcher("Views/Dashboard.jsp").forward(request, response);
    }
}
