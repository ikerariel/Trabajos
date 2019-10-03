<%-- 
    Document   : inicio
    Created on : 24-abr-2019, 16:13:29
    Author     : !mX - Made on Earth by Humans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%
        HttpSession sessionActiva = request.getSession();
        if (sessionActiva.getAttribute("sessionON") == null) {
            response.sendRedirect("/syscontrol/nologin.jsp");
        }
    %>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=out.jsp">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">

        <title>Syscontrol</title>
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
                <div class="container-fluid">
                    <!-- Cuadros estadisticas -->
                    <br>
                    <div class="row">

                        <div class="container">
                            <div class="card-deck mb-3 text-center">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                        <h4 class="my-0 font-weight-normal">Apertura</h4>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="card-title pricing-card-title">$0 <small class="text-muted">/ mo</small></h1>
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <li>10 users included</li>
                                            <li>2 GB of storage</li>
                                            <li>Email support</li>
                                            <li>Help center access</li>
                                        </ul>
                                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
                                    </div>
                                </div>
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                        <h4 class="my-0 font-weight-normal">Recargas</h4>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="card-title pricing-card-title">$15 <small class="text-muted">/ mo</small></h1>
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <li>20 users included</li>
                                            <li>10 GB of storage</li>
                                            <li>Priority email support</li>
                                            <li>Help center access</li>
                                        </ul>
                                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
                                    </div>
                                </div>
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                        <h4 class="my-0 font-weight-normal">Billeteras / Giros</h4>
                                    </div>
                                    <div class="card-body">
                                        <h1 class="card-title pricing-card-title">$29 <small class="text-muted">/ mo</small></h1>
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <li>30 users included</li>
                                            <li>15 GB of storage</li>
                                            <li>Phone and email support</li>
                                            <li>Help center access</li>
                                        </ul>
                                        <button type="button" class="btn btn-lg btn-block btn-outline-primary">Sign up for free</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!-- fin contenido -->
            </div>
            <!-- Fin Cuadros estadisticas -->
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- Bootstrap core JavaScript -->
    <script src="Recursos/vendor/jquery/jquery.min.js"></script>
    <script src="Recursos/vendor/jquery/bootstrap.bundle.min.js"></script>
    <!-- Menu Toggle Script -->
    <script src="Recursos/js/menu.js"></script>
</body>
</html>
