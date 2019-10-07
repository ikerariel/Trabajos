<%-- 
    Document   : presupuestocompravista
    Created on : 10/06/2019, 09:51:54 PM
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
        <script src="Validadores/presupuestocompravalidador.js"></script>
         <title>Presupuesto de Compras</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesPresupuestoCompra">
                    <a id="btnNuevo" href="#ventanaPresupuestoCompra" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Factura Compras" data-toggle="modal"
                       onclick="getcodigoPresuComp(); fechaactualPresuCompra(); MostrarEstadoPresu()">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Factuta Compras" data-toggle="modal" onclick="recuperaDetallePresuCompra()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Factura">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Factura Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Factura Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
                </div>
                
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Presupuesto de Compras</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                                <input id="PresuNro" type="text" style="background-color: #ffffcc" class="form-control" disabled="" placeholder="Numero de presupuesto">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadoPresuC" type="text" style="background-color: #ffffcc" class="form-control" disabled="" placeholder="Estado de presupuesto">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarPlanillaCompraPresu" type="text" style="text-transform: uppercase; font-weight: bold; background-color: #ffffcc" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaCompraPresu()"placeholder="Buscar Pediod...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaCompraPresu" onclick="seleccionarPresupuestoCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-warning">CODIGO</th>
                                            <th class="alert-warning">FECHA</th>
                                            <th class="alert-warning">PROVEEDOR</th>
                                            <th class="alert-warning">USUARIO</th>
                                            <th class="alert-warning">TIPO</th>
                                            <th class="alert-danger">ESTADO</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta"></tbody>
                                </table>
                            </div>
                        </div>
                </div>
            </form>
        </section>
        <div class="modal fade" id="ventanaPresupuestoCompra">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-md btn-warning col-md-1"  id="btnGuardar" title="" onclick="insertarpresuCompra()" >Guardar</a>
                        <a class="btn btn-md btn-danger col-md-1"  id="btnGuardarModificado" title="" onclick="ModificarPresupuestoCopmra()" >Modificar</a>
                        <a class="close  btn btn-md btn-danger glyphicon glyphicon-off" data-dismiss="modal" onclick="limpiarcampoPresupuesto()" aria-hidden="true" title="Salir"></a>
                    </div>
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Presupuesto de Compras</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="codigo" class="form-control input-sm alert-warning">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="PresuCompFecha" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-warning">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="PresuCompEstado"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-warning">
                                    </div>                                   
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="PresuCompUsuario" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-warning">
                                    </div>
                                    <label class="col-md-1 control-label">Proveedor</label>  
                                    <div class="col-md-3">
                                        <input id="PresuCompProvee" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" autofocus=""
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-warning" onclick="abrirproveedorPresu()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">PedidoCompra</label>  
                                    <div class="col-md-3">
                                        <input id="PresuCompPedidoC" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Pedido Compra" class="form-control input-sm alert-warning" 
                                               onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)" onclick="AbrirPedidoCompraPresu()"
                                               onkeydown="
                                                   if (event.keyCode === 13) {
                                                       RecuperarDetallePedidoC();
                                                   }">
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">C.Tipo</label>  
                                    <div class="col-md-2">
                                        <input id="PresuCompTipo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="tipo de compra" class="form-control input-sm alert-warning" onclick="AbrirtipoComprasPresu()"
                                               onkeyup="controlarcampost(this)" onchange="controlarcampost(this)">
                                    </div> 
                                    <label class="col-md-1 control-label">Intervalo</label>  
                                    <div class="col-md-1">
                                        <input id="PresuCompIntervalo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="number" placeholder="" class="form-control input-sm alert-warning">
                                    </div>
                                    <label class="col-md-1 control-label">Nro.Cuota</label>  
                                    <div class="col-md-1">
                                        <input id="PresuCompNroCuota" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="number" placeholder="" class="form-control input-sm alert-warning" onclick="calculomonto()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">Monto</label>  
                                    <div class="col-md-3">
                                        <input disabled="" id="PresuCompMonto" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="" class="form-control input-sm alert-warning">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="PresuCompIdEstado" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="PresuCompIdProvee" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="PresuCompidTipo" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiMerca" type="text" placeholder="Cod" class="form-control input-sm" onclick="AbrirModalMercaderiaPresu()"
                                           style="font-weight: bold; background-color:#ffffcc; font-size: 12pt"
                                           onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="nombreMerca" type="text" placeholder="Descirpcion del producto"
                                           style="font-weight: bold; background-color:#ffffcc;font-size: 12pt"
                                           onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioMerca" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="font-weight: bold; background-color: #ffffcc; font-size: 12pt"
                                           onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="cantidadMerca" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="font-weight: bold; background-color:#ffffcc; font-size: 12pt"
                                           onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaCompraGrillaPresu();
                                                   }">
                                </div>
                                <div class="col-md-1">
                                    <input disabled="" id="codMerca" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div class="table-responsive" style="height: 150px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetallePresuCompra" onclick="SeleccionarDetallePresuCompra()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th class="alert-warning">CODIGO</th>
                                            <th class="alert-warning">DESCRIPCION</th>
                                            <th class="alert-warning">PRECIO</th>
                                            <th class="alert-warning">CANTIDAD</th>
                                            <th class="alert-warning">SUB TOTAL</th>
                                            <th  class="alert-danger" style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                    <span class="input-group-addon">Total a Pagar:</span>
                    <input class="form-control" id="total" style="font-size: 15px; background-color:#ffffcc;" type="text"
                           onkeyup="SoloNumerosPresu(this)" onchange="SoloNumerosPresu(this)">
                    </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalProveedorPresu">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarProveedorPresu" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaProveedPresuC()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedoresPresu" onclick="seleccionarProveedor()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Codigo</th>
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
        <div class="modal fade" id="ModalPedidoCompraPresu">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla Pedido de Compra</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarPedidoCompraPresu" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaPedidoCompraPresu()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPedidoCompraPresu" onclick="seleccionarPedidoCompraPresu()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>NRO.ORDEN</th>
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
        <div class="modal fade" id="ModalPresuTipo">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrartipocompraPresu" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablatipocompraPresu()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablatipocompraPresu" onclick="seleccionartipocompraPresu()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr>
                                            <th>codigo</th>
                                            <th>tipo Compras</th>
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
        <div class="modal fade" id="ModalMercaderiaPresu">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercaderiaCompraPresu" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaCompraPresu()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 300px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderiaCompraPresu"
                                       onclick="seleccionarMercaderiaCompra()">
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
    </body>
</html>
