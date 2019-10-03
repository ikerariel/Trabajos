<%-- 
    Document   : Template
    Created on : 24-jun-2019, 14:39:41
    Author     : !mX - Made on Earth by Humans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">

        <title>Simple Sidebar - Start Bootstrap Template</title>
        <!-- Bootstrap CSS -->
        <link href="Recursos/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="Recursos/css/simple-sidebar.css" rel="stylesheet">

    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar -->
            <%@ include file="menu.jsp"%>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <%@ include file="navbar.jsp"%>
                <!-- contenido -->

                <!-- fin contenido -->
            </div>
        </div>


        <!-- Bootstrap core JavaScript -->
        <script src="Recursos/vendor/jquery/jquery.min.js"></script>
        <script src="Recursos/vendor/jquery/bootstrap.bundle.min.js"></script>
        <!-- Menu Toggle Script -->
        <script src="Recursos/js/menu.js"></script>
    </body>
</html>
