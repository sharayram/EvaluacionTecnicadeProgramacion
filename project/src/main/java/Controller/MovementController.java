/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;
import DAO.MovementDAO;
import Model.Movement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saray
 */
@WebServlet(name = "MovementController", urlPatterns = {"/MovementController"})
public class MovementController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        MovementDAO dao = new MovementDAO();
        List<Movement> movimientos = dao.getAll(tipo);

        request.setAttribute("movimientos", movimientos);
        request.getRequestDispatcher("Views/historial.jsp").forward(request, response);
    }
}
