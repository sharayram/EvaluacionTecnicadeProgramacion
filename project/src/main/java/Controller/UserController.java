/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import DAO.UserDAO;

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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
   public class UserController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Obtener datos del formulario
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        int idRol = Integer.parseInt(request.getParameter("idRol"));
        int status = Integer.parseInt(request.getParameter("status"));

        // 2. Crear objeto usuario
        User user = new User();
        user.setName(name);
        user.setMail(mail);
        user.setPassword(password);
        user.setIdRol(idRol);
        user.setStatus(status);

        // 3. Registrar usuario en la base de datos
        UserDAO userDAO = new UserDAO();
        boolean exito = userDAO.registrarUsuario(user);

        // 4. Enviar respuesta a la vista
        if (exito) {
            request.setAttribute("success", "Views/Inventario.jsp.");
        } else {
            request.setAttribute("error", "No se pudo registrar el usuario.");
        }
        // 5. Redirigir a la vista de registro
        request.getRequestDispatcher("Views/registrar.jsp").forward(request, response);
    }
}

