<%-- 
    Document   : ordencompravista
    Created on : 18/06/2018, 10:33:32 AM
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
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Validadores/ordencompravalidador.js"></script>
         <title>Orden de Compras</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesOrdenCompra">
                    <a id="btnNuevo" href="#ventanaOrdenCompra" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Orden de Compras" data-toggle="modal"
                       onclick="getcodigoOrden(); getestadoOrden(); fechaactual()" >Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperarDetalleOrdenCO()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Orden de Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesordencompra()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla Orden de Compras</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon alert-danger" style="font-weight: bold;font-size: 12pt">Pedido Nro*</span>
                                <input id="ordenNro" type="text" style="font-weight: bold;font-size: 12pt" class="form-control alert-success" disabled="" placeholder="Numero de Orden">
                                <span class="input-group-addon alert-danger" style=" font-weight: bold" >Estado*</span>
                                <input id="estadOrdenP" type="text" style="font-weight: bold;font-size: 12pt" class="form-control alert-success" disabled="" placeholder="Estado de Orden">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon alert-danger" style="font-weight: bold;font-size: 12pt">Buscar</span>
                                <input id="filtrarOrdenC" type="text" style="font-weight: bold;font-size: 12pt" 
                                       class="form-control alert-success " maxlength="20" onkeyup="buscadorPlanillaOrden()"placeholder="Buscar Pedido...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenC" onclick="seleccionOrdenCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">NRO. Orden</th>
                                            <th class="alert-success">FECHA</th>
                                            <th class="alert-success">PROVEEDOR</th>
                                            <th class="alert-success">USUARIO</th>
                                            <th class="alert-success">C. PEDIDO</th>
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
                        <a class="btn btn-md btn-success col-md-1"  id="btnGuardar" title="" onclick="InsertarOrden()" >Guardar</a>
                        <a class="btn btn-md btn-danger col-md-1"  id="btnModificar" title="" onclick="modificarOrdenC()" >Modificar</a>
                        <a class="close  btn btn-md btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" onclick="limpiarcampoOrdenp()" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nueva Orden de Compras</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Registro" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechaOrden" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadoOrden"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-success">
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
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-success">
                                    </div>
                                    <label class="col-md-1 control-label">Proveedor</label>  
                                    <div class="col-md-3">
                                        <input id="proveeOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-success" onclick="abrirprov()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">No.Pedidos</label>  
                                    <div class="col-md-3">
                                        <input id="pedoidoOrden" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Pedidos" class="form-control input-sm alert-success" onclick="abrirpedi()"
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
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="idmercadGenerico" type="text" placeholder="Cod" class="form-control input-sm alert-success" onclick="abrirMerca(); getcodigoMercaderia()"
                                           style="font-weight: bold;font-size: 12pt"
                                           onkeyup="SoloNumerosOrden(this)" onchange="SoloNumerosOrden(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm alert-success" disabled="" id="iddescrip" type="text" placeholder="Descirpcion del producto"
                                           style="font-weight: bold;font-size: 12pt"
                                           onkeyup="SoloNumerosOrden(this)" onchange="SoloNumerosOrden(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="PrecioMer" disabled="" class="form-control input-sm alert-success" type="text" placeholder="Ingrese precio"
                                           style="font-weight: bold;font-size: 12pt"
                                           onkeyup="SoloNumerosOrden(this)" onchange="SoloNumerosOrden(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="idcanti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm alert-success" 
                                           style="font-weight: bold;font-size: 12pt"
                                           onkeyup="SoloNumerosOrden(this)" onchange="SoloNumerosOrden(this)"
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
                        <div class="table-responsive" style="height: 150px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleOrdenCompra" onclick="seleccionDetalleMerecadOrden()">
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
                            <input class="form-control" id="total" style="font-size: 15px" type="text"
                           onkeyup="SoloNumerosOrden(this)" onchange="SoloNumerosOrden(this)">
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
