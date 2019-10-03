<%-- 
    Document   : notacreditodebitoventavista
    Created on : 31/07/2019, 05:58:46 PM
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
        <script src="Validadores/notacreditodebitovalidador.js"></script>
         <title>Nota de Credito y Debito de Ventas</title>
    </head>
    <body>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesNota">
                    <a id="btnNuevo" href="#ventanaNota" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nueva Nota..." data-toggle="modal"
                       onclick="getcodigoNota(); fechaactualNota(); mostrarEstadoN(); mostrarUsuarioN(); mostrarSucurN()">Nuevo </a>
                    <a id="btnModificar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Modificar Factuta Compras" data-toggle="modal" onclick="recuperarDetalleNotaC()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Factura">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Factura Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Factura Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
                  <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportes()"></a>
              </div>
                
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla Nota de credito y debito de Ventas</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">NOTA Nro*</span>
                                <input id="nroNotaP" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Nota">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadoNotaP" type="text" style="" class="form-control" disabled="" placeholder="Estado de Nota">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarPlanillaNota" type="text" style="text-transform: uppercase; font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaNota()"placeholder="Buscar Lista...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaPlanillaNota" onclick="seleccionarNotaPlanilla()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">CODIGO</th>
                                            <th class="alert-info">TIPO</th>
                                            <th class="alert-success">FECHA</th>
                                            <th class="alert-info">MOTIVO</th>
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
        <div class="modal fade" id="ventanaNota">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnGuardar" title="" onclick="insertarNota()" >Guardar</a>
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnGuardarModificado" title="" onclick="ModificarDetalleOrdenCo()" >Modificar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nota de Credito y Debito de Ventas</div>
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
                                        <input disabled id="notaFecha" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="notaEstado"  type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese estado" class="form-control input-sm alert-danger">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="notaIdEstado" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="notaUsuario" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Nota Tipo</label>  
                                    <div class="col-md-3">
                                        <input id="notatipo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese tipo de nota" class="form-control input-sm alert-danger">
                                    </div>
                                    <label class="col-md-1 control-label">Factura</label>  
                                    <div class="col-md-3">
                                        <input id="notafactuComp" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Factura Ventas" class="form-control input-sm alert-danger" 
                                               onclick="AbrirFacturaNota()" autofocus="" onkeydown="
                                                                                            if (event.keyCode === 13) {
                                                                                                RecuperarDetalleFacturaNota();
                                                                                            }">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="notaidSucursal" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input id="notaIdUsuario" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Motivo</label>  
                                    <div class="col-md-10">
                                        <input id="notaMotivo" style="text-transform: uppercase; font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese motivo" class="form-control input-sm alert-danger">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input id="codgenericiMerca" type="text" placeholder="Cod" class="form-control input-sm" onclick="abrirModalMercaderiaNota()"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros()" onchange="ValidacionesSoloNumeros()">
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
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                                </div>

                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-1">
                                    <input id="cantidadMerca" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaNotaGrilla();
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
                        <div class="table-responsive" style="height: 180px">
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleNota" onclick="SeleccionarDetalleFacturaNota()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                            <th style="display: none"></th>
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">PRECIO</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info">SUB TOTAL</th>
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
                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)">
                    </div>
                    </div>
                </div>
            </div> 
        </div>
        <div class="modal fade" id="ModalFacturaNota">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <div>Planilla Factura Compra</div>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <DIV class="modal-body">
                        
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarFacturaNota" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Pediod..." onkeyup="buscadorTablaNota()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaFacturaNota" onclick="seleccionaFacturaNota()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th>Factura</th>
                                            <th>FECHA</th>
                                            <th>TIPO</th>
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
                                   class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaNota()">
                        </div>
                    </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                        <div id="scroll" class="table-responsive" style="height: 300px" >   
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercaderiaCompra"
                                   onclick="seleccionarMercaderiaNota()">
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
