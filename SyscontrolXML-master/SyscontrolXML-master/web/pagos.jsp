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
            #scroll2{
                overflow: scroll;
                height:280px;
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
                            <a class="nav-link active" data-toggle="tab" href="#pagosbilletera">Pagos Giro/Biletera</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#pagosrecargas">Pagos Recargas</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="pagosbilletera">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- tablas -->
                                         <div class="form-row">
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefault02" style="">Ver Detalle :</label>
                                            <select class="custom-select" id="v_detallegiro" >

                                            </select> 

                                        </div>
                                        <div class="col-md-9 mb-2">

                                            <label for="validationDefault02" style="">Buscar :</label>
                                            <input class="form-control" type="text" maxlength="20" style="
                                                   font-weight: bold" id="vBuscarRegistro">
                                        </div>
                                    </div>
                                <div class="table-responsive" id="scroll2">
                           


                                    <table class="table table-striped table-sm table-hover" id="mitablapagos">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none">#</th>
                                                <th scope="col">Cliente</th>
                                                <th scope="col">Operación</th>
                                                <th scope="col">Importe</th>
                                                <th scope="col" id="td_fecha"></th>
                                                <th scope="col">Opción</th>
                                                <th scope="col" style="display: none">op</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- fin tablas -->
                                <br>
                                   <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-primary" id="textgiro"></label>
                                        <input type="text" disabled="" class="form-control" id="v_totalpagopend" placeholder="" required maxlength="10">
                                    </div>
                                </div>

                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                    <div class="tab-pane container" id="pagosrecargas">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">

                                <!-- tablas -->
                                          <div class="form-row">
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefault02" style="">Ver Detalle :</label>
                                            <select class="custom-select" id="v_detallerecarga" >

                                            </select> 

                                        </div>
                                        <div class="col-md-9 mb-2">

                                            <label for="validationDefault02" style="">Buscar :</label>
                                            <input class="form-control" type="text" maxlength="20" style="
                                                   font-weight: bold" id="vBuscarRegistrorecarga">
                                        </div>
                                    </div>
                                <div class="table-responsive" id="scroll2">
                                    <table class="table table-striped table-sm table-hover" id="mitablapagosrecargas">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none">#</th>
                                                <th scope="col">Cliente</th>
                                                <th scope="col">Nro.Detino</th>
                                                <th scope="col">Operación</th>
                                                <th scope="col">Importe</th>
                                                <th scope="col" id="td_fechar">Fecha</th>
                                                <th scope="col">Opción</th>
                                               <th scope="col" style="display: none">op</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- fin tablas -->
                                <br>
                                <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02"  class="badge badge-primary" id="txtpagorecarga"></label>
                                        <input type="text" disabled="" class="form-control" id="v_totalpagopendrecarga" placeholder="" required maxlength="10">
                                    </div>
                                </div>

                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                    <div style="padding-top: 100px" class="modal fade" id="v_pagos" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Registrar Pagos</h6>
                                </div>

                                <div class="modal-body">
                                    <div class="form-row">
                                        <div class="col-md-2">
                                            <label for="validationDefault01">Nro. Registro : </label>
                                            <input type="text" class="form-control" id="v_girocod" disabled="" splaceholder="" required="" >
                                        </div>
                                        <div class="col-md-3 mb-4" style="display: none">
                                            <label for="validationDefault01">Cedula : </label>
                                            <input type="text" class="form-control" id="v_modalci" disabled="" splaceholder="" required="" >
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <label for="validationDefault01">Nombre Cliente : </label>
                                            <input type="text" class="form-control" id="v_modalname" disabled="" placeholder="" required="" >
                                        </div>

                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-3 mb-4">
                                            <label for="validationDefault01">Importe Total : </label>
                                            <input type="text" class="form-control" id="v_modalmonto" disabled="" splaceholder="" required="" >
                                        </div>
                                        <div class="col-md-3 mb-4">
                                            <label for="validationDefault01">Importe a Pagar : </label>
                                            <input type="text" class="form-control" id="v_modalmontopagar"  splaceholder="" required="" >
                                        </div>


                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button"  class="btn btn-outline-success"title="Guardar pago Giro/Billetera" style="display: none" id="btnguardarpago">Guardar</button>
                                    <button type="button"  class="btn btn-outline-primary"title="Guardar pago Recarga" style="display: none"  id="btnguardarpagorecarga">Guardar</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                </div>

                            </div>

                        </div>
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
        <script src="validaciones/validacionesPago.js"></script>
        <script src="validaciones/validacionesgenericos.js"></script>
        <script src="Recursos/js/menu.js"></script>
        <script src="Recursos/js/jquery-confirm.min.js"></script>
        <!-- Vlidaciones Script -->


    </body>
</html>
