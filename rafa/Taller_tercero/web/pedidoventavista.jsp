<%-- 
    Document   : pedidoventavista
    Created on : 18/09/2019, 11:07:56 AM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <%
        HttpSession sessionActiva = request.getSession();
        if (sessionActiva.getAttribute("sessionON") == null) {
            response.sendRedirect("/Taller_tercero/acceso_v.jsp");
        }
        %>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="Recursos/css/iconos.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap-theme.min.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.css"/>
        <link rel="stylesheet" href="Recursos/css/bootstrap-select.min.css"/>
        <script src="Recursos/js/main.js"></script>
        <script src="Recursos/js/jquery.js"></script>
        <script src="Recursos/js/bootstrap.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/jquery.dataTables.min.js"></script>
        <script src="Recursos/js/bootstrapValidator.js"></script>
        <script src="Recursos/js/bootstrap-select.js"></script>
        <script src="Recursos/js/bootstrap-select.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="Validadores/pedidoventavalidador.js"></script>
         <title>Pedidos de Ventas</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div class="col-md-9" id="botonesPedidoVe">
                    <a id="btnNuevo" href="#ventanaPedidoVenta" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Pedido" data-toggle="modal"
                       onclick="codigopedidoVe(); fechaactualPediV(); MostrarEstadoPediV()">Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetallePediVe()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Pedido" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion de Pedido" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
                </div>
                <br>
                <br>
                <br>
                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla: Pedidos de Ventas</div>
                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                            <input id="nropediVenta" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estpediVenta" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                    </div>
                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPedidoVe" type="text" style="text-transform: uppercase; font-weight: bold" class="form-control " maxlength="20" onkeyup="buscadorTablaPedidoVe()"placeholder="Buscar Pediod...">
                        </div>
                    </div>

                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPediVe" onclick="seleccionarPediVe()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th class="alert-success">CODIGO</th>
                                        <th class="alert-success">FECHA</th>
                                        <th class="alert-success">USUARIO</th>
                                        <th class="alert-success">CLIENTE</th>
                                        <th class="alert-info">ESTADO</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaPedidoVenta">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <div class="modal-body">
                        <!--CONTENIDO DE LA VENTANA--->
                        <a class="btn btn-lg btn-success col-md-1" id="btnGuardar" title="" onclick="InsertarPediVentas()" >Grabar*</a>
                        <a class="btn btn-lg btn-danger col-md-1" id="btnGuardarModificado" title="" onclick="EliminarDetalleMercaderiaPediVe()" >Modificar</a>
                        <!--HEADER DE LA VENTANA--->
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" onclick="limpiarcampoventa()" aria-hidden="true" title="Salir"></a>
                    </div>
                    <br>
                    <br>
                    <div class="panel">
                        <div class="panel panel-default">
                            <br>
                            <div class="panel-footer" style="font-weight: bold">Pedidos de Ventas</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="PVcodigo" style="font-weight: bold;font-size: 12pt" 
                                                name="PVcodigo" type="text" placeholder="Registro" class="form-control input-sm alert-success" required=""  autofocus >
                                    </div>
                                      <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="PVfecha" type="datetime" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="PVestad"  type="text" style="font-weight: bold;font-size: 12pt"
                                               placeholder=""class="form-control input-sm alert-success">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idPVestad" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-1">
                                        <input  id="PVusuar" style="font-weight: bold;font-size: 12pt" 
                                                type="text" placeholder="Ingresar Usuarios" class="form-control input-sm alert-success" required="">
                                    </div>
                                    <label class="col-md-1 control-label">Clientes</label>  
                                    <div class="col-md-3">
                                        <input  id="PVclient" style="font-weight: bold;font-size: 12pt" onclick="abrirpediVeCli()"
                                                type="text" placeholder="Ingresar Clientes" class="form-control input-sm alert-success" required="">
                                    </div>
                                    <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-3">
                                        <input maxlength="60" id="PVobserv" style="font-weight: bold;font-size: 12pt" 
                                               type="" class="form-control input-sm alert-success" required=""  autofocus onkeydown="">
                                    </div>  
                                    <div class="col-md-2">
                                        <input  id="PVtotal" style="font-weight: bold;font-size: 12pt"
                                                type="text" placeholder="Total" class="form-control input-sm alert-success" required="">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idPVclient" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                <input id="idmaterialGenerico" type="text" placeholder="Cod" class="form-control input-sm alert-success" onclick="AbrirModalMercaderiaPediVent()"
                                           style="font-weight: bold; font-size: 12pt"
                                           onkeyup="ValidacionSoloNumeros(this)" onchange="ValidacionSoloNumeros(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm alert-success" disabled="" id="iddescripcion" type="text" placeholder="Descirpcion del producto"
                                           style="font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input class="form-control input-sm alert-success" disabled="" id="idpreci" type="text" placeholder="Precio"
                                           style="font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="idcantidad" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm alert-success" 
                                           style="font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaderiaPediV();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="idmaterial" type="text" placeholder="" maxlength="3" class="form-control input-sm alert-success" 
                                           style="visibility: hidden; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 180px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleMercaderiaPediVe" onclick="selecTablaMercaderiaDet()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">cod</th>
                                            <th class="alert-success">CODIGO</th>
                                            <th class="alert-success">DESCRIPCION</th>
                                            <th class="alert-success">PRECIO</th>
                                            <th class="alert-success">CANTIDAD</th>
                                            <th class="alert-success">SUB TOTAL</th>
                                            <th style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                </tbody>
                            </table>
                        </div>
                    </div>
                        <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                            <span class="input-group-addon">Total a Pagar:</span>
                            <input class="form-control" id="total" style="font-size: 15px" type="text">
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalClientePediVe">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarClientPediV" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaClientePediV()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaClientePediVe" onclick="seleccionarCliente()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Codigo</th>
                                            <th>Clientes</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>                       
                    </div>
                </div>
            </div> 
        </div>                                   
        <div class="modal fade" id="ModalMercaderiaPediV">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercaderiaPedidoV" type="text" style="font-weight: bold" maxlength="20" class="form-control input-md"
                                 placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaPediVe()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderiaPediV" onclick="seleccionarMercaderiaPedidoV()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th style="display: none">Cod.</th>
                                            <th>Cod.Material</th>
                                            <th>Matarial</th>
                                            <th>Precio</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>                       
                    </div>
                </div>
            </div> 
        </div>
    </body>
</html>
