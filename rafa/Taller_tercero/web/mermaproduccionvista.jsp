<%-- 
    Document   : mermaproduccionvista
    Created on : 04/08/2019, 10:00:00 PM
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
         <title>Merma de Produccion</title>
    </head>
    <body>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesOrdenCompra">
                    <a id="btnNuevo" href="#ventanaOrdenCompra" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       onclick="getcodigoOrden(); getestadoOrden(); getusuarioOrden(); fechaactual()" >Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetalleOrdenCO()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Orden de Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenComp()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla Merma de Produccion</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                                <input id="ordenNro" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Merma Produccion">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadOrdenP" type="text" style="" class="form-control" disabled="" placeholder="Estado de Merma Produccion">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarOrdenC" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaOrden()"placeholder="Buscar Lista...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenC" onclick="seleccionOrdenCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">FECHA</th>
                                            <th class="alert-info">MERMA</th>
                                            <th class="alert-info">USUARIO</th>
                                            <th class="alert-success">ESTADO</th>
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
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnModificar" title="" onclick="ModificarDetalleOrdenCo()" >Modificar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" onclick="limpiarcampoOrdenp()" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nueva Merma de Produccion</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechaOrden" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadoOrden"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="" class="form-control input-sm alert-info">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idestadOrden" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Etapas</label>  
                                    <div class="col-md-3">
                                        <input id="proveeOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="" class="form-control input-sm alert-info" onclick="abrirprov()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">Orden N°</label>  
                                    <div class="col-md-3">
                                        <input id="pedoidoOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="" class="form-control input-sm alert-info" onclick="abrirpedi()"
                                               onkeydown="
                                                   if (event.keyCode === 13) {
                                                       recuperarDetallePedido();
                                                   }">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idproveedor" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="idusuaOrden" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Observacion</label>  
                                    <div class="col-md-10">
                                        <input disabled id="usuarioOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="" class="form-control input-sm alert-info">
                                    </div>
                                   
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
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="idmercadGenerico" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirMerca(); getcodigoMercaderia()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="iddescrip" type="text" placeholder="Descirpcion del producto"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Medidas</label>
                                <div class="col-md-2">
                                    <input id="PrecioMer" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese Medidas"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="idcanti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
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
                        <div class="table-responsive" style="height: 180px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleOrdenCompra" onclick="seleccionDetalleMerecadOrden()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">cod</th>
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">MEDIDAS</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th style="width: 30px" class="alert-success"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                </tbody>
                            </table>
                        </div>
                        </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="grillaMercad">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <input class="btn btn-sm btn-success" type="button" value="INSERTAR" onclick="insertarMercaderia()" />
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="col-md-2">
                            <input id="codmerca" style="font-weight: bold; font-size: 12pt" 
                                type="text" placeholder="cod." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-2">
                            <input id="codgenericomerca" style="font-weight: bold; font-size: 12pt" 
                                type="text" placeholder="codG." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-6">
                            <input id="descrimerca" style="font-weight: bold; font-size: 12pt" 
                                type="text" placeholder="Mercaderia descripcion.." class="form-control input-sm alert-success">
                        </div>
                        <div class="col-md-2">
                            <input id="preciomerca" style="font-weight: bold; font-size: 12pt" 
                                type="text" placeholder="Precio" class="form-control input-sm alert-success">
                        </div>
                        <br>
                        <br>
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercad" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercad()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 300px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercad"
                                       onclick="seleccionMercad()">
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
        <div class="modal fade" id="grillaProveed">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedor" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaProveed()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="seleccionProvee()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Proveedor</th>
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
        <div class="modal fade" id="grillaPedidos">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla de Pedidos</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarPedi" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaPedi())">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidos" onclick="seleccionPedidos()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>NRO. PEDIDO</th>
                                            <th>FECHA</th>
                                            <th>USUARIO</th>
                                            <th class="alert-info">ESTADO</th>
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
        <div class="modal fade" id="grillaPresupuesto">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla de Presupuesto</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarPresupuesto" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaPresupuesto()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPresupuesto" onclick="seleccionPresupuesto()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>ORDEN NRO</th>
                                            <th>FECHA</th>
                                            <th>TIPO</th>
                                            <th class="alert-info">ESTADO</th>
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
