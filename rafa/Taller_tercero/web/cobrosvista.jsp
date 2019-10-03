<%-- 
    Document   : cobrosvista
    Created on : 12/07/2019, 08:12:23 PM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Validadores/ordencompravalidador.js"></script>
         <title>Cobros</title>
    </head>
    <body>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesOrdenCompra">
                    <a id="btnNuevo" href="#ventanaOrdenCompra" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       onclick="getcodigoOrden(); getestado(); getusuario();fechaactual()">Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetalleOrdenCO()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick=></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenComp()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Gestionar Cobros</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">produccion Nro*</span>
                                <input id="ooooo" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero ....">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadOrdenP" type="text" style="" class="form-control" disabled="" placeholder="Estado....">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarOrdenC" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaOrden()"placeholder="Buscar...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTab" onclick="seleccionOrdenCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">COBROS NRO.</th>
                                            <th class="alert-success">FECHA</th>
                                            <th class="alert-success">USUARIO</th>
                                            <th class="alert-success">MONTO</th>
                                            <th class="alert-danger">ESTADO</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaOrdenCompra">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardar" title="" onclick="InsertarOrden()" >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nuevo Cobros</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="c" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fecha" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-2">
                                        <input disabled id="esta"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="" class="form-control input-sm alert-danger">
                                    </div>
                                    <div class="col-md-2">
                                        <input disabled id="usuarioO" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="CUENTA" class="form-control input-sm alert-danger">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioO" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="" class="form-control input-sm alert-danger">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">AperCierre</label>  
                                    <div class="col-md-3">
                                        <input id="pedoidoOr" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="" class="form-control input-sm alert-danger" onclick="abrirpedi()"
                                               onkeydown="
                                                   if (event.keyCode === 13) {
                                                       recuperarDetallePedido();
                                                   }">
                                    </div>
                                    <label class="col-md-1 control-label">Efectivo</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioO" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="" class="form-control input-sm alert-danger">
                                    </div>
                                    
                                    <div class="col-md-1">
                                        <input id="idestadOrden" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input id="idproveedor" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="idusuaOrden" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">factura nro.</label>
                                <div class="col-md-1">
                                    <input id="idmercadGenerico" type="text" placeholder="" class="form-control input-sm" onclick="abrirMerca(); getcodigoMercaderia()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>
                                <label class="col-md-1 control-label">cuenta nro.</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="iddescrip" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">cobros</label>
                                <div class="col-md-2">
                                    <input id="PrecioMer" disabled="" class="form-control input-sm" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Monto</label>
                                <div class="col-md-1">
                                    <input id="idcanti" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaderiaGrilla();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="idmercad" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 80px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleOrdenCompra" onclick="seleccionDetalleMerecadOrden()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th>FACTURA NRO</th>
                                            <th>CUENTA NRO</th>
                                            <th>COBROS</th>
                                            <th>MONTO</th>
                                            <th style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">cuenta nro</label>
                                <div class="col-md-1">
                                    <input id="" type="text" placeholder="" class="form-control input-sm" onclick="abrirMerca(); getcodigoMercaderia()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>
                                <label class="col-md-1 control-label">Tarjeta nro</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Cobros</label>
                                <div class="col-md-2">
                                    <input id="" disabled="" class="form-control input-sm" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Monto</label>
                                <div class="col-md-1">
                                    <input id="" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaderiaGrilla();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 80px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleOrdenCompra" onclick="seleccionDetalleMerecadOrden()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th>CUENTA NRO</th>
                                            <th>TARJETA NRO</th>
                                            <th>COBROS</th>
                                            <th>MONTO</th>
                                            <th style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cheque nro</label>
                                <div class="col-md-1">
                                    <input id="" type="text" placeholder="" class="form-control input-sm" onclick="abrirMerca(); getcodigoMercaderia()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>
                                <label class="col-md-1 control-label">Cuenta nro</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">entidad</label>
                                <div class="col-md-2">
                                    <input id="" disabled="" class="form-control input-sm" type="text" placeholder=""
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Monto</label>
                                <div class="col-md-1">
                                    <input id="" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaderiaGrilla();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="idmercad" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 80px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleOrdenCompra" onclick="seleccionDetalleMerecadOrden()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th>CHEQUE NRO</th>
                                            <th>CUENTA NRO</th>
                                            <th>ENTIDAD</th>
                                            <th>MONTO</th>
                                            <th style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt"></tbody>
                            </table>
                        </div>
                    </div>    
                        <%--<div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                    <span class="input-group-addon">Total a Pagar:</span>
                    <input class="form-control" id="total" style="font-size: 15px" type="text"
onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">--%>
                    </div>
                    </div>
                </div>
            </div>
    </body>
</html>
