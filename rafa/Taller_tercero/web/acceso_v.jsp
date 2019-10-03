<%-- 
    Document   : menuprincipal_v
    Created on : 25/05/2018, 02:51:34 PM
    Author     : Rafel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Recursos/css/estilos_1.css">
        <link rel="stylesheet" href="Recursos/css/iconos.css">
        <link rel="stylesheet" href="Recursos/css/bootstrap1.css">
        <link rel="stylesheet" href="Recursos/css/font-mfizz.css">
        <link rel="stylesheet" href="Recursos/css/font-awesome.css">
        <script src="Recursos/js/main.js"></script>
        <script src="Recursos/js/bootstrap.js"></script>
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <link rel="stylesheet" href="Recursos/css/bootstrap.css"/>
        <script type="text/javascript" src="Recursos/js/jquery.js"></script>
        <script src="Recursos/js/ImagenFondo.js"></script> 
        <script src="Recursos/js/jquery.backstretch.min.js"></script>
        <script src="Validadores/acceso_v.js"></script>

        <style type="text/css">
            body{
                padding-top: 80px;
                padding-bottom: 40px;

            }
            .login{
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
            }
            #sha{
                max-width: 400px;
                height: 400px;
                -webkit-box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                -moz-box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                box-shadow: 0px 0px 18px 0px rgba(48, 50, 50, 0.48);
                border-radius: 6%;
            }
            #avatar{
                width: 200px;  <%--horizontal--%>
                height: 120px;  <%--vertical--%>
                margin: 0px auto 10px;
                display: block;
                border-radius: 40%;
            }
        </style>

        <title>Acceso al Sistema</title>
    </head>
    <body>
        <div class="container well" id="sha">
            <div class="row">
                <div class="col-xs-12">
                    <img src="Recursos/img/casa th.gif" class="img-responsive" id="avatar">
                </div>
            </div>

            <form class="login" action="accesoServlet"  method="POST">
                <div class="form-group has-feedback">
                    <input id="nombreuser" onblur="obtenerDatos()" style="font-weight: bold" type="text" title="Ingrese su Usuario" class="form-control input-lg"placeholder="Usuario" name="usuario" required autofocus
                         >
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    <input id="claveuser" style="font-weight: bold" type="password" title="Ingrese su Contraseña" class="form-control input-lg"placeholder="Contraseña" name="pass" required autofocus>
                </div>
                <div class="form-group has-feedback" >
                    <span  class="glyphicon glyphicon-bishop form-control-feedback"></span>
                    <input id="Codsucursal" readonly="" name="sucursales"  style="font-weight: bold; display:none " >
                </div>
                <div class="form-group has-feedback">
                    <input id="coddeposito" readonly=""  name="depositos" style="font-weight: bold; display:none " >
                </div>
                <input  title="Conectarse al Sistema" class="btn btn-lg btn-primary btn-block"
                        value="Acceder" type="submit">
            

            </form>
        </div>

    </body>
</html>
