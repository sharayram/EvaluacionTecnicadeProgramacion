/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;

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
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                String mail = request.getParameter("mail");
                String password = request.getParameter("password");

                UserDAO dao = new UserDAO();
                User user = dao.validarLogin(mail, password);

                if (user != null) {
                // Guardamos al usuario en la sesión
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", user);
                        if (user.getIdRol() == 1) {
                            response.sendRedirect("ProductController");
                        } else if (user.getIdRol() == 2) {
                            response.sendRedirect("ProductController");
                        }
                }
        }
}

