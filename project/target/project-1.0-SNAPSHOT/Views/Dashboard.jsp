<%@ page import="java.util.*, Model.Product, Model.User" %>

<%
    User usuario = (User) session.getAttribute("usuario");
    if (usuario == null || usuario.getIdRol() != 1) {
        response.sendRedirect("Views/login.jsp");
        return;
    }
%>


<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="DAO.ProductDAO" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Administrador</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Estilo del contenedor principal */
        .container {
            display: flex;
        }

        /* Estilo del menú lateral */
        .sidebar {
            width: 250px;
            background-color: #5cb85c;
            height: 100vh;
            padding-top: 20px;
        }

        .sidebar a {
            display: block;
            color: white;
            padding: 15px;
            text-decoration: none;
            font-size: 18px;
            border-bottom: 1px solid #34495e;
            transition: background-color 0.3s;
        }

        .sidebar a:hover {
            background-color: #34495e;
        }

        /* Estilo del contenido principal */
        .content {
            padding: 20px;
            flex: 1;
            background-color: white;
            height: 100vh;
            overflow-y: scroll;
        }

        /* Estilo de los títulos */
        h1 {
            color: #333;
        }

        .menu-title {
            font-size: 24px;
            margin-bottom: 20px;
        }

        .menu-option {
            margin-bottom: 15px;
            font-size: 18px;
        }

        /* Estilo del botón de cerrar sesión */
        .logout {
            background-color: #e74c3c;
            color: white;
            padding: 10px;
            text-align: center;
            cursor: pointer;
            margin-top: 20px;
            border: none;
            width: 100%;
            font-size: 18px;
        }
        .styled-table {
            width: 100%;
            border-collapse: collapse;
            font-size: 16px;
            min-width: 400px;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        .styled-table thead tr {
            background-color: #5cb85c;
            color: #ffffff;
            text-align: left;
        }
        .styled-table th, .styled-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #dddddd;
        }

        .styled-table tbody tr:nth-child(even) {
            background-color: #f3f3f3;
        }

        .styled-table tbody tr:hover {
            background-color: #f1f1f1;
            transition: background-color 0.2s ease-in-out;
        }
    </style>
</head>
<body>

<!-- Contenedor principal -->
<div class="container">

    <div class="content">
        <h1>Bienvenido al Dashboard, Administrador</h1>
        <div style="margin-bottom: 20px;">
            <form action="MovementController" method="get">
                <input type="submit" value="Ver historial de movimientos" 
                accept=""style="padding: 10px 20px; background-color: #0275d8; color: white; border: none; border-radius: 5px; cursor: pointer;">
            </form>
</div>
        <div style="display: flex; justify-content: space-between;">
        <div style="flex: 1; margin-right: 20px; overflow-x: auto;">
            <h3>Inventario general</h3>
            <table class="styled-table" border="1">
                    <tr>
                        <th>ID</th><th>Nombre</th><th>Cantidad</th><th>Estatus</th>
                    </tr>
                    <%
                        List<Product> products = (List<Product>) request.getAttribute("products");
                        if (products == null) {
                        ProductDAO dao = new ProductDAO();
                        products = dao.getAll(); // trae activos e inactivos
                        }
                        if (products != null) {
                            for (Product p : products) {
                    %>
                    <tr>
                        <td><%= p.getIdProduct() %></td>
                        <td><%= p.getName() %></td>
                        <td><%= p.getAmount() %></td>
                        <td style="color: <%= p.getStatus() == 1 ? "green" : "red" %>;">
                        <%= p.getStatus() == 1 ? "Activo" : "Inactivo" %>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" style="text-align: center;">No hay productos registrados.</td>
                    </tr>
                    <%
                    }
                    %>
            </table>
        </div>
    
        <div style="flex: 0.5; background-color: #f9f9f9; padding: 20px; border-radius: 10px;">
            <h2>Registrar nuevo producto</h2>
            <form action="AddProductController" method="post">
                <input type="text" name="name" placeholder="Nombre del producto" required style="width: 100%; margin-bottom: 10px; padding: 8px;">
                <input type="submit" value="Registrar" style="width: 100%; padding: 8px; background-color: #5cb85c; color: white; border: none;">
            </form>
            
            <h3>Entrada de productos (Aumentar inventario)</h3>
            <form action="ProductInputController" method="post">
                <label>Producto:</label>
                <select name="idProduct" required>
            <%
                    if (products != null) {
                        for (Product p : products) {
            %>
                    <option value="<%= p.getIdProduct() %>"><%= p.getName() %> (Cantidad actual: <%= p.getAmount() %>)</option>
            <%
                    }
                }
            %>
                </select>
    
            <label style="margin-left: 10px;">Cantidad a agregar:</label>
            <input type="number" name="amount" min="1" required>

            <input type="submit" value="Agregar Inventario" style="margin-left: 10px; background-color: #5cb85c; color: white; border: none; padding: 5px 10px;">
            </form>

            <%
                String mensajeEntrada = (String) request.getAttribute("mensajeEntrada");
                if (mensajeEntrada != null) {
            %>
                <p style="color: green;"><%= mensajeEntrada %></p>
            <%
            }
            %>
            
            <h3>Desactivar productos</h3>
            <form action="ProductOutputController" method="post">
                <label>Selecciona producto a dar de baja:</label>
                <select name="idProduct" required>
            <%
                    if (products != null) {
                        for (Product p : products) {
                            if (p.getStatus() == 1) { //muestra solo activos
            %>
                        <option value="<%= p.getIdProduct() %>"><%= p.getName() %> (Cantidad: <%= p.getAmount() %>)</option>
            <%
                    }
                }
            }
        %>
            </select>
            <input type="submit" value="Dar de baja" style="margin-left: 10px; background-color: #e74c3c; color: white; padding: 5px 10px; border: none;">
            </form>
            
            <h3>Salida de productos (Disminuir inventario)</h3>
            <form action="RemoveProductsController" method="post">
                <label>Producto:</label>
                <select name="idProduct" required>
            <%
                    if (products != null) {
                        for (Product p : products) {
                    if (p.getStatus() == 1) { // Solo activos
            %>
                    <option value="<%= p.getIdProduct() %>"><%= p.getName() %> (Cantidad actual: <%= p.getAmount() %>)</option>
            <%
                            }
                        }
                    }
            %>
                </select>

                <label style="margin-left: 10px;">Cantidad a sacar:</label>
                <input type="number" name="amount" min="1" required>
                <input type="submit" value="Sacar Inventario" style="margin-left: 10px; background-color: #d9534f; color: white; border: none; padding: 5px 10px;">
            </form>

            <%
                String mensajeSalida = (String) request.getAttribute("mensajeSalida");
                if (mensajeSalida != null) {
            %>
                <p style="color: red;"><%= mensajeSalida %></p>
            <%
                }
            %>
        </div>          
        </div> 
    </div>
            
</div>

</body>
</html>
