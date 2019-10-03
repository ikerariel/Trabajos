<%-- 
    Document   : categoriasvista
    Created on : 22/05/2019, 01:27:49 PM
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
        <script src="Validadores/categoriasvalidador.js"></script>
        <script type="text/javascript" src="Validadores/buscadorvalidador.js"></script>
        <title>CATEGORIAS</title>
    </head>
    <body>
        <%@ include file="menutercero.jsp"%>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <label class="col-md-8 control-label" style=" font-weight: bold; font-size: 25pt; color: #003eff">GESTIONAR CATEGORIAS</label> 
                <BR>
                <BR>
                <BR>
                <div class="panel panel-default">              
                    <div class="panel-footer" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-success" style=" display: none; font-weight: bold" onclick="autonumerico(); abilitarInsertCate()">Nuevo </a>
                        <a id="btnInsertar" class="btn btn-lg btn-primary" style=" display: none; font-weight: bold" onclick="controlarCampocate(), campovaciocate()">Insertar </a>
                        <a id="btnModificar" class="btn btn-lg btn-info" style=" display: none; font-weight: bold" title="Modificar" onclick="amb(2)">Modificar </a>
                        <a id="btnAnular" class="btn btn-lg btn-danger" style=" display: none; font-weight: bold" title="Borrar" onclick="amb(3)">Borrar*</a>
                        <a id="btnLimpiar" class="btn btn-lg btn-warning" style=" display: none; font-weight: bold" title="Borrar" onclick="limpiarcampocate()">limpiar</a>
                        <a id="btnReporte" class="btn btn-lg btn-primary glyphicon glyphicon-print" style=" display: none" title="Reportes" onclick="reportesCatego()"></a>
                    </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold">CODIGO</label>  
                                <div class="col-md-4">
                                    <input disabled="" id="cod_categoria" style="font-weight: bold; font-size: 12pt;
                                            background-color: #d9edf7 " type="text" placeholder="Registro" class="form-control input-sm" required=""
                                        onkeydown=" if (event.keyCode === 13) { listarCategoriaSegunFiltro(); }">
                                </div>
                                <label class="col-md-1 control-label" style=" font-weight: bold">NOMBRE</label>  
                                <div class="col-md-4">
                                    <input disabled="" id="descr_categoria"  type="text" style="font-weight: bold; font-size: 12pt; background-color: #d9edf7"
                                           placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="" onkeyup="return sololetrasCate(event)">
                                </div>
                            </div>
                        </div> 
                    </div> 
                    <BR>
                    <div class="input-group">
                        <span class="input-group-addon">Buscar</span>
                        <input class="form-control" type="text" id="filtrar" placeholder="Ingrese Descripcion a Buscar"
                               style="font-weight: bold; font-size:12pt; background-color: #d9edf7 ">
                    </div>
                    <br>
                    
                    <div class="table-responsive" > 
                        <div class="headercontainer" >
                            <div class="tablecontainer">
                                <table class=" table-striped table-bordered table-hover table input-md" id="miTabla" onclick="seleccioncate()">
                                    <thead>
                                        <tr>
                                            <th><div>CODIGO</div></th>
                                            <th><div>CATEGORIAS</div></th>
                                        </tr>
                                    </thead>
                                    <tbody class="buscar"></tbody>
                                </table>
                            </div>
                        </div>     
                    </div>
                </div>
                <div id="footer">
                rafael.fg80@gmail.com
                </div>
            </form>
        </section>
    </body>
</html>
