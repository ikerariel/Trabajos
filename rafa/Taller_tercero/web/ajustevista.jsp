<%-- 
    Document   : ajustevista
    Created on : 25/07/2019, 03:12:29 PM
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
        <script src="Validadores/ajustesvalidador.js"></script>
         <title>Ajuste</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesAjuste">
                    <a id="btnNuevo" href="#ventanaAjuste" class="btn btn-lg btn-success" style=" font-weight: bold"  title="Nuevo Ajuste" data-toggle="modal"
                       onclick="getcodigoAjustes(); fechaactualAjuste(); MostrarEstadoAjuste(); MostrarUsuarioAjuste()" >Nuevo </a>
                    <a id="btnRecuperar" class="btn btn-lg btn-info" style=" font-weight: bold" title="Recuperar Orden de Compras" data-toggle="modal" onclick="recuperaDetalleAjustes()">Recuperar </a>
                    <a id="btnAnular" class="btn btn-lg btn-danger" style=" font-weight: bold" title="Anular Pedido">Anular*</a>
                    <a id="btnConfirmar" class="btn btn-lg btn-warning glyphicon glyphicon-ok" style=" font-weight: bold" title="Confirmar Orden de Compras" onclick=></a>
                    <a id="btnRevertir" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Confirmacion Orden de Compras" onclick=""></a>
                    <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" title="Reporte de Panilla" onclick="reportesOrdenComp()"></a>

                </div>
                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">Planilla de Ajuste</div>
                    <div class="">
                        <div class="input-group  input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Ajuste nro</span>
                                <input id="ajusteNro" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="Numero de Ajuste">
                                <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                                <input id="estadoajuste" type="text" style="" class="form-control" disabled="" placeholder="Estado de Ajuste">
                        </div>
                    </div>
                    <div class="">
                            <div class="input-group input-sm">
                                <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                                <input id="filtrarPlanillaAjuste" type="text" style="font-weight: bold" 
                                       class="form-control " maxlength="20" onkeyup="buscadorPlanillaAjuste()"placeholder="Buscar Lista...">
                            </div>
                        </div>
                    <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scrollPlanilla" class="table-responsive">
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaAjuste" onclick="seleccionarListaAjuste()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-success">NUMEROS.</th>
                                            <th class="alert-info">FECHA</th>
                                            <th class="alert-success">MOTIVO</th>
                                            <th class="alert-info">USUARIO</th>
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
        <div class="modal fade" id="ventanaAjuste">
            <div class="modal-dialog" style="width: 1200px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <a class="btn btn-lg btn-success col-md-1"  id="btnGuardarAjuste" title="" onclick="InsertarAjuste()" >Guardar</a>
                        <a class="btn btn-lg btn-danger col-md-1"  id="btnModificarAjuste" title="" onclick="updateAjuste()" >Modificar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" data-dismiss="modal" aria-hidden="true" onclick="limpiarcampoOrdenp()" title="Salir"></a>
                    </div>
                    <!--CONTENIDO DE LA VENTANA--->
                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">Nuevo Ajuste</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Codigo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="codigoAjus" style="font-weight: bold; font-size: 12pt" 
                                               name="codigoAjus" type="text" placeholder="Registro" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-3">
                                        <input disabled id="fechaAjuste" type="datetime" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese Fecha" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Estado</label>  
                                    <div class="col-md-3">
                                        <input disabled id="estadAjuste"  type="text" style="font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese Estado" class="form-control input-sm alert-info">
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idestadAjuste" style="visibility: hidden;" type="text">
                                    </div>
                                </div>
                            </div>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Usuario</label>  
                                    <div class="col-md-2">
                                        <input disabled id="usuarioAjuste" style="font-weight: bold;font-size: 12pt"
                                               type="text" placeholder="Ingrese Usuario" class="form-control input-sm alert-info">
                                    </div>
                                    <label class="col-md-1 control-label">Motivo</label>  
                                    <div class="col-md-3">
                                        <input id="motivoAjuste" style="font-weight: bold;font-size: 12pt" 
                                               type="text" placeholder="Ingrese Motivo" class="form-control input-sm alert-info" onclick="abrirmotivoajust()">
                                    </div>
                                    <label class="col-md-1 control-label">Tipo Ajuste</label>  
                                    <div class="col-md-3">
                                        <select class="form-control" id="vTtipoAjustes">
                                            <option value="1">SOBRANTE</option>
                                            <option value="2">FALTANTE</option>
                                        </select>
                                    </div>
                                    <div class="col-md-2">
                                        <input id="idmotivo" style="visibility: hidden;" type="text">
                                    </div>
                                    <div class="col-md-1">
                                        <input  id="idusuaAjuste" type="text" style="visibility: hidden;">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-2">
                                    <input id="idmercadGenerico" type="text" placeholder="Cod" class="form-control input-sm alert-info" onclick="AbrirModalMercaderiaAjuste()"
                                           style="font-weight: bold; font-size: 12pt">
                                </div>
                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-4">
                                    <input class="form-control input-sm alert-info" disabled="" id="iddescrip" type="text" placeholder="Descirpcion"
                                           style="font-weight: bold; font-size: 12pt">
                                </div>
                                <label class="col-md-1 control-label">Cant*</label>
                                <div class="col-md-2">
                                    <input id="idcanti" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm alert-info" 
                                           style="font-weight: bold; font-size: 12pt"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       CargarMercaGrillaAjuste();
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
                            <table class="table table-striped table-bordered table-hover table input-md" id="miTablaDetalleAjustes" onclick="SeleccionarDetalleAjuste()">
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead>
                                    <tr class="alert-dismissable" >
                                        <th style="display: none"></th>
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th  class="alert-warning" style="width: 30px"><div><center><img src="Recursos/img/delete.png"/></center></div></th>
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
        <div class="modal fade" id="ModalMercaderiaAjuste">
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMercaderiaAjuste" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20" 
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMercaderiaAjuste()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 300px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMercadAjuste"
                                       onclick="seleccionarMercaderiaAjuste()">
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
        <div class="modal fade" id="ModalAjuste">
            <div class="modal-dialog" style="width: 400px;">
                <div class="modal-content">
                    <!--HEADER DE LA VENTANA--->
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                    </div>
                    <DIV class="modal-body">
                        <div class="container-fluid">
                            <div class="form-group">
                                <input id="filtrarMotivoA" type="text" style="text-transform: uppercase; font-weight: bold" maxlength="20"
                                       class="form-control input-md" placeholder="Buscar Mercadeeria..." onkeyup="buscadorTablaMotivoAjust()">
                            </div>
                        </div>
                        <div class="panel-body">
                            <!-- Tabla detalle -->
                            <div id="scroll" class="table-responsive" style="height: 200px" >
                                <table class="table table-striped table-bordered table-hover table input-md" id="miTablaMotivoAjuste" onclick="seleccionarMotivoAjuste()">
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
    </body>
</html>
