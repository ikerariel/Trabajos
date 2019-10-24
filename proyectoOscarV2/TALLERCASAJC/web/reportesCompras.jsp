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
        <script src="validador/reportescompras.js"></script> 
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
                    <div class="col-md-3">
                        
                    </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Libro Compra <span class="">  <input id="vlibrocompra" style="width: 25px; height: 25px" type="checkbox"></span></label> 
                      </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label">Resumen Compra <span class="">  <input id="vComprasresumen" style="width: 25px; height: 25px"  type="checkbox"></span></label> 
                      </div>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label col-md-5">Por Proveedor</label>
                          <div class="col-md-3">
                              <select class="form-control" id="vProveedor">
                                  <option value="5" style="font-weight:bold;color: #003eff">ELIJA UNA OPCIÓN</option>
                                  <option value="1"> CASA CHINA</option>
                                  <option value="2">ELECTRONICA S.A.</option>
                                  <option value="3">ELECTROTOTAL</option>
                                  <option value="4">CASA ORIENTE</option>
                              </select>
                          </div>
                        
                      </div><br>
                      <div class="panel-footer" style="font-weight: bold; text-align: center" >
                          <label class="control-label col-md-5">Por Estado</label>
                          <div class="col-md-3">
                              <select class="form-control" id="vEstado">
                                        <option  value="5" style="font-weight:bold;color: #003eff">ELIJA UNA OPCIÓN</option>
                                  <option value="1">CONFIRMADO</option>
                                  <option value="2">ANULADO</option>
                                  <option value="3">PENDIENTE</option>
                              </select>
                          </div>
                        
                      </div>
                    <BR>
                    <BR>
                    
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
                    
                       <div class="panel-footer col-lg-offset-9" style="font-weight: bold" >
                        <a id="btngenerarinforme" class="btn btn-lg btn-primary" style=" font-weight: bold"  >Generar Reporte </a>
                    </div>
                    <BR>
                
                </div>
              
            </form>
        </section>
    </body>
</html>
