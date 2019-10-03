<%-- 
    Document   : recargas
    Created on : 01-jul-2019, 11:46:02
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

        <link href="Recursos/css/jquery-confirm.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="Recursos/css/simple-sidebar.css" rel="stylesheet">

        <!-- estilo de los paneles -->
        <style>
            .paneles{
                margin-top: 10px;
                margin-left: 15px;
            }
            #scroll{
                overflow: scroll;
                height:200px;
            }
        </style>

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


                <!-- Nav tabs -->
                <div class="paneles">
                    <ul class="nav nav-tabs">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#consulta">Consulta</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="Consulta">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefault02">Opción</label>
                                            <select class="form-control" id="v_opciones">

                                            </select>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefault02" id="v_text">Número Linea</label>
                                            <input type="text" class="form-control" id="v_consultadato"  placeholder="" required maxlength="10"
                                                   >
                                        </div>
                                        <div class="col-md-2 mb-2">
                                            <label for="validationDefault02">Consultar:</label>
                                            <br>
                                            <!-- Button trigger modal -->
                                            <button type="button" id="btnconsultar" onclick="" class="btn btn-block btn-outline-info" >
                                                Consultar
                                            </button>
                                        </div>

                                    </div>


                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <label for="validationDefault02">Detalle Recargas</label>
                                    <table class="table table-striped table-sm table-hover" id="mitablaconsultarecarga">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none">#</th>
                                                <th scope="col">Operador</th>
                                                <th scope="col">Linea</th>
                                                <th scope="col">Monto</th>
                                                <th scope="col">Nro.Transacciòn</th>
                                                <th scope="col">Fecha</th>
                                                <th scope="col">Opción</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <div class="table-responsive" id="scroll">
                                    <label for="validationDefault02">Detalle Giros/Billeteras</label>
                                    <table class="table table-striped table-sm table-hover" id="mitablaconsultagiro">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none">#</th>
                                                <th scope="col" >Oper.</th>
                                                <th scope="col">Nro. Origen</th>
                                                <th scope="col">Nro. Destino</th>
                                                <th scope="col">Oper.</th>
                                                <th scope="col">Monto Env.</th>
                                                <th scope="col">(%)</th>
                                                <th scope="col">Monto Cobra.</th>
                                                <th scope="col">Fecha Envío</th>
                                                <th scope="col">Opción</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <hr>



                                <hr>
                                <!-- fin tablas -->
                                <br>

                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                </div>

                <!-- Nav tabs end -->

                <!-- fin contenido -->
            </div>

        </div>


        <!-- Bootstrap core JavaScript -->
        <script src="Recursos/vendor/jquery/jquery.min.js"></script>
        <script src="Recursos/vendor/jquery/bootstrap.bundle.min.js"></script>
        <!-- Menu Toggle Script -->
        <script src="Recursos/js/menu.js"></script>
        <script src="Recursos/js/jquery-confirm.min.js"></script>
        <!-- Vlidaciones Script -->
        <script src="validaciones/validacionesgenericos.js"></script>
        <script src="validaciones/validacionesRecargas.js"></script>
        <script src="validaciones/validacionesConsulta.js"></script>

    </body>
</html>
