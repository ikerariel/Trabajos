<%-- 
    Document   : facturacompravista
    Created on : 27/06/2018, 08:19:14 PM
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
        <script src="Validadores/facturacompravalidador.js"></script>
         <title>Factura Compras</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesFacturaCompra">
                    <a id="btnNuevo" href="#ventanaFacturaCompra" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Factura Compras" data-toggle="modal"
                       onclick="getcodigoCompra(); fechaactualCompra(); MostrarEstado(); MostrarUsuario(); MostrarSucursalF()">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Factuta Compras" data-toggle="modal" onclick="recuperarDetalleFacturaC()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Factura">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Factura Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Factura Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
                </div>
                
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Factura Compras</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Pedido Nro*</span>
                                <input id="nrofacturaP" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadofacturaP" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarPlanillaCompra" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaCompra()"placeholder="Buscar Pediod...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaCompra" onclick="seleccionarFacturaCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">CODIGO</th>
                                            <th class="alert-info">FACTURA NRO</th>
                                            <th class="alert-success">FECHA</th>
                                            <th class="alert-info">TIPO</th>
                                            <th class="alert-success">PROVEEDOR</th>
                                            <th class="alert-success">USUARIO</th>
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
        <div class="modal fade" id="ventanaFacturaCompra">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-md btn-danger col-md-1"  id="btnGuardar" title="" onclick="inserFacCompra()" >Guardar</a>
                        <a class="btn btn-md btn-danger col-md-1"  id="btnGuardarModificado" title="" onclick="updatfacCompra()" >Modificar</a>
                        <a class="close  btn btn-md btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Factura de Compras</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigo" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="factuCompFecha" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="factuCompEstado"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-danger">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="factuCompIdEstado" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="factuCompUsuario" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Proveedor</label>  
                                    <div class="col-md-3">
                                        <input id="factuCompProvee" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" autofocus=""
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-danger" onclick="abrirproveedor()">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">OrdenCompra</label>  
                                    <div class="col-md-3">
                                        <input id="factuCompOrdenC" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Orden Compra" class="form-control input-sm alert-danger" 
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)" onclick="AbrirOrdenCompra()"
                                               onkeydown="
                                                   if (event.keyCode === 13) {
                                                       RecuperarDetalleOrdenC();
                                                   }">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="factuCompIdProvee" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="factuCompIdUsuario" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">N.Factura</label>  
                                    <div class="col-md-3">
                                        <input id="factuCompNroFactura" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Nro de Factura" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)"onclick="abrirprov()">
                                    </div>
                                    <label class="col-md-1 control-label">C.Tipo</label>  
                                    <div class="col-md-3">
                                        <input id="facturatipoC" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese tipo de compra" class="form-control input-sm alert-danger" onclick="tipoComprasAbrir()">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="factuIdSucursal" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="factuidtipocompras" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                         <input disabled="" id="codimpuesto" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Intervalo</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompIntervalo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="number" placeholder="Ingrese Intervalo" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)"onkeydown="
                                                   if (event.keyCode === 13) { pasarCampos();}">
                                    </div>
                                    <label class="col-md-1 control-label">Nro.Cuota</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompNroCuota" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="number" placeholder="Ingrese Cuota" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)"
                                                onkeydown="
                                                     if (event.keyCode === 13) {
                                                       calculomontoconp();
                                                   }">
                                    </div>
                                    
                                    <label class="col-md-1 control-label">Monto</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompMonto" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Monto" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)">
                                    </div>
                                    <label class="col-md-1 control-label">Cuota</label>  
                                    <div class="col-md-2">
                                        <input id="faccuotamonto" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Monto" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)">
                                    </div>
                                     
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiMerca" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirModalMercaderia()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="nombreMerca" type="text" placeholder="Descirpcion del producto"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>
                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioMerca" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                              
                                <div class="col-md-1">
                                    <input id="cantidadMerca" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaCompraGrilla();
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
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleFacturaCompra" onclick="SeleccionarDetalleFacturaCompra()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">PRECIO</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info">SUB TOTAL</th>
                                            <th  style="display: none" class="alert-info" >imp</th>
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
                    <input class="form-control" id="total" style="font-size: 15px" type="text"
                           onkeyup="ValidacionesSoloNumerosFac(this)" onchange="ValidacionesSoloNumerosFac(this)">
                    </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalFacturaTipo">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrartipocompra" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablatipocompra()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablatipocompra" onclick="seleccionartipocompra()">
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
        <div class="modal fade" id="ModalProveedor">
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
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="seleccionarProveedor()">
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
        <div class="modal fade" id="ModalOrdenCompra">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla Orden de Compra</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarOrdenCompra" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaOrdenCompra()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenCompra" onclick="seleccionarOrdenCompra()">
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
        <div class="modal fade" id="ModalMercaderia">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercaderiaCompra" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaCompra()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 300px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderiaCompra"
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
