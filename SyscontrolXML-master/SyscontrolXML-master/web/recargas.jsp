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
                            <a class="nav-link active" data-toggle="tab" href="#recarga">Recarga</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="Recarga">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefault01">Monto</label>
                                            <input type="text" class="form-control" id="v_montorecarga" placeholder="Monto" required="" >
                                        </div>
                                        <div class="col-md-3">
                                            <label for="validationDefaultUsername" >Operador</label>
                                            <select class="custom-select" id="v_operadora">

                                            </select>
                                        </div>
                                        <div class="col-md-2 mb-3" style="display: none">
                                            <label for="validationDefault02">Prefijo</label>
                                            <select class="custom-select" id="v_prefijo" >
                                            </select>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefault02">Linea</label>
                                            <input type="text" class="form-control" id="v_nrolinea" placeholder="Linea" required maxlength="10">
                                        </div>
                                            <div class="col-md-3 mb-2">
                                            <label for="validationDefaultUsername">Pago</label>
                                            <select class="custom-select" id="v_pagorecarga" >
                                        
                                            </select>
                                        </div>

                                    </div>
                                    <div class="form-row">
                                            <div class="col-md-2 mb-2">
                                            <label for="validationDefaultUsername">Cliente :</label>
                                            <input type="text" class="form-control" id="v_ciclienterecarga"  placeholder="Cedula" required> 
                                        </div>
                                        <div class="col-md-6">
                                            <label for="validationDefaultUsername" style="visibility: hidden">..</label>
                                            <input type="text" class="form-control" id="v_nombreclienterecarga"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>
                                        <div class="col-md-4">
                                            <label for="validationDefaultUsername">Nro. Transacción </label>
                                            <input type="text" class="form-control" id="v_nrotransaccion" placeholder="Numero de transacción" aria-describedby="inputGroupPrepend2">
                                        </div>
                                           <div class="col-md-1">
                                            <label for="validationDefaultUsername" style="display: none">..</label>
                                            <input type="text" class="form-control" style="display: none" id="v_idclienterecarga"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>
                                     
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-8">
                                            <label for="validationDefaultUsername">Observaciòn </label>
                                            <input type="text" class="form-control" id="v_observacion" placeholder="Escriba una observaciòn" aria-describedby="inputGroupPrepend2">
                                        </div>
                                    </div>

                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitablarecarga">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none"></th>
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
                                <hr>
                                <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-primary">Saldo Disponible TIGO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldodisptigo" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Saldo Disponible CLARO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldodispclaro" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-info">Saldo Disponible PERSONAL :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldodisppersonal" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Saldo Disponible VOX :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldodispvox" placeholder="" required maxlength="10">
                                    </div>
                                </div>
                                <div class="form-row" style="display: none">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-primary">Saldo Disponible TIGO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldotigo" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Saldo Disponible CLARO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldoclaro" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-info">Saldo Disponible PERSONAL :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldopersonal" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Saldo Disponible VOX :</label>
                                        <input type="text" disabled="" class="form-control" id="v_saldovox" placeholder="" required maxlength="10">
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02">Total Recarga TIGO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totaltigo" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02">Total Recarga CLARO :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalclaro" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02">Total Recarga PERSONAL :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalpersonal" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02">Total Recarga VOX :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalvox" placeholder="" required maxlength="10">
                                    </div>

                                </div>
                                <hr>
                                <!-- fin tablas -->
                                <br>
                                <!-- botones -->
                                <button type="button" class="btn btn-secondary"  id="btnnuevacarga" onclick="">Nuevo</button>
                                <button type="button" id="btnguardarecarga" style="display: none" class="btn btn-success">Guardar</button>
                                <button type="button" class="btn btn-danger" style="display: none">Modificar</button>
                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                </div>

                <!-- Nav tabs end -->

                <!-- fin contenido -->
            </div>
                <div style="padding-top: 100px" class="modal fade" id="v_clienterecarga" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Nuevo Cliente</h6>
                            </div>

                            <div class="modal-body">
                                <div class="form-row">
                                    <div class="col-md-3 mb-4">
                                        <label for="validationDefault01">Cedula : </label>
                                        <input type="text" class="form-control" id="v_modalcedularecarga" disabled="" splaceholder="" required="" >
                                    </div>
                                    <div class="col-md-6 mb-4">
                                        <label for="validationDefault01">Nombre Cliente : </label>
                                        <input type="text" class="form-control" id="v_modalnombrerecarga" placeholder="" required="" >
                                    </div>

                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button"  class="btn btn-outline-success" id="btnguardarclienterecarga">Guardar</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                            </div>

                        </div>

                    </div>
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

    </body>
</html>
