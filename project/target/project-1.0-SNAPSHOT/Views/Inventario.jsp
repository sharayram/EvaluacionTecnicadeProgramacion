<!-- inventario.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Model.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inventario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eef;
        }
        .container {
            width: 90%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }
        h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #5cb85c;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Inventario de Productos</h2>

    <!-- Tabla de productos -->
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Estatus</th>
        </tr>
        <%
            List<Product> productos = (List<Product>) request.getAttribute("products");
            if (productos != null) {
                for (Product p : productos) {
        %>
        <tr>
            <td><%= p.getIdProduct() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getAmount() %></td>
            <td><%= p.getStatus() == 1 ? "Activo" : "Inactivo" %></td>
        </tr>
        <%      }
            }
        %>
    </table>
</div>
</body>
</html>