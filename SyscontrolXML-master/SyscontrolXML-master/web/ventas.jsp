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
            #scrollTimbrado {height:180px;overflow: scroll}
            #scroolAperCierre {height:320px;overflow: scroll}


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
                            <a class="nav-link  active" data-toggle="tab" href="#registrarTimbrados">Registrar Timbrados</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#vAperturaCaja">Apertura de Caja</a>
                        </li>

                    </ul>
                </div>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane container active"  id="registrarTimbrados">
                        <!-- contenido cajas -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <form>
                                    <div class="form-row">
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefaultUsername" >Tipo Documento</label>
                                            <select class="custom-select" id="vTipoDocumento">
                                                <option value="1">Factura</option>
                                                <option value="2">Nota Crédito</option>
                                                <option value="2">Nota Débito</option>
                                                <option value="2">Nota Remisión</option>
                                            </select>
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Nro. Timbrado</label>
                                            <input type="text" class="form-control"  id="vNrotimbrado" placeholder="timbrado" >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Fecha Alta</label>
                                            <input type="text" class="form-control" id="vFechaalta" disabled="" >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Fecha Inicio</label>
                                            <input type="date" class="form-control" id="vfechainicio" placeholder="Sucursal" >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Fecha Fin</label>
                                            <input type="date" class="form-control" id="vfechafin" placeholder="Sucursal" >
                                        </div>


                                    </div>
                                    <div class="form-row">
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Nro. Establecimiento</label>
                                            <input type="text" class="form-control"  id="vEstablecimiento" placeholder="timbrado" >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Nro.Caja</label>
                                            <input type="text" class="form-control" id="vNroCaja"  >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Nro. Desde</label>
                                            <input type="text" class="form-control" id="vNroDesde"  >
                                        </div>
                                        <div class="col-md-2 mb-3">
                                            <label for="validationDefault02">Nro. Hasta</label>
                                            <input type="text" class="form-control" id="vNroHasta"  >
                                        </div>
                                        <div class="col-md-2">
                                            <label for="validationDefault02" style="visibility: hidden">-</label>
                                            <button type="button" style="" class="btn btn-outline-info form-control " id="btngenerarSQ" title="Guardar Registros">Generar</button>
                                        </div>


                                    </div>

                                </form>
                                <!-- fin contenido cards -->

                                <!-- tablas -->
                                <div id="scrollTimbrado">
                                    <table class="table table-sm table-striped  table-hover " id="mitablaTimbrados">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Tipo Doc.</th>
                                                <th scope="col">Nro. Timbrado</th>
                                                <th scope="col">Fecha Alta</th>
                                                <th scope="col">F. Vigencia Inicio</th>
                                                <th scope="col">F. Vigencia Fin</th>
                                                <th scope="col">Nro. Dede</th>
                                                <th scope="col">Nro. Hasta</th>
                                                <th scope="col">Estado</th>
                                                <th scope="col">Opción</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>

                                </div>
                                <div  class="modal fade"  id="v_ventanaSQ" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document" >
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h6 class="modal-title " id="vTXT" style="text-align: center"></h6>
                                            </div>

                                            <div class="modal-body">
                                                <div class="form-row">
                                                    <div class="col-md-3 mb-3">
                                                        <label for="validationDefault02">Nro. Timbrado</label>
                                                        <input disabled="" type="text" class="form-control form-control-sm"  id="vMNrotimbrado"  >
                                                    </div>
                                                    <div class="col-md-2 mb-3">
                                                        <label for="validationDefault02">Nro. Estable.</label>
                                                        <input disabled="" type="text" class="form-control form-control-sm "  id="vMEstablecimiento" >
                                                    </div>
                                                    <div class="col-md-2 mb-3">
                                                        <label for="validationDefault02">Nro.Caja</label>
                                                        <input disabled="" type="text" class="form-control form-control-sm" id="vMNroCaja"  >
                                                    </div>
                                                    <div class="col-md-2 mb-3">
                                                        <label for="validationDefault02">Nro. Desde</label>
                                                        <input disabled="" type="text" class="form-control form-control-sm" id="vMNroDesde"  >
                                                    </div>
                                                    <div class="col-md-2 mb-3">
                                                        <label for="validationDefault02">Nro. Hasta</label>
                                                        <input disabled="" type="text"  class="form-control form-control-sm" id="vMNroHasta"  >
                                                    </div>



                                                </div>
                                                <div class="form-row">

                                                    <div class="col-md-3 mb-3">
                                                        <label for="validationDefault02">Fecha Alta</label>
                                                        <input disabled="" type="text" class="form-control form-control-sm" id="vMFechaalta" disabled="" >
                                                    </div>
                                                    <div class="col-md-3 mb-3">
                                                        <label for="validationDefault02">Fecha Inicio</label>
                                                        <input disabled="" type="date" class="form-control form-control-sm" id="vMfechainicio" placeholder="Sucursal" >
                                                    </div>
                                                    <div class="col-md-3 mb-3">
                                                        <label for="validationDefault02">Fecha Fin</label>
                                                        <input disabled="" type="date" class="form-control form-control-sm" id="vMfechafin" placeholder="Sucursal" >
                                                    </div>
                                                    <div class="col-md-3 mb-3">
                                                        <label for="validationDefault02" style="color: #007bff">Seleccionar Verificación</label>
                                                        <input  type="checkbox" title="Seleccionar el Check en caso que todos los datos estén correctos, caso contrario, click en el botón CERRAR" class="form-control form-control-sm alert-primary" id="vMverificacion" placeholder="Sucursal" >
                                                    </div>



                                                </div>
                                                <div class="form-row">

                                                    <div id="scroll"  style="height: 250px; overflow: scroll" class=" container panel panel-footer" >
                                                        <table class="table table-striped table-bordered table-hover table-sm"  id="miTablaGenerada" >
                                                            <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                                            <thead class="thead-dark">
                                                                <tr id="tableCabecera" >
                                                                    <th style="display: none">Nro.Establec. *</th>
                                                                    <th style="display: none">Nro. Caja *</th>
                                                                    <th>Secue. *</th>
                                                                    <th style="display: none">Nro. *</th>
                                                                    <th>Nro. * </th>
                                                                    <th>Estado * </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody id="table_deta" style="font-weight: bold; font-size: 10pt" >
                                                            </tbody>
                                                        </table>

                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" style="display: none"  class="btn btn-success" title="Guardar Registros" id="btnGuardarTimbrado">Guardar</button>
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                                    </div>
                                                </div>
                                            </div>


                                        </div>

                                    </div>
                                </div>
                                <div style="padding-top: 180px" class="modal fade"  id="v_Cargando" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog modal-sm" role="document" >
                                        <div class="modal-content">

                                            <div class="modal-body">

                                                <button class=" container btn btn-lg btn-success"><span class="spinner-border spinner-grow-sm">
                                                    </span> Generando <span class="spinner-border spinner-grow-sm">
                                                    </span></button>


                                            </div>
                                        </div>


                                    </div>

                                </div>
                            </div>

                            <!-- fin tablas -->
                            <br>


                        </div>

                    </div>
                    <div class="tab-pane container" style="" id="vAperturaCaja">

                        <!-- contenido apertura -->
                        <br>
                        <div class="card">
                            <div class="card-body">
                                <!-- contenido cards -->
                                <div class="">
                                    <button type="button" class="btn btn-outline-primary" style="">Nuevo</button> 
                                    <button type="button" class="btn btn-success" id="btnGuardarAperVenta" style="">Guardar</button>  
                                    <button type="button" class="btn btn-danger" id="btnCerrarCaja" style="">Cerrar Caja</button>  
                                </div>
                                <hr>
                                <form>
                                    <div class="form-row">
                                        <div class="form-row col-md-2">

                                            <label for="validationDefault02">Monto Apertura :</label>
                                            <input type="text" class="form-control form-control-sm "  id="apeMontoapertura" >

                                            <label for="validationDefault02">Caja :</label>
                                            <select disabled=""  type="text" class="form-control form-control-sm" id="aperCaja"  ></select>

                                            <label for="validationDefault02">Cajero :</label>
                                            <select id="listacajeros"  class="form-control form-control-sm"  >
                                            </select>

                                            <label for="validationDefault02">Documento :</label>
                                            <select id="listaDoc"  class="form-control form-control-sm" >
                                            </select>

                                            <label for="validationDefault02">Factura :</label>
                                            <select type="text"  class="form-control form-control-sm" id="aperFactTimrbados"  ></select>
                                            <input disabled=""  type="text"  class="form-control form-control-sm" id="aperIDfacTimbrados"  >
                                            <input disabled="" style="display: none" type="text"  class="form-control form-control-sm" id="CodigoApertura"  >


                                        </div>
                                        <div class="form-row col-md-10">
                                            <div id="scroolAperCierre" class="container">
                                                <table onclick="seleccionCaja()" style="cursor: pointer" class="table table-sm table-striped  table-hover " id="mitablaaperturaCierreCajaVentas" >
                                                    <thead class="thead-dark">
                                                        <tr>
                                                            <th scope="col">#</th>
                                                            <th scope="col" style="height: 50px">Fecha Apertura</th>
                                                            <th scope="col">Monto Apertura</th>
                                                            <th scope="col">Cajero</th>
                                                            <th scope="col">Caja</th>
                                                            <th scope="col" >Fecha Cierre</th>
                                                            <th scope="col">Estado</th>
                                                            <th scope="col" style="display: none"></th>
                                                            <th scope="col" style="display: none"></th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                    </tbody>
                                                </table>

                                            </div>
                                        </div>


                                    </div><br>
                                </form>
                                <br>
                                <br>
                            </div>

                        </div>

                        <!-- fin contenido Aperturas -->

                    </div>



                </div>
            </div>

            <!-- Nav tabs end -->

            <!-- fin contenido -->
        </div>
        <!-- Bootstrap core JavaScript -->
        <script src="Recursos/vendor/jquery/jquery.min.js"></script>
        <script src="Recursos/vendor/jquery/bootstrap.bundle.min.js"></script>
        <!-- Menu Toggle Script -->
        <script src="Recursos/js/menu.js"></script>
        <script src="validaciones/validacionesVentas.js"></script>
        <script src="validaciones/validacionesgenericos.js"></script>
        <script src="Recursos/js/jquery-confirm.min.js"></script>
    </body>
</html>
