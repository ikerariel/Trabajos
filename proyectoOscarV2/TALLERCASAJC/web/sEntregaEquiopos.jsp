
<%-- 
    Document   : FacturasCompras
    Created on : 05/09/2018, 10:29:16 AM
    Author     : Oscar
--%>        

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="validador/sEntregaEquipos.js"></script> 
        <script src="validador/genericoJS.js"></script> 
        <title>Entrega de Equipos</title>
        <style type="text/css">
            #scrollPlanilla{
                overflow: scroll;
                height:300px;
            }
            #scroll{
                overflow: scroll;
                height:250px;
            }
            #scroll01{
                overflow: scroll;
                height:150px;
            }

            hr {
                height: 1px;
                background-color: black;
                position: relative;
                top: -20px;
            }


        </style>
    </head>
    <body>
        <%@include file="viwmenu.jsp" %>
        <%@include file="resportesmodales.jsp" %>

        <section>
            <form class="form-horizontal"  id="defaultForm">

                <div class="col-md-9" id="botonesRecepcion">
                    <a id="btnNuevoEQ"  class="btn btn-lg btn-success" style=" font-weight: bold"   data-toggle="modal"
                       >Nuevo </a>
                    <a id="btnConfirmarEQ" class="btn btn-lg btn-warning glyphicon glyphicon-ok"  style=" font-weight: bold" title="Confirmar Diagnostico"></a>
                    <a id="btnRevertirEQ" class="btn btn-lg btn-danger glyphicon glyphicon-minus-sign" style=" font-weight: bold" title="Revertir Diagnostico" ></a>
                    <a id="btnInformeEQ" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" font-weight: bold" title="Ver Informe" onclick=""></a>
                </div>

                <br>
                <br>
                <br>

                <div class="panel panel-default">
                    <div class="panel-footer" style="font-weight: bold">PLANILLA DE ENTREGA DE EQUIPOS</div>

                    <div class="">
                        <div class="input-group  input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Nro. Registro*</span>
                            <input id="nroEQ" type="text" style="background-color: #d9edf7" class="form-control" disabled="" placeholder="">
                            <span class="input-group-addon" style=" font-weight: bold" >Estado*</span>
                            <input id="estadoEQ" type="text" style="" class="form-control" disabled="" placeholder="Estado">
                        </div>
                    </div>

                    <div class="">
                        <div class="input-group input-sm">
                            <span class="input-group-addon" style=" font-weight: bold">Buscar</span>
                            <input id="filtroEQ" type="text" style="text-transform: uppercase; font-weight: bold" 
                                   class="form-control " maxlength="20" placeholder="Buscar registro...">
                        </div>
                    </div>
                    <div class="panel-body">

                        <!-- TABLAS DETALLES DE PLANILLA -->

                        <div id="scrollPlanilla" class="table-responsive">
                            <table class="table table-striped table-bordered table-hover table input-md" id="mitablaEQ" >
                                <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                <thead >
                                    <tr  >
                                        <th class="" >NRO. REGISTRO</th>
                                        <th class="">FECHA</th>
                                        <th class="">CLIENTE</th>
                                        <th class="">ESTADO</th>
                                        <th class="">USUARIO</th>
                                        <th class="" style="text-align: center">OPCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody class=""></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </section>

        <!--/////////////  CABECERAS VENTANA DE FACTURAS COMPRAS //////////////////////////////////////////--->

        <div class="modal fade" id="ventanaEQ">
            <div class="modal-dialog" style="width: 80%;">
                <div class="modal-content">

                    <!--HEADER DE LA VENTANA//////////////////////////////////////////////////////////////////////--->

                    <div class="modal-header" >
                        <a class="btn btn-lg btn-primary col-md-1" style="display: none"  id="btnguardarEQ" title=""  >Guardar</a>
                        <a class="btn btn-lg btn-success col-md-1" style="display: none" id="btntmodificarEQ" title=""  >Guardar</a>
                        <a class="close  btn btn-lg btn-danger glyphicon glyphicon-off" id="btncloseEQ" data-dismiss="modal" aria-hidden="true" title="Salir"></a>
                    </div>

                    <!-- //////PLANILLA DE CARGA DE DETALLES ////--->

                    <div class="panel">
                        <div class="panel panel-default">
                            <div class="panel-footer" style="font-weight: bold">NUEVA ENTREGA DE EQUPOS</div>
                            <br> 
                            <div class="form-horizontal">
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Nro.</label>  
                                    <div class="col-md-1">
                                        <input disabled="" id="codigoEQ" style="text-transform: uppercase; font-weight: bold; font-size: 12pt" 
                                               name="codigo" type="text" placeholder="Codigo" class="form-control input-sm ">
                                    </div>

                                    <label class="col-md-1 control-label">Fecha</label>  
                                    <div class="col-md-2">
                                        <input disabled id="fechaEQ" type="datetime" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               placeholder="Ingrese fecha" class="form-control input-sm ">
                                    </div>
                                    <label class="col-md-2  control-label">Nro. Orden Trabajo</label>  
                                    <div class="col-md-1">
                                        <input list="listOTeq" id="nrorecOdentrabajoEQ" type="text" style="font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm "
                                               onkeydown="
                                                           if (event.keyCode === 13) {
                                                               recuperarOTeq();
                                                           }">
                                                                                        <datalist id="listOTeq"  >
                                        </datalist>
                                    </div>
                                    <label class="col-md-2  control-label">Fecha Orden Trabajo</label>  
                                    <div class="col-md-2">
                                        <input disabled="" id="fechaOrdentrabajoEQ" type="text" style="font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">
                                    </div>


                                </div>
                                <div class="form-group">

                                    <label class="col-md-1 control-label">Cliente</label>  
                                    <div class="col-md-5">
                                        <input disabled id="clinteNombreEQ" type="text" style="text-transform: uppercase; font-weight: bold;font-size: 12pt"
                                               class="form-control input-sm ">
                                        <input  id="idclienteEQ" type="text" style="display: none"
                                                class="form-control input-sm ">

                                    </div>
                                    <label class="col-md-1 control-label">Observación.</label>  
                                    <div class="col-md-5">
                                        <textarea id="obsEQ" maxlength="200" style="font-weight: bold; font-size: 12pt" 
                                                  n type="text" placeholder="Ingese el diagnóstico." class="form-control input-sm "></textarea>
                                    </div>


                                </div>
                            </div>



                        </div>

                        <!--HEADER DE LA VENTANA detalle de Articulo////////////////////////////////////////////////////--->

                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-1 control-label">Cod*</label>
                                <div class="col-md-1">
                                    <input list="listaarticulosEQ"  name="lisart "id="v_articusEQ" type="text" 
                                           placeholder="Cod" class="form-control"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       traerArti();
                                                   }">
                                    <datalist id="listaarticulosEQ">
                                    </datalist>
                                </div>

                                <label class="col-md-1 control-label">Desciprción</label>
                                <div class="col-md-5">
                                    <input class="form-control input-sm" disabled="" id="descripartiuloEQ" type="text" placeholder="Descirpcion del articulo"
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6;font-size: 12pt"
                                           onkeyup="separadorMiles(this)" onchange="separadorMiles(this)">
                                </div>



                                <label class="col-md-1 control-label">Cantidad*</label>
                                <div class="col-md-1">
                                    <input id="cantArtEQ" type="text" placeholder="Cant" maxlength="3" class="form-control input-sm" 
                                           style="text-transform: uppercase; font-weight: bold; background-color:#e6ffe6; font-size: 12pt"
                                           onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
                                           onkeydown="
                                                   if (event.keyCode === 13) {
                                                       cargaGrillaD();
                                                   }">
                                </div>

                                <div class="col-md-1">
                                    <input disabled="" id="codArtiEQ" type="text" placeholder="" maxlength="3" class="form-control input-sm" 
                                           style="visibility: hidden;">
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">

                            <!-- Tabla detalle para cargar aeticulo -->

                            <div class="table-responsive" style="height: 180px">
                                <table  class="table table-striped table-bordered table-hover table input-md" id="mitabladetalleEQ">
                                    <!--<table class="table table-hover  table-condensed with-pager input-md" id="miTabla" onclick="seleccion()">-->
                                    <thead>
                                        <tr class="alert-dismissable" >
                                            <th class="alert-info">CODIGO</th>
                                            <th class="alert-info">DESCRIPCION</th>
                                            <th class="alert-info">CANTIDAD</th>
                                            <th class="alert-info"></th>

                                        </tr>
                                    </thead>
                                    <tbody  id="table_deta" style="font-weight: bold;font-size: 10pt" >

                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div> 
        </div>


    </div>

</body>
</html>