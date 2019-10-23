<%-- 
    Document   : estadovista
    Created on : 22/05/2019, 02:13:14 PM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Reportes</title>
  
    </head>
    <body>
       <%@include file="viwmenu.jsp" %>
        <section>
            <form class="form-horizontal"  id="defaultForm">
                <div style="text-align: center">
                    <label class=" control-label" style=" font-weight: bold; font-size: 25pt; color: #ffffff">Informes</label>   
                </div>
                <div class="panel panel-default"> 
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Libro Compra <span class="">  <input style="height: 20px; width: 20px" id="vlibrocompra" type="checkbox"></span></label> 
                      </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Resumen Compra <span class="">  <input style="height: 20px; width: 20px" id="vComprasresumen" type="checkbox"></span></label> 
                      </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Por Proveedor <span class="">  <input style="height: 20px; width: 20px" id="vComprasresumen" type="checkbox"></span></label> 
                      </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Por Estado <span class="">  <input style="height: 20px; width: 20px" id="vComprasresumen" type="checkbox"></span></label> 
                      </div>
                    <BR>
                    <div class="">
                        <div class="form-horizontal container" >
                            <div class="form-group" ><br>
                                
                                <label class="col-md-2 control-label" style=" font-weight: bold;left: 200px">FECHA DESDE</label>  
                                <div class="col-md-3" style="left: 220px">
                                    <input  id="vFechadesde" style="text-transform: uppercase; font-weight: bold; font-size: 12pt;
                                            background-color: #ffffff " type="date" placeholder="Registro" class="form-control input-sm" required=""
                                       >
                               
                                </div>
                       
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label" style=" font-weight: bold;left: 200px">FECHA HASTA</label>  
                                <div class="col-md-3" style="left: 220px">
                                    <input id="vFechahasta"  type="date" style="text-transform: uppercase; font-weight: bold;font-size: 12pt;
                                           background-color: #ffffff" placeholder="Ingrese nombre" class="form-control input-sm" required autofocus="">
                                </div>
                            </div>
                        </div> 
                    </div> 
                       <div class="panel-footer col-lg-offset-9" style="font-weight: bold" >
                        <a id="btnNuevo" class="btn btn-lg btn-primary" style=" font-weight: bold" onclick="ver()" >Generar Reporte </a>
                    </div>
                    <BR>
                
                </div>
              
            </form>
        </section>
    </body>
</html>
