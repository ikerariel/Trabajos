<%-- 
    Document   : reportes
    Created on : 20/07/2019, 10:44:16 AM
    Author     : Carlos
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
                                        <h4 class="my-0 font-weight-normal">Recargas</h4>
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <img src="Recursos/img/pdf.jpeg" width="100" height="100">
                                        </ul><br><br>
                                        <button  type="button" id="btninf_recarga" class="btn btn-lg btn-block btn-outline-danger"  data-toggle="modal">Ver Reporte</button>
                                    </div>
                                </div>
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                        <h4 class="my-0 font-weight-normal">Billeteras / Giros</h4>
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <img src="Recursos/img/pdf.jpeg" width="100" height="100">
                                        </ul><br><br>
                                        <button type="button"id="btninf_giro"class="btn btn-lg btn-block btn-outline-success">Ver Reporte</button>
                                    </div>
                                </div>
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                        <h4 class="my-0 font-weight-normal">Pagos</h4>
                                    </div>
                                    <div class="card-body">
                                        <ul class="list-unstyled mt-3 mb-4">
                                            <img src="Recursos/img/pdf.jpeg" width="100" height="100">
                                        </ul><br><br>
                                        <button type="button" id="btninf_pago" class="btn btn-lg btn-block btn-outline-primary">Ver Reporte</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div style="padding-top: 100px" class="modal fade" id="v_informes" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h6 class="modal-title" id="txt_inf"></h6>
                                </div>

                                <div class="modal-body">
                                    <div class="form-row " id="divCombo" style="justify-content: center; display: none">

                                        <div class="col-md-4 ">
                                            <label  for="validationDefault01">Operación: </label>
                                            <select type="date"  class="form-control" id="vOpcioninforme" placeholder="" required="" ></select>
                                        </div>

                                        <div class="col-md-3">
                                            <label  for="validationDefault01">Estado: </label>
                                            <select   class="form-control alert-primary" id="vOpcionpago" placeholder="" required="" ></select>
                                        </div>




                                    </div><br>
                                    <div class="form-row" style="justify-content: center">
                                        <label class="col-md-2" for="validationDefault01">Fecha Desde : </label>
                                        <div class="col-md-4">
                                            <input type="date" class="form-control" id="vFechadesde" placeholder="" required="" >
                                        </div>


                                    </div><br>
                                    <div class="form-row" style="justify-content: center">
                                        <label class="col-md-2"  for="validationDefault01">Fecha Hasta : </label>
                                        <div class="col-md-4">
                                            <input type="date" class="form-control" id="vFechahasta" placeholder="" required="" >
                                        </div>

                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" title="Generar Informe Recarga" style="display: none" id="btninfrecarga" class="btn btn-outline-danger" >
                                        Generar
                                    </button>
                                    <button type="button" title="Generar Informe Giro/Billetera" style="display: none" id="btninfgiro"  class="btn btn-outline-danger" >
                                        Generar
                                    </button>
                                    <button type="button"title="Generar Informe Pagos"  style="display: none" id="btninfpago"  class="btn btn-outline-danger" >
                                        Generar
                                    </button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>

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
    <!-- Rerporte -->
    <script src="validaciones/validacionesgenericos.js"></script>

</body>
</html>
