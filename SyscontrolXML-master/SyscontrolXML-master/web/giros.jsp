<%-- 
    Document   : giros
    Created on : 03-jul-2019, 14:13:32
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
                            <a class="nav-link active " data-toggle="tab" href="#giros">Giros / Billeteras</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active" id="giros">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefaultUsername">Tipo Operacion</label>
                                            <select class="custom-select" id="v_tipooperacion">

                                            </select>
                                        </div>
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefaultUsername">Operadora</label>
                                            <select class="custom-select" id="v_operadoragiro">

                                            </select>
                                        </div>
                                        <div class="col-md-3 mb-3">

                                            <label for="validationDefault01">Linea Origen :</label>
                                            <input type="text" class="form-control" id="v_lineaorigen" placeholder="Linea Origen" required>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefault02">Linea Destino :</label>
                                            <input type="text" class="form-control" id="v_lineadestino" placeholder="Linea Destino" required>
                                        </div>

                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefaultUsername">Monto :</label>
                                            <input type="text" class="form-control" id="v_montogiro" placeholder="Monto" required> 
                                        </div>
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefaultUsername">Porcentaje</label>
                                            <select class="custom-select" id="v_porsentaje" disabled="">
                                                <option value="1">5%</option>
                                                <option value="2">SIN PORCENTAJE.</option>
                                            </select>
                                        </div>
                                        <div class="col-md-3 mb-3">
                                            <label for="validationDefaultUsername">Total Envio :</label>
                                            <input type="text" class="form-control" id="v_totalenvio" disabled="" placeholder="Monto" required> 
                                        </div>
                                        <div class="col-md-3 mb-2">
                                            <label for="validationDefaultUsername">Pago</label>
                                            <select class="custom-select" id="v_pago" >

                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-2 mb-2">
                                            <label for="validationDefaultUsername">Cliente :</label>
                                            <input type="text" class="form-control" id="v_cicliente"  placeholder="Cedula" required> 
                                        </div>
                                        <div class="col-md-5">
                                            <label for="validationDefaultUsername" style="visibility: hidden">..</label>
                                            <input type="text" class="form-control" id="v_nombrecliente"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>

                                        <div class="col-md-5 md-5">
                                            <label for="validationDefaultUsername">Nro. Transacciòn :</label>
                                            <input type="text" class="form-control" id="v_nrotransacciongiro" placeholder="Nro. Transacciòn" required> 
                                        </div>
                                        <div class="col-md-1">
                                            <label for="validationDefaultUsername" style="display: none">..</label>
                                            <input type="text" class="form-control" style="display: none" id="v_idcliente"disabled="" placeholder="Nombre del cliente" required> 
                                        </div>

                                    </div>
                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitablagiros">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col" style="display: none">#</th>
                                                <th scope="col">Oper.</th>
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
                                <!-- fin tablas -->
                                <br>
                                <div class="form-row">
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-primary">Total Giro :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalGiro" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Total Billetera :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalBilletera" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-info">Total Retiro :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalRetiro" placeholder="" required maxlength="10">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label for="validationDefault02" class="badge badge-danger">Total Reverisòn :</label>
                                        <input type="text" disabled="" class="form-control" id="v_totalReversion" placeholder="" required maxlength="10">
                                    </div>
                                </div>


                                <!-- botones -->
                                <button type="button" class="btn btn-secondary">Nuevo</button>
                                <button type="button" class="btn btn-success" style="display: none" id="btnguardargiro">Guardar</button>
                                <button type="button" class="btn btn-danger" style="display: none" >Modificar</button>
                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                    
                    

                    <!-- fin contenido cajas -->

                </div>
            </div>
            <div style="padding-top: 100px" class="modal fade" id="v_cliente" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h6 class="modal-title" id="exampleModalLabel" style="text-align: center">Nuevo Cliente</h6>
                        </div>

                        <div class="modal-body">
                            <div class="form-row">
                                <div class="col-md-3 mb-4">
                                    <label for="validationDefault01">Cedula : </label>
                                    <input type="text" class="form-control" id="v_modalcedula" disabled="" splaceholder="" required="" >
                                </div>
                                <div class="col-md-6 mb-4">
                                    <label for="validationDefault01">Nombre Cliente : </label>
                                    <input type="text" class="form-control" id="v_modalnombre" placeholder="" required="" >
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button"  class="btn btn-outline-success" id="btnguardarcliente">Guardar</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
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
    <script src="validaciones/validacionesgenericos.js"></script>
    <script src="validaciones/validacionesGiro.js"></script>
    <script src="Recursos/js/jquery-confirm.min.js"></script>
    <!-- Menu Toggle Script -->
    <script src="Recursos/js/menu.js"></script>
</body>
</html>
