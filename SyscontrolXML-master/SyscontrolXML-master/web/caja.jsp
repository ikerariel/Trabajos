<%-- 
    Document   : apertura
    Created on : 24-jun-2019, 14:34:00
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
            .table-responsive {height:350px;}
                     #scroll{
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
                            <a class="nav-link" style="display: none" data-toggle="tab" href="#Cajas">Cajas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#Apertura">Apertura</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#Cierre">Cierre</a>
                        </li>
                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container" style="display: none" id="Cajas">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefaultUsername" >Caja</label>
                                            <select class="custom-select">
                                                <option value="1">Caja #1</option>
                                                <option value="2">Caja #2</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefault02">Sucursal</label>
                                            <input type="text" class="form-control" id="validationDefault02" placeholder="Sucursal" disabled="true">
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefaultUsername">Usuario</label>
                                            <div class="input-group">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text" id="inputGroupPrepend2">@</span>
                                                </div>
                                                <input type="text" class="form-control" id="validationDefaultUsername" placeholder="Usuario" aria-describedby="inputGroupPrepend2" disabled="true">
                                            </div>
                                        </div>
                                    </div>
                                    <label for="validationDefaultUsername">Estado</label>
                                    <select class="custom-select">
                                        <option value="1">Cerrada</option>
                                        <option value="2">Abierta</option>
                                    </select>
                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <table class="table table-sm table-striped  table-hover">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Caja</th>
                                            <th scope="col">Ultima factura</th>
                                            <th scope="col">Sucursal</th>
                                            <th scope="col">Usuario</th>
                                            <th scope="col">Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>caja1</td>
                                            <td>002-002-0015196</td>
                                            <td>Fernando de la Mora</td>
                                            <td>Emanuel Maldonado</td>
                                            <td>Abierta</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>caja2</td>
                                            <td>002-002-0015196</td>
                                            <td>Fernando de la Mora</td>
                                            <td>Emanuel Maldonado</td>
                                            <td>Abierta</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">3</th>
                                            <td>caja3</td>
                                            <td>002-002-0015196</td>
                                            <td>Fernando de la Mora</td>
                                            <td>Emanuel Maldonado</td>
                                            <td>Abierta</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>caja1</td>
                                            <td>002-002-0015196</td>
                                            <td>Fernando de la Mora</td>
                                            <td>Emanuel Maldonado</td>
                                            <td>Abierta</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">2</th>
                                            <td>caja2</td>
                                            <td>002-002-0015196</td>
                                            <td>Fernando de la Mora</td>
                                            <td>Emanuel Maldonado</td>
                                            <td>Abierta</td>
                                        </tr>
                                    </tbody>
                                </table>
                                <!-- fin tablas -->
                                <br>
                                <!-- botones -->
                                <button type="button" class="btn btn-secondary">Nuevo</button>
                                <button type="button" class="btn btn-success">Guardar</button>
                            </div>
                        </div>

                        <!-- fin contenido cajas -->

                    </div>
                    <div class="tab-pane container active" id="Apertura">

                        <!-- contenido apertura -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-4 mb-3" style="display: none">
                                            <label for="validationDefaultUsername">Caja</label>
                                            <select class="custom-select">
                                                <option value="1">Caja #1</option>
                                                <option value="2">Caja #2</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefault02">Fecha</label>
                                            <input type="text" class="form-control" id="v_fechaactual" placeholder="fecha" disabled="true">
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefault02">Montos</label>
                                            <br>
                                            <!-- Button trigger modal -->
                                            <button type="button" id="btnagregarMontos"  class="btn btn-outline-info" data-toggle="modal">
                                                Agregar montos
                                            </button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="apertura" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h6 class="modal-title" id="exampleModalLabel">Montos por Operadoras</h6>
                                                        </div>

                                                        <div class="modal-body">
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Tigo</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm"  id="v_montotigo"  onblur="" placeholder="monto tigo">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Claro</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="v_montoclaro"  placeholder="monto claro">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Personal</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="v_montopersonal"   placeholder="monto personal">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Vox</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="v_montovox"  placeholder="monto vox">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div>
                                                                <h6 class="modal-title" id="exampleModalLabel">Giros/Billeteras</h6>
                                                            </div>
                                                            <br>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Tigo</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="giro_tigo" placeholder="monto tigo">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Claro</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="giro_claro"   placeholder="monto claro">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Personal</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="giro_personal"  placeholder="monto personal">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Vox</label>
                                                                <div class="col-sm-9">
                                                                    <input type="text" class="form-control form-control-sm" id="giro_vox"    placeholder="monto vox">
                                                                </div>
                                                                <label for="colFormLabelSm" class="col-sm-1 col-form-label col-form-label-sm">Gs.</label>
                                                            </div>
                                                            <div class="col-md-12 mb-3">
                                                                <label for="validationDefault01">Observacion :</label>
                                                                <input type="text" class="form-control" id="v_observación" placeholder="observación" required="" >
                                                            </div>
                                                            <div class="input-group mb-2">
                                                                <div class="input-group-prepend">
                                                                    <span class="input-group-text" id="inputGroup-sizing-default">Total Recargas: </span>
                                                                </div>
                                                                <input type="text" class="form-control" disabled="" id="total_recarga" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                                <div class="input-group-prepend">
                                                                    <span class="input-group-text" id="inputGroup-sizing-default">Total Giros/Billetera: </span>
                                                                </div>
                                                                <input type="text" class="form-control" disabled="" id="total_girobilletera" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                                <div class="input-group-prepend">
                                                                    <span class="input-group-text" id="inputGroup-sizing-default">Total Gral.: </span>
                                                                </div>
                                                                <input type="text" class="form-control" disabled="" id="total_general"  aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                            <button type="button" style="display: none" class="btn btn-primary" id="btnguardarapertura" title="Guardar Registros">Guardar</button>
                                                            <button type="button" style="display: none" class="btn btn-primary" id="btnupdateapertura" title="Modificar Registros">Guardar</button>
                                                        </div>

                                                    </div>

                                                </div>
                                            </div>
                                            <!-- fin contenido modal -->
                                        </div>
                                    </div>
                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitablaapertura">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Fecha Apertura</th>
                                                <th scope="col">Monto Recarga</th>
                                                <th scope="col">Monto Giro/Billetera</th>
                                                <th scope="col">Monto Total</th>
                                                <th scope="col">Estado</th>
                                                <th scope="col">Opción</th>
                                                <th scope="col">Usuario</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- fin tablas -->
                                <br>
                                <!-- botones -->
                                <button type="button" class="btn btn-secondary" style="display: none">Nuevo</button> 
                                <button type="button" class="btn btn-success" style="display: none">Guardar</button>
                            </div>
                        </div>

                        <!-- fin contenido Aperturas -->

                    </div>
                    <div class="tab-pane container fade" id="Cierre">

                        <!-- contenido cierre -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-4 mb-3" style="display: none">
                                            <label for="validationDefaultUsername">Caja</label>
                                            <select class="custom-select">
                                                <option value="1">Caja #1</option>
                                                <option value="2">Caja #2</option>
                                            </select>
                                        </div>
                                        <div class="col-md-4 mb-3">
                                            <label for="validationDefault02">Fecha</label>
                                            <input type="text" class="form-control" id="fecha_ciere" placeholder="fecha" disabled="true">
                                        </div>
                                        <div class="col-md-4 mb-3" style="display: none">
                                            <label for="validationDefault02">Montos</label>
                                            <br>
                                            <!-- Button trigger modal -->
                                            <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#cierre">
                                                Agregar montos
                                            </button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="cierre" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Montos por Operadoras</h5>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Tigo</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto tigo">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Claro</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto claro">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Personal</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto personal">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Vox</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto vox">
                                                                </div>
                                                            </div>
                                                            <div>
                                                                <h6 class="modal-title" id="exampleModalLabel">Giros/Billeteras</h6>
                                                            </div>
                                                            <br>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Tigo</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto tigo">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Claro</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto claro">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Personal</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto personal">
                                                                </div>
                                                            </div>
                                                            <div class="form-group row">
                                                                <label for="colFormLabelSm" class="col-sm-2 col-form-label col-form-label-sm">Vox</label>
                                                                <div class="col-sm-10">
                                                                    <input type="text" class="form-control form-control-sm" id="colFormLabelSm" placeholder="monto vox">
                                                                </div>
                                                            </div>
                                                            <div class="input-group mb-2">
                                                                <div class="input-group-prepend">
                                                                    <span class="input-group-text" id="inputGroup-sizing-default">Total</span>
                                                                </div>
                                                                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                            <button type="button" class="btn btn-primary">Guardar</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- fin contenido modal -->
                                        </div>
                                    </div>
                                </form>
                                <!-- fin contenido cards -->
                                <br>
                                <!-- tablas -->
                                <div class="table-responsive" id="scroll">
                                    <table class="table table-striped table-sm table-hover" id="mitablacierreapertura">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">Nro. Apertura</th>
                                                <th scope="col">Fecha Apertura</th>
                                                <th scope="col">Total Tigo</th>
                                                <th scope="col">Total Claro</th>
                                                <th scope="col">Total Personal</th>
                                                <th scope="col">Total Vox</th>
                                                <th scope="col" style="text-align: center">Informes</th>
                                                <th scope="col">Opción</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
                                <!-- fin tablas -->
                                <br>
                                <!-- botones -->
                                <button type="button" class="btn btn-secondary" style="display: none">Nuevo</button>
                                <button type="button" class="btn btn-success" style="display: none">Guardar</button>
                            </div>
                        </div>

                        <!-- fin contenido Cierre -->

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
        <script src="validaciones/validacionesCaja.js"></script>
        <script src="validaciones/validacionesgenericos.js"></script>
        <script src="Recursos/js/jquery-confirm.min.js"></script>
    </body>
</html>
