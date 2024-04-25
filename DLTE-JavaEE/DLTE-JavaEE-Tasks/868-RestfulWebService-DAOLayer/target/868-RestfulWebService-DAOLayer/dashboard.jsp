<%@ page import="org.database.UserServices" %>
<%@ page import="org.database.StorageTarget" %>
<%@ page import="org.database.DatabaseTarget" %>
<%@ page import="org.database.User" %><%--
  Created by IntelliJ IDEA.
  User: xxshettn
  Date: 4/20/2024
  Time: 6:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
    <%
        response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
        UserServices userServices;
        StorageTarget storageTarget=new DatabaseTarget();
        userServices =new UserServices(storageTarget);
        if(session.getAttribute("username")!=null){
            String username = (String) session.getAttribute("username");
            User user=  userServices.callFindById(username);
    %>
    <nav class="navbar navbar-expand-lg navbar-light" style="background-color:green;">
        <div class="container-fluid justify-content-between">
            <a class="navbar-brand" href="#">MYBANK</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <p class="text-light display-6">Hi, <%=session.getAttribute("username")%></p>
                    </li>
                    <li class="nav-item active">
                        <a class="btn btn-outline-light rounded-5 me-2" href="#">Home</a>
                    </li>
                    <li class="nav-item active">
                        <a class="btn btn-outline-light rounded-5 me-2" href="create.jsp">Create Account</a>
                    </li>
                    <li class="nav-item active">
                        <a href="logout" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-door-open"></span> Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div id="pageContent" class="container mt-3">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">User Information</h5>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Name: <%= user.getUsername() %></li>
                    <li class="list-group-item">Address: <%= user.getAddress() %></li>
                    <li class="list-group-item">Contact: <%= user.getContact() %></li>
                    <li class="list-group-item">Email: <%= user.getEmail() %></li>
                    <li class="list-group-item">Balance: <%= user.getBalance() %></li>
                </ul>
            </div>
        </div>
    </div>
    <% } else {
        response.sendRedirect("index.jsp");
    } %>
</body>
</html>
</body>
</html>
