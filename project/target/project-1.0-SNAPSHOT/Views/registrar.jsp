<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .form-container {
            width: 400px;
            margin: 80px auto;
            padding: 25px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px #aaa;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-top: 15px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        button {
            margin-top: 20px;
            background: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            width: 100%;
            border-radius: 5px;
            cursor: pointer;
        }
        .message {
            text-align: center;
            margin-top: 15px;
            font-weight: bold;
        }
        .error {
            color: red;
        }
        .success {
            color: green;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Registrar Nuevo Usuario</h2>
        <form action="${pageContext.request.contextPath}/UserController" method="post">
            <label>Nombre:</label>
            <input type="text" name="name" required>
            
            <label>Correo:</label>
            <input type="email" name="mail" required>
            
            <label>Contraseña:</label>
            <input type="password" name="password" required>
            
            <label>Rol:</label>
            <select name="idRol" required>
                <option value="1">Administrador</option>
                <option value="2">Almacenista</option>
            </select>
            
            <label>Estatus:</label>
            <select name="status" required>
                <option value="1">Activo</option>
                <option value="0">Inactivo</option>
            </select>

            <button type="submit">Registrar</button>
        </form>

        <!-- Mensajes desde el controlador -->
        <%
            String error = (String) request.getAttribute("error");
            String success = (String) request.getAttribute("success");
            if (error != null) {
        %>
            <div class="message error"><%= error %></div>
        <% } else if (success != null) { %>
            <div class="message success"><%= success %></div>
        <% } %>
    </div>
</body>
</html>
