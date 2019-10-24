
<%-- 
    Document   : FacturasCompras
    Created on : 05/09/2018, 10:29:16 AM
    Author     : user
--%>    

<!DOCTYPE html>
<html>
        <%

        HttpSession sessionActivaUser = request.getSession();
        if (sessionActivaUser.getAttribute("user") == null) {
            response.sendRedirect("/TALLERCASAJC/acceso.jsp");
        }

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="validador/FacturasComprasvalidad.js"></script>
        <script src="validador/genericoJS.js"></script>
        <title>FACTURA COMPRAS</title>

        <style>
            #scrollPlanilla{
                overflow: scroll;
                height:200px;
            }  
        </style> 
    </head>
    <body>
   <%@include file="viwmenu.jsp" %>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesFacturasCompras">
                    <a id="btnNuevoCompras" href="#ventanaFacturasCompras" class="btn btn-lg btn-success" style=" font-weight: bold"   title="Nuevo Factura Compras" data-toggle="modal"
                       onclick="">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Factuta Compras" data-toggle="modal" onclick="recuperarCompra()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Factura">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Factura Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Factura Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="informesvarios(4)"></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE FACTURAS COMPRAS</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Fac. Nro:*</span>
                            <input id="nrofacturaP" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Pedido">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadofacturaP" type="text" style="" class="form-control" disabled="" placeholder="Estado de Pedido">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtrarPlanillaCompras" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" onkeyup="buscadorPlanillaCompras()"placeholder="Buscar Pediod...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaCompra" onclick="seleccionarFacturasCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th class="">CODIGO</th>
                                        <th class="">FACTURA NRO</th>
                                        <th class="">FECHA</th>
                                        <th class="">PROVEEDOR</th>
                                        <th class="">USUARIO</th>
                                        <th class="">ESTADO</th>
                                    </tr>
                                </thead>
                                <tbody id="table_deta"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->

        <div class="modal fade" id="ventanaFacturasCompras">
            <div class="modal-dialog" style="width: 1300px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-sm btn-primary col-md-1" style="display: none" id="btnguardarCompra" title="" onclick="InsertarFacturasCompras()" >Guardar</a>
                        <a class="btn btn-sm btn-success col-md-1" style="display: none" id="btnmModificarCompra" title="" onclick="ModificarDetOrdenComprass()" >Guardar</a>
                        <a class="close  btn btn-sm btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">FACTURAS DE COMPRAS</div>
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Nro.</label>  
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
                                        <input disabled id="factuCompEstado"    type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="PENDIENTE" class="form-control input-sm alert-success">
                                    </div>

                                    <div class="col-md-2">
                                        <input id="factuCompIdEstado" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="col-md-2" style="display: none">
                                        <input disabled id="factuCompUsuario" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-2 control-label">Nro. Orden Compra</label>  
                                    <div class="col-md-2">
                                        <input list="listaordencompra" id="factuCompOrdenC" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Orden Compra" class="form-control input-sm alert-danger" 
                                               onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()" 
                                               onkeydown="
                                                       if (event.keyCode === 13) {
                                                           RecuperarDetOrdenComprass();
                                                       }">
                                                                                             <datalist id="listaordencompra" ></datalist>                                                                                                    
                                    </div>
                                        <label class="col-md-1 control-label">N.Factura</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompNroFactura" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Nro de Factura" class="form-control input-sm alert-danger" onclick="">
                                    </div>
                                    <label class="col-md-1 control-label">Proveedores</label>  
                                    <div class="col-md-3">
                                        <input id="factuCompProvee" disabled="" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" autofocus=""
                                               type="text" placeholder="Ingrese Proveedor" class="form-control input-sm alert-danger" onclick="abrirproveedores()">
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
                                    <div class="col-md-2" style="display: none">
                                        <input disabled id="factuCompSucursal" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-2 control-label">Tipo Compra</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompTipo" disabled=""  style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Pedidos" class="form-control input-sm alert-danger">
                                        <input id="fidtipocompra" style="visibility: hidden;" type="text">
                                    </div>
                                    <label class="col-md-1 control-label">Intervalo</label>  
                                    <div class="col-md-1">
                                        <input id="factuCompIntervalo" disabled=""  style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Intervalo" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                    </div>
                                    <label class="col-md-1 control-label">Cant.Cuota</label>  
                                    <div class="col-md-1">
                                        <input id="factuCompNroCuota" disabled=""  style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Cuota" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                    </div>
                                    <label class="col-md-1 control-label">Monto</label>  
                                    <div class="col-md-2">
                                        <input id="factuCompMonto" disabled=""  style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Monto" class="form-control input-sm alert-danger"
                                               onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="factuCompIdSucursal" style="visibility: hidden;" type="text">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiArti" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirModalArticulos()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
                                </div>

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-3">
                                    <input class="form-control input-sm" disabled="" id="nombreArti" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>

                                <label class="col-md-1 control-label">Precio</label>
                                <div class="col-md-2">
                                    <input id="precioArti" disabled="" class="form-control input-sm" type="text" placeholder="Ingrese precio"
                                           style="text-transform: uppercase; font-weight: bold; background-color: #e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cantidad*</label>
                                <div class="col-md-1">
                                    <input id="cantidadArti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarArtiComprasGrilla();
                                                   }">
                                </div>

                                <div class="col-md-1">
                                    <input disabled="" id="codArti" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                    <input disabled="" id="codImpuesto" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive" style="height: 115px">
                                <table class="table table-striped table-bordered table-hover table input-sm" id="miTablaDetFacturasCompras" onclick="SeleccionarDetFacturasCompras()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="">CODIGO</th>
                                            <th class="">DESCRIPCION</th>
                                            <th class="">PRECIO</th>
                                            <th class="">CANTIDAD</th>
                                            <th class="">SUB TOTAL</th>
                                            <th class="" style="display: none"></th>
                                             <th>Opcion</th>
                                        </tr>
                                    </thead>
                                    <tbody id="table_deta" style="font-weight: bold;font-size: 10pt">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-xs-3 col-xs-offset-9 input-group input-group-sm">
                            <span class="input-group-addon">Total a Pagar:</span>
                            <input class="form-control" id="vtotalCompra" style="font-size: 15px" type="text"
                                   onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
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
                                <input id="filtrarProveedores" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaProveedores()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->

                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaProveedores" onclick="seleccionarProveedores()">
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



                    <!--HEADER DE LA VENTANA                                     ORDEN COMPRAS--->


                    <div class="modal-header">
                        <div>Planilla Orden de Compra</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA        ORDEN COMPRAS--->
                    <DIV class="modal-body">

                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarOrdenCompra" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pedidos..." onkeyup="buscadorTablaOrdenComprass()">
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaOrdenComp" onclick="seleccionarOrdenComprass()">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>NRO.ORDEN</th>
                                            <th>FECHA</th>
                                            <th>USUARIO</th>
                                            <th class="alert-info">ESTADOS</th>
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
        <div class="modal fade" id="ModalArticulos">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA--->

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <div class="container-fluid">
                        <div class="form-group">
                            <input id="filtrarArticulosCompras" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                   class="form-control input-md" placeholder="Buscar Articulos..." onkeyup="buscadorTablaArticulosCompras()">
                        </div>
                    </div>
                    <div class="panel-body">
                        <!-- Tabla detalle -->
                        <div id="scroll" class="table-responsive" style="height: 300px" >
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaArticulosCompras"
                                   onclick="seleccionarArticulosCompras()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none">Cod.</th>
                                        <th>Cod.Material</th>
                                        <th>Matarial</th>
                                        <th>Precio</th>
                                        <th style="display: none"></th>
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
