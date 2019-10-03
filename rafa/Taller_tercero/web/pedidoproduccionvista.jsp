<%-- 
    Document   : pedidoproduccionvista
    Created on : 01/08/2019, 08:20:04 AM
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
        <script src="Validador/pedidocompravalidar.js"></script>
         <title>Pedidos de Produccion</title>
    </head>
    <body>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesPedido">
                    <a id="btnNuevo" href="#ventanaPedido" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Pedido" data-toggle="modal" onclick="getcodigo_v()">Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetalle()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Pedido" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion de Pedido" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla: Pedidos de Produccion</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                            <input id="v_nropedido" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="v_estado" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold" >Obs.*</span>
                            <input id="v_obs" type="text" style="" class="form-control" disabled="" placeholder="Observaciones">
                        </div>
                    </div>
                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPedido" type="text" style="text-transform: uppercase; font-weight: bold" class="form-control " maxlength="20" onkeyup="buscadorTablaPedido()"placeholder="Buscar Lista...">
                        </div>
                    </div>

                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidos" onclick="seleccion()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th class="alert-info">CODIGO</th>
                                        <th class="alert-info">FECHA</th>
                                        <th class="alert-info">COMENTARIO</th>
                                        <th class="alert-info">USUARIO</th>
                                        <th class="alert-success">ESTADO</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!--<div class="form-group">
                    <div class="col-md-12" >
                        <div class=" col-md-4" id="datos">
                             <input id="usernameD" value="" style="text-transform: uppercase;background: #919292; border: 1px solid #919292; font-weight: bold"  type="text"  class="form-control">
                        </div>
                        <div class=" col-md-4">
                             <input id="" readonly=""style="text-transform: uppercase; font-weight: bold"  type="text"  class="form-control">
                        </div>
                        <div class=" col-md-4">
                             <input id="" readonly=""style="text-transform: uppercase; font-weight: bold"  type="text"  class="form-control">
                        </div>              
                    </div>
                </div>-->
            </form>
        </section>
        <div class="modal fade" id="ventanaPedido">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <div class="modal-body">
                        <!--CONTENIDO DE LA VENTANA--->
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardar" title="" onclick="InsertarPedidoCompra()" >Grabar*</a>
                        <!--HEADER DE LA VENTANA--->
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" onclick="limpiarcampopedidoc()" aria-hidden="true" title="Salir"></a>
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
                                        <input  disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                                name="codigo" type="text" placeholder="Registro" class="form-control input-sm alert-info" required=""  autofocus >
                                    </div>
                                      <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechapedido" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estado"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="PENDIENTE" value="PENDIENTE" class="form-control input-sm alert-info">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input  id="usuario" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                                type="text" placeholder="Registro" class="form-control input-sm alert-info" required="">
                                    </div>
                                    <label class="col-md-1 control-label">Obs.*</label>  
                                    <div class="col-md-7">
                                        <input maxlength="60" id="observ" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="" class="form-control input-sm alert-info" required=""  autofocus onkeydown="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="idmaterialGenerico" type="text" placeholder="Cod" class="form-control input-sm alert-info" onclick="abrir()"
                                           style="text-transform: uppercase; font-weight: bold; font-size: 12pt"
                                           onkeyup="ValidacionSoloNumeros(this)" onchange="ValidacionSoloNumeros(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm alert-info" disabled="" id="iddescripcion" type="text" placeholder="Descirpcion del producto"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input class="form-control input-sm alert-info" disabled="" id="idpreci" type="text" placeholder="Precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="idcantidad" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm alert-info" 
                                           style="text-transform: uppercase; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaderia();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="idmaterial" type="text" placeholder="" maxlength="3" class="form-control input-sm alert-info" 
                                           style="text-transform: uppercase;visibility: hidden; font-weight: bold; font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 180px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleMercaderia" onclick="selecTablaMercaderiaDet()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">cod</th>
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">PRECIO</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info">SUB TOTAL</th>
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
                            <input class="form-control" id="total" style="font-size: 15px" type="text"
                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="grillaProducto">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercaderia" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderia()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderia" onclick="seleccionMercaderia()">
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
